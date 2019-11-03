package com.example.envision.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.envision.Adapter.ListPageRecyclerView;
import com.example.envision.R;
import com.example.envision.interfaces.ApiResponseListener;
import com.example.envision.loandefaulter.constants.ApiConstants;
import com.example.envision.loandefaulter.model.Customer;
import com.example.envision.loandefaulter.service.RequestQueueService;
import com.example.envision.loandefaulter.service.TDApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPage extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        //initList();
        initRecyclerView();
    }

//    public void initList(){
//        list = new ArrayList<>();
//        for(int i= 300;i<310;i++){
//            list.add("Name  "+  i);
//        }
//    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.listpage_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.setAdapter(new ListPageRecyclerView(this,list));
        try {
            TDApiService apiService = new TDApiService();
            JSONObject params=new JSONObject();
            try {
                params.put("continuationToken","1_20");
            }catch (Exception e){
                e.printStackTrace();
            }
            apiService.postRequest(this, apiResponseListener, params, ApiConstants.TD_FETCH_CUSTOMER);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Implementing interfaces of FetchDataListener for POST api request
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
                                final ArrayList<Customer> customers = getCustomers(response);
                                recyclerView.setAdapter(new ListPageRecyclerView(ListPage.this, customers));
                            }
                        }else{
                            RequestQueueService.showAlert("Error! No data fetched", ListPage.this);
                        }
                    }
                } else {
                    RequestQueueService.showAlert("Error! No data fetched", ListPage.this);
                }
            }catch (Exception e){
                RequestQueueService.showAlert("Something went wrong", ListPage.this);
                e.printStackTrace();
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            RequestQueueService.showAlert(msg,ListPage.this);
        }

        @Override
        public void onFetchStart() {
            RequestQueueService.showProgressDialog(ListPage.this);
        }
    };

    private ArrayList<Customer> getCustomers(final JSONObject response) {
        final ArrayList<Customer> customerList = new ArrayList<>();
        try{
            final JSONArray customers = response.getJSONArray("customers");
            for (int i = 0; i < customers.length(); i++) {
                final JSONObject customerObject = customers.getJSONObject(i);
                final Customer customer = new Customer();
                customer.setCustomerId(customerObject.getString("id"));
                customer.setFirstName(customerObject.getString("givenName"));
                customer.setLastName(customerObject.getString("surname"));
                customer.setAge(customerObject.getInt("age"));
                customer.setTotalIncome(customerObject.has("totalIncome") ? customerObject.getInt("totalIncome") : 0);
                customer.setGender(customerObject.getString("gender"));
                customer.setBirthDate(customerObject.getString("birthDate"));
                customer.setRelationshipStatus(customerObject.getString("relationshipStatus"));
                customer.setGender(customerObject.has("workActivity") ? customerObject.getString("workActivity") : "");

                StringBuilder address = new StringBuilder();
                final JSONObject principalResidence = customerObject.getJSONObject("addresses").getJSONObject("principalResidence");
                address.append(principalResidence.getString("streetNumber"));
                address.append(", ");
                address.append(principalResidence.getString("streetName"));
                address.append(", ");
                address.append(principalResidence.getString("municipality"));
                address.append(", ");
                address.append(principalResidence.getString("postalCode"));
                customer.setAddress(address.toString());

                customerList.add(customer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  customerList;
    }
}
