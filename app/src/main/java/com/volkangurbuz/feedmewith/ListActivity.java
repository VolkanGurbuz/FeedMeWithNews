package com.volkangurbuz.feedmewith;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


  public class ListActivity extends AppCompatActivity {


    ArrayList<NewsModel> list;
    private ListView listView;
    private NewAdapter newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.newsList);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            getTechInfoFromWebsite(value);
            //The key argument here must match that used in the other activity
        }
        newAdapter = new NewAdapter(this, list);

        listView.setAdapter(newAdapter);


    }


    protected void getTechInfoFromWebsite(String topic) {


        list = new ArrayList<>();

        final ProgressDialog pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Lütfen bekleyiniz, bilgileri getiriyorum...");


        final Gson gson = new Gson();
        String url = "https://api.hurriyet.com.tr/v1/articles?%24filter=Path%20eq%20'/" + topic + "/'";

        RequestQueue requestQueue = Volley.newRequestQueue(ListActivity.this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            NewsModel haberModel = gson.fromJson(jsonArray.get(i), NewsModel.class);
                            list.add(haberModel);
                        }
                        newAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("responseErr", error.getMessage());
                pd.dismiss();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("accept", "application/json");
                params.put("apikey", "api key buraya gelmeli");
                return params;
            }
        };
        requestQueue.add(stringRequest);
        pd.show();


    }


}

