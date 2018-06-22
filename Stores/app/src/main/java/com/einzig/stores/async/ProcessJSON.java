package com.einzig.stores.async;

import android.os.AsyncTask;

import com.einzig.stores.Constants;
import com.einzig.stores.activities.MainActivity;
import com.einzig.stores.objects.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProcessJSON extends AsyncTask<Void, List<Store>, List<Store>> {

    private WeakReference<MainActivity> context;

    public ProcessJSON(WeakReference<MainActivity> context) {
        this.context = context;
    }

    @Override
    protected List<Store> doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();
        List<Store> stores = new ArrayList<>();
        Request request = new Request.Builder()
                .url(Constants.DEV_URL_STRING)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                Gson gson = new Gson();
                JSONObject jsonObject = new JSONObject(body.string());
                JSONArray jsonArray = jsonObject.getJSONArray("stores");
                stores = gson.fromJson(jsonArray.toString(), new TypeToken<List<Store>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stores;
    }

    @Override
    protected void onPostExecute(List<Store> stores) {
        if (context.get() != null)
            context.get().dismissProgress();
    }
}
