package com.example.envision.loandefaulter.service;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.envision.interfaces.ApiResponseListener;
import com.example.envision.loandefaulter.constants.ApiConstants;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class TDApiService {

    public void getRequest(final Context context, final ApiResponseListener listener, final String ApiURL) throws JSONException {
        if (listener != null) {
            //call onFetchComplete of the listener
            //to show progress dialog
            //-indicates calling started
            listener.onFetchStart();
        }
        //base server URL
        String baseUrl = ApiConstants.TD_API_URL;
        //and make full api
        String url = baseUrl + ApiURL;
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (listener != null) {
                            if(response.has("result")) {
                                //received response
                                //call onFetchComplete of the listener
                                listener.onFetchComplete(response);
                            }else if(response.has("errorMsg")){
                                //has error in response
                                //call onFetchFailure of the listener
                                listener.onFetchFailure(response.getString("errorMsg"));
                            } else{
                                listener.onFetchComplete(null);
                            }
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        listener.onFetchFailure("Network Connectivity Problem");
                    } else if (error.networkResponse != null && error.networkResponse.data != null) {
                        VolleyError volley_error = new VolleyError(new String(error.networkResponse.data));
                        String errorMessage = "";
                        try {
                            JSONObject errorJson = new JSONObject(volley_error.getMessage().toString());
                            if(errorJson.has("error")) {
                                errorMessage = errorJson.getString("error");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (errorMessage.isEmpty()) {
                            errorMessage = volley_error.getMessage();
                        }

                        if (listener != null) listener.onFetchFailure(errorMessage);
                    } else {
                        listener.onFetchFailure("Something went wrong. Please try again later");
                    }
                }
            }){
                // Add the API Key to the Authorization header of the API request call.
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", ApiConstants.TD_API_KEY);
                    return params;
                }
        };

        RequestQueueService.getInstance(context).addToRequestQueue(postRequest.setShouldCache(false));
    }

    public void postRequest(final Context context, final ApiResponseListener listener, JSONObject params, final String ApiURL) throws JSONException {
        if (listener != null) {
            //call onFetchComplete of the listener
            //to show progress dialog
            //-indicates calling started
            listener.onFetchStart();
        }
        //base server URL
        String baseUrl = ApiConstants.TD_API_URL;
        //and make full api
        String url = baseUrl + ApiURL;
        //Requesting with post body as params
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (listener != null) {
                                if(response.has("result")) {
                                    //received response
                                    //call onFetchComplete of the listener
                                    listener.onFetchComplete(response);
                                }else if(response.has("error")){
                                    //has error in response
                                    //call onFetchFailure of the listener
                                    listener.onFetchFailure(response.getString("errorMsg"));
                                } else{
                                    listener.onFetchComplete(null);
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    listener.onFetchFailure("Network Connectivity Problem");
                } else if (error.networkResponse != null && error.networkResponse.data != null) {
                    VolleyError volley_error = new VolleyError(new String(error.networkResponse.data));
                    String errorMessage = "";
                    try {
                        JSONObject errorJson = new JSONObject(volley_error.getMessage().toString());
                        if(errorJson.has("error")){
                            errorMessage = errorJson.getString("error");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (errorMessage.isEmpty()) {
                        errorMessage = volley_error.getMessage();
                    }

                    if (listener != null) listener.onFetchFailure(errorMessage);
                } else {
                    listener.onFetchFailure("Something went wrong. Please try again later");
                }

            }
        });

        RequestQueueService.getInstance(context).addToRequestQueue(postRequest.setShouldCache(false));
    }
}
