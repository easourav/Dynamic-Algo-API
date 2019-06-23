package com.example.dynamicalgoapi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynamicalgoapi.models.User;
import com.example.dynamicalgoapi.webApi.ProfileApi;
import com.example.dynamicalgoapi.webApi.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    User users;
    TextView demoTv;

// fetchProfile(query);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressbar);

        users = new User();

        floatingActionButton = findViewById(R.id.fabBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.GONE);

    }

    private void fetchProfile(String s) {
        ProfileApi profileApi = RetrofitClient.getClient().create(ProfileApi.class);

        final Call<User> userCall = profileApi.getContact(s);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()){
                    users = response.body();

                    Toast.makeText(HomeActivity.this, "success "+users.getName(), Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(HomeActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
