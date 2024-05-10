package com.project.togather.retrofit.interfaceAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    @GET("/user/phone")
    Call<ResponseBody> checkPhoneNumber(@Query("phone") String phone);
    @GET("/user/doublecheck")
    Call<Boolean> doubleCheckUserName(@Query("name") String name);
    @POST("/user")
    Call<ResponseBody> signUp(
            @Query("phone") String phone,
            @Query("name") String name
    );
    @POST("/login")
    Call<ResponseBody> login(@Query("phone") String phone);

}
