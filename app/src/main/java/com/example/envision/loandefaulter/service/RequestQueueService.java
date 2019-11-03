package com.example.envision.loandefaulter.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class RequestQueueService {

    private static RequestQueueService instance;
    private RequestQueue requestQueue;
    private static Context context;
    private static ProgressDialog progressDialog;

    private RequestQueueService(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueService getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueService(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public Map<String, String> getRequestHeader() {
        Map<String, String> headerMap = new HashMap<>();
        return headerMap;
    }

    public void clearCache() {
        requestQueue.getCache().clear();
    }

    public void removeCache(String key) {
        requestQueue.getCache().remove(key);
    }

    //To show alert / error message
    public static void showAlert(String message, final Context context) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Error!");
            builder.setMessage(message);
            builder.setPositiveButton("OK", null);

            builder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Start showing progress
    public static void showProgressDialog(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null) {
                    if (progressDialog.isShowing() == true) cancelProgressDialog();
                }

                progressDialog = ProgressDialog.show(activity,"Please wait..", "We are fetching data",true,true);
//                progressDialog = new Dialog(activity);
//                progressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//                progressDialog.setContentView(R.layout.progress_indicator);
//                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                progressDialog.show();
//                progressDialog.setCancelable(false);
            }
        });

    }

    //Stop showing progress
    public static void cancelProgressDialog() {
        if (progressDialog != null){
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

}
