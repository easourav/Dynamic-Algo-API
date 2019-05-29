package com.example.dynamicalgoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dynamicalgoapi.models.ProfileRequest;
import com.example.dynamicalgoapi.models.ProfileResponse;
import com.example.dynamicalgoapi.webApi.ProfileApi;
import com.example.dynamicalgoapi.webApi.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button saveBtn, cencelBtn;
    EditText nameEt, emailEt, aboutEt;

    ProfileApi profileApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveBtn = findViewById(R.id.btnSave);
        cencelBtn = findViewById(R.id.btnCancel);
        nameEt = findViewById(R.id.etName);
        emailEt = findViewById(R.id.etEmail);
        aboutEt = findViewById(R.id.etAbout);

        profileApi = RetrofitClient.getClient().create(ProfileApi.class);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }
        });

        cencelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void createPost() {
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setName(nameEt.getText().toString());
        profileRequest.setEmail(emailEt.getText().toString());
        profileRequest.setAboutMe(aboutEt.getText().toString());

        if(nameEt.length()==0){
            nameEt.setError("Input Name");

        }
        else if (emailEt.length()==0){
            emailEt.setError("Input Email");
        }
        else if (aboutEt.length()==0){
            aboutEt.setError("Input about");
        }
        else {
            Call<ProfileResponse> profileResponseCall = profileApi.getProfileInfo(profileRequest);
            profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    //ProfileResponse profileResponse = response.body();
                    nameEt.setText(null);
                    emailEt.setText(null);
                    aboutEt.setText(null);
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

}
