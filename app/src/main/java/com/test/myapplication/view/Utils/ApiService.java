package com.test.myapplication.view.Utils;

import com.test.myapplication.view.Model.RecyPojo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/users?page=2")
    Call<RecyPojo> getDetails();
}
