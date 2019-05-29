package com.example.dynamicalgoapi.webApi;

import com.example.dynamicalgoapi.models.ProfileRequest;
import com.example.dynamicalgoapi.models.ProfileResponse;
import com.example.dynamicalgoapi.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfileApi {

    @GET("api/personinfoes")
    Call<List<ProfileResponse>> getProfile(String aboutme, @Query("Aboutme") String keyword);

    @POST("api/personinfoes")
    Call<ProfileResponse> getProfileInfo(@Body ProfileRequest profileRequest);

    @GET("api/personinfoes?Aboutme=hello")
    Call<List<User>> getUserCollection ();

    @GET("api/personinfoes?")
    Call<List<User>> getContact(
            @Query("Aboutme") String keyword
    );
}
