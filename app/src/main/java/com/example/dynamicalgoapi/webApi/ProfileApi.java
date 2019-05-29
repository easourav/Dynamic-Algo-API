package com.example.dynamicalgoapi.webApi;

import com.example.dynamicalgoapi.models.ProfileRequest;
import com.example.dynamicalgoapi.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileApi {
    @POST("api/personinfoes")
    Call<ProfileResponse> getProfileInfo(@Body ProfileRequest profileRequest);
}
