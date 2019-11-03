package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.envision.Adapter.CustomerDetailRecyclerView;
import com.example.envision.R;
import com.example.envision.impl.KNNAlgorithm;
import com.example.envision.interfaces.ApiResponseListener;
import com.example.envision.loandefaulter.constants.ApiConstants;
import com.example.envision.loandefaulter.model.Account;
import com.example.envision.loandefaulter.model.BillPayment;
import com.example.envision.loandefaulter.model.Customer;
import com.example.envision.loandefaulter.model.Transaction;
import com.example.envision.loandefaulter.service.RequestQueueService;
import com.example.envision.loandefaulter.service.TDApiService;
import com.example.envision.utility.BankPrediction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class CustomerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardView;
    private TextView name, age, school, work, income,accountId,gender;
    private Customer customer;
    private Account creditAccount;
    private RecyclerView recyclerView;
    private de.hdodenhof.circleimageview.CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        final Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("CUSTOMER");
        initTextView();
        initRecyclerView();
    }

    public void initTextView() {
        name = findViewById(R.id.activity_person_detal_name_tv);
        name.setText(customer.getFirstName()+" "+customer.getLastName());
        age = findViewById(R.id.activity_person_detail_age_tv);
        age.setText(String.valueOf(customer.getAge()));
        gender = findViewById(R.id.activity_person_detail_gender_tv);
        gender.setText(customer.getGender());
        school = findViewById(R.id.activity_person_detail_school_tv);
        school.setText("-NA-");
        work =  findViewById(R.id.activity_person_detail_work_tv);
        if(customer.getWorkType()!=null && !customer.getWorkType().isEmpty()){
            work.setText(customer.getWorkType());
        } else {
            work.setText("-NA-");
        }
        work.setText(customer.getWorkType()!=null ? customer.getWorkType().toUpperCase():"-NA-");
        income = findViewById(R.id.activity_person_detail_income_tv);
        income.setText(String.valueOf(customer.getTotalIncome()));
        accountId = findViewById(R.id.activity_person_detail_account_id);
        accountId.setText(customer.getCustomerId());
        cardView = findViewById(R.id.activity_person_detail_predict_cardview);
        cardView.setOnClickListener(this);
        imageView = findViewById(R.id.profile_image);

        if(customer.getGender().equalsIgnoreCase("Male"))
            imageView.setImageResource(R.drawable.male);
        else
            imageView.setImageResource(R.drawable.female);

    }

    public void initRecyclerView() {
        recyclerView = findViewById(R.id.activity_person_detail_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            final TDApiService apiService = new TDApiService();
            apiService.getRequest(this, apiAccountResponseListener, String.format(ApiConstants.TD_FETCH_CUSTOMER_ACCOUNTS, customer.getCustomerId()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Implementing interfaces of FetchDataListener for GET  accounts api request
    ApiResponseListener apiAccountResponseListener = new ApiResponseListener() {
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

    @Override
    public void onClick(View v) {
        if( v == cardView){
            try {
                creditAccount = customer.getAccounts().get("creditCardAccounts").get(0);
                final TDApiService apiService = new TDApiService();
                apiService.getRequest(this, apiTransactionResponseListener, String.format(ApiConstants.TD_FETCH_ACCOUNT_TRANSACTIONS, creditAccount.getAccountId()));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //Implementing interfaces of FetchDataListener for GET  accounts api request
    ApiResponseListener apiTransactionResponseListener = new ApiResponseListener() {
        @Override
        public void onFetchComplete(JSONObject data) {
            RequestQueueService.cancelProgressDialog();
            try {
                if (data != null) {
                    if (data.has("statusCode")) {
                        int statusCode = data.getInt("statusCode");
                        if(statusCode == 200){
                            final JSONArray transactionArray = data.getJSONArray("result");
                            if(transactionArray.length() > 0) {
                                final ArrayList<Transaction> transactions = getTransactions(transactionArray);
                                Collections.sort(transactions, Collections.<Transaction>reverseOrder());
                                creditAccount.setTransactions(transactions);
                                final ArrayList<BillPayment> payments = getBillPayments(transactions);
                                callPrediction(payments);
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

    private ArrayList<Transaction> getTransactions(final JSONArray transactionArray){
        final ArrayList<Transaction> transactions = new ArrayList<>();
        try{
            for (int i = 0; i < transactionArray.length(); i++) {
                final JSONObject transactionObject = transactionArray.getJSONObject(i);
                final Transaction transaction = new Transaction();
                transaction.setTransactionId(transactionObject.getString("id"));
                transaction.setAccountId(creditAccount.getAccountId());
                transaction.setCustomerId(customer.getCustomerId());
                transaction.setType(transactionObject.getString("type"));
                transaction.setAmount(transactionObject.getDouble("currencyAmount"));
                transaction.setSource(transactionObject.getString("source"));
                if(transactionObject.has("merchantId")){
                    transaction.setMerchantId(transactionObject.getString("merchantId"));
                }
                transaction.setDateTime(transactionObject.getString("originationDateTime"));
                transaction.setTag(transactionObject.getJSONArray("categoryTags").getString(0));
                transaction.setDescription(transactionObject.getString("description"));

                transactions.add(transaction);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private ArrayList<BillPayment> getBillPayments(final ArrayList<Transaction> transactions){
        final ArrayList<BillPayment> payments = new ArrayList<>();
        double currentBillAmount = creditAccount.getBalance();
        String lastDate = "";

        for (int i = 0; i < transactions.size(); i++) {

            if(transactions.get(i).getAmount() < 0){
                currentBillAmount += transactions.get(i).getAmount();
                final BillPayment payment = new BillPayment();
                payment.setAmountPaid(Math.abs(transactions.get(i).getAmount()));
                payment.setBillAmount(currentBillAmount);
                payment.setPaymentDate(transactions.get(i).getDateTime());

                if(payments.size() > 0){
                    int lastIndex = payments.size() - 1;
                    int diff = getDateDiffInMonths(payments.get(lastIndex).getPaymentDate(), payment.getPaymentDate());
                    payments.get(lastIndex).setDelay(diff <= 0 ? 0 : diff - 1);
                }
                payments.add(payment);
            } else {
                currentBillAmount -= transactions.get(i).getAmount();
            }

            lastDate = transactions.get(i).getDateTime();

            if(payments.size() == 7){
                break;
            }
        }

        if(payments.size() == 7){
            payments.remove(payments.size() - 1);
        } else {
            boolean flag = false;
            for(int i = payments.size(); i <= 7; i++){
                final BillPayment payment = new BillPayment();
                payment.setAmountPaid(0);
                payment.setBillAmount(currentBillAmount);
                payment.setPaymentDate(lastDate);
                payment.setDelay(0);
                if(!flag && payments.size() > 0){
                    int lastIndex = payments.size() - 1;
                    int diff = getDateDiffInMonths(payments.get(lastIndex).getPaymentDate(), payment.getPaymentDate());
                    payments.get(lastIndex).setDelay(diff <= 0 ? 0 : diff - 1);
                    flag = true;
                }
                if(payments.size() == 6){
                    break;
                }
                payments.add(payment);
            }
        }

        return payments;
    }

    private int getDateDiffInMonths(String fromDate, String toDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(fromDate.split("T")[0]);
            Date date2 = sdf.parse(toDate.split("T")[0]);
            long diff = date1.getTime() - date2.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            int diffMonths = 0;
            if(diffDays > 30)
                diffMonths = (int)(diffDays / 30);
            return  diffMonths;
        } catch (ParseException e){
            System.out.println("Error while comparing dates...");
            e.printStackTrace();
            return 0;
        }
    }

    private void callPrediction(final ArrayList<BillPayment> payments){
        final ArrayList<Double> inputData = new ArrayList<>();
        inputData.add(creditAccount.getBalance());
        inputData.add((double)BankPrediction.getGenderValue(customer.getGender()));
        inputData.add((double)BankPrediction.getWorkValue(customer.getWorkType()));
        inputData.add((double)BankPrediction.getMaritalStatusValue(customer.getRelationshipStatus()));
        inputData.add((double)customer.getAge());
        for (BillPayment payment : payments){
            inputData.add(payment.getDelay());
        }
        for (BillPayment payment : payments){
            inputData.add(payment.getBillAmount());
        }
        for (BillPayment payment : payments){
            inputData.add(payment.getAmountPaid());
        }

        KNNAlgorithm knnAlgorithm = new KNNAlgorithm(CustomerDetailsActivity.this);
        boolean returnValue = knnAlgorithm.getPrediction(inputData,1);
        String result;
        if(returnValue)
            result = "The person is not likely to be a loan defaulter";
        else
            result = "The person is likely to be a loan defaulter";
        new AlertDialog.Builder(CustomerDetailsActivity.this)
                .setTitle("Result")
                .setMessage(result)
                .setPositiveButton("Ok",null)
                .create().show();

    }

}
