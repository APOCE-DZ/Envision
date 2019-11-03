package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.envision.Adapter.CustomerDetailRecyclerView;
import com.example.envision.BankruptPredictorActivity;
import com.example.envision.R;
import com.example.envision.interfaces.ApiResponseListener;
import com.example.envision.loandefaulter.constants.ApiConstants;
import com.example.envision.loandefaulter.model.Account;
import com.example.envision.loandefaulter.model.Customer;
import com.example.envision.loandefaulter.service.RequestQueueService;
import com.example.envision.loandefaulter.service.TDApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardView;
    private TextView name, age, school, work, income,accountId;
    private Customer customer;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        final Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("CUSTOMER");
        initTextView();
        initRecyclerView();
    }

    @Override
    public void onClick(View v) {
        if( v == cardView){
            startActivity(new Intent(CustomerDetailsActivity.this, BankruptPredictorActivity.class));
        }
    }

    public void initTextView() {
        name = findViewById(R.id.activity_person_detal_name_tv);
        name.setText(customer.getFirstName()+" "+customer.getLastName());
        age = findViewById(R.id.activity_person_detail_age_tv);
        age.setText(customer.getAge());
        school = findViewById(R.id.activity_person_detail_school_tv);
        work =  findViewById(R.id.activity_person_detail_work_tv);
        income = findViewById(R.id.activity_person_detail_income_tv);
        accountId = findViewById(R.id.activity_person_detail_account_id);
        cardView = findViewById(R.id.activity_person_detail_predict_cardview);
        cardView.setOnClickListener(this);
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.activity_person_detail_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            TDApiService apiService = new TDApiService();
            apiService.getRequest(this, apiResponseListener, String.format(ApiConstants.TD_FETCH_CUSTOMER, customer.getCustomerId()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Implementing interfaces of FetchDataListener for GET api request
    ApiResponseListener apiResponseListener = new ApiResponseListener() {
        @Override
        public void onFetchComplete(JSONObject data) {
            RequestQueueService.cancelProgressDialog();
            try {
                if (data != null) {
                    if (data.has("statusCode")) {
                        int statusCode = data.getInt("statusCode");
                        if(statusCode == 200){
                            JSONObject response = data.getJSONObject("result");
                            if(response != null) {
                                final HashMap<String, ArrayList<Account>> accounts = getCustomerAccounts(response);
                                customer.setAccounts(accounts);
                                recyclerView.setAdapter(new CustomerDetailRecyclerView(CustomerDetailsActivity.this, customer));
                            }
                        }else{
                            RequestQueueService.showAlert("Error! No data fetched", CustomerDetailsActivity.this);
                        }
                    }
                } else {
                    RequestQueueService.showAlert("Error! No data fetched", CustomerDetailsActivity.this);
                }
            }catch (Exception e){
                RequestQueueService.showAlert("Something went wrong", CustomerDetailsActivity.this);
                e.printStackTrace();
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            RequestQueueService.showAlert(msg,CustomerDetailsActivity.this);
        }

        @Override
        public void onFetchStart() {
            RequestQueueService.showProgressDialog(CustomerDetailsActivity.this);
        }
    };

    private HashMap<String, ArrayList<Account>> getCustomerAccounts(final JSONObject response) {
        final HashMap<String, ArrayList<Account>> accountList = new HashMap<>();
        try{
            if(response.has("bankAccounts")){
                final JSONArray bankAccounts = response.getJSONArray("bankAccounts");
                final ArrayList<Account> accounts = getAccounts(bankAccounts, "bankAccounts");
                accountList.put("bankAccounts", accounts);
            }
            if(response.has("creditCardAccounts")){
                final JSONArray creditCardAccounts = response.getJSONArray("creditCardAccounts");
                final ArrayList<Account> accounts = getAccounts(creditCardAccounts, "creditCardAccounts");
                accountList.put("creditCardAccounts", accounts);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    private ArrayList<Account> getAccounts(final JSONArray accountArray, final String accountType){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            for (int i = 0; i < accountArray.length(); i++) {
                final JSONObject accountObject = accountArray.getJSONObject(i);
                final Account account = new Account();
                account.setAccountId(accountObject.getString("id"));
                account.setCustomerId(accountObject.getString("relatedCustomerId"));
                account.setBalance(accountObject.getDouble("balance"));
                account.setAccountType(accountType);
                account.setCardType(accountObject.getString("type"));
                account.setCurrency(accountObject.getString("currency"));
                account.setOpenDate(accountObject.getString("openDate"));
                account.setMaskNumber(accountObject.has("maskedAccountNumber") ?
                                        accountObject.getString("maskedAccountNumber") :
                                        accountObject.getString("maskedNumber"));
                accounts.add(account);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
