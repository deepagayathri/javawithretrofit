package com.test.myapplication.view.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.test.myapplication.R;
import com.test.myapplication.view.Model.DataItem;
import com.test.myapplication.view.Model.RecyPojo;
import com.test.myapplication.view.Utils.ApiClient;
import com.test.myapplication.view.Utils.ApiService;
import com.test.myapplication.view.Utils.InternetConnection;
import com.test.myapplication.view.adapter.DataAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class Recy_Page extends AppCompatActivity {
    private ArrayList<DataItem> contactList;
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy__page);
        loadData();
    }

    private void loadData() {
        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(Recy_Page.this);
            dialog.setTitle("Getting JSON data");
            dialog.setMessage("Please wait...");
            dialog.show();

            ApiService api = ApiClient.getApiService();
            Call<RecyPojo> call = api.getDetails();

            call.enqueue(new Callback<RecyPojo>() {
                @Override
                public void onResponse(Call<RecyPojo> call, Response<RecyPojo> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {

                        /**
                         * Got Successfully
                         */
                        Log.e("LOGGG", "onResponse: " + (ArrayList<DataItem>) response.body().getData());
                        contactList = (ArrayList<DataItem>) response.body().getData();

                        /**
                         * Binding that List to Adapter
                         */
                        RecyclerView recyclerContacts = (RecyclerView) findViewById(R.id.recyclerContacts);
                        adapter = new DataAdapter(contactList, Recy_Page.this);
                        recyclerContacts.setAdapter(adapter);
                        recyclerContacts.setLayoutManager(new LinearLayoutManager(Recy_Page.this));
                        performAdapterClick();

                    } else {
                        Snackbar.make(findViewById(R.id.layoutMain), "Something going wrong!", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RecyPojo> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(findViewById(R.id.layoutMain), "Check your internet connection!", Snackbar.LENGTH_LONG).show();
        }
    }

    private void performAdapterClick() {

        adapter.setOnViewStatsClick(new DataAdapter.OnViewStatsClick() {
            @Override
            public void OnViewStatsClickListioner(int position) {

                Intent aBookingDetailsIntent = new Intent(getApplicationContext(), ClickAdapterActivity.class);
                aBookingDetailsIntent.putExtra("id", contactList.get(position).getId());
                aBookingDetailsIntent.putExtra("last_name", contactList.get(position).getLastName());
                startActivity(aBookingDetailsIntent);

            }

        });


    }
}
