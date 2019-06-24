package com.example.dynamicalgoapi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynamicalgoapi.models.User;
import com.example.dynamicalgoapi.webApi.ProfileApi;
import com.example.dynamicalgoapi.webApi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    //User users;
    TextView nameTv, emailTv;
    EditText searchET;
    Button searchBtn;
    ProfileApi profileApi;

// fetchProfile(query);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressbar);

        nameTv = findViewById(R.id.tvName);
        emailTv = findViewById(R.id.tvEmail);

        searchET = findViewById(R.id.etSearch);
        searchBtn = findViewById(R.id.btnSearch);
        floatingActionButton = findViewById(R.id.fabBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        progressBar.setVisibility(View.INVISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchET.getText().toString();
                if (searchET.length() == 0) {
                    searchET.setError("Input NID");

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    fetchProfile(query);
                }

            }
        });

    }

    private void fetchProfile(String s) {

        profileApi = RetrofitClient.getClient().create(ProfileApi.class);

        final Call<List<User>> userCall = profileApi.getContact(s);
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()){
                   List<User> users = response.body();
                    if (users.size()==0){
                        Toast.makeText(HomeActivity.this, "User not found", Toast.LENGTH_LONG).show();
                        nameTv.setText(null);
                        emailTv.setText(null);
                    }else {
                        for (int i = 0; i<users.size();i++){
                            String userName = users.get(i).getName();
                            String userEmail = users.get(i).getEmail();

                            nameTv.setText(userName);
                            emailTv.setText(userEmail);
                        }
                    }



                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(HomeActivity.this, "error "+response.code(), Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(HomeActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(HomeActivity.this, "Err3or: "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
