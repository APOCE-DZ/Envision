package com.example.envision.interfaces;

import org.json.JSONObject;

public interface ApiResponseListener {

    void onFetchComplete(JSONObject data);

    void onFetchFailure(String msg);

    void onFetchStart();
}
