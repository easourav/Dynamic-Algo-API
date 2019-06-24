package com.example.dynamicalgoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dynamicalgoapi.models.ProfileRequest;
import com.example.dynamicalgoapi.models.ProfileResponse;
import com.example.dynamicalgoapi.webApi.ProfileApi;
import com.example.dynamicalgoapi.webApi.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    Button saveBtn, cencelBtn;
    ProgressBar dataSavePB;
    EditText nameEt, emailEt, aboutEt, nidET;
    String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\ +]{1,256}" +
            "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" + "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";

    ProfileApi profileApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        saveBtn = findViewById(R.id.btnSave);
        cencelBtn = findViewById(R.id.btnCancel);
        nameEt = findViewById(R.id.etName);
        emailEt = findViewById(R.id.etEmail);
        nidET = findViewById(R.id.etNid);
        aboutEt = findViewById(R.id.etAbout);
        dataSavePB = findViewById(R.id.pbDataSave);

        dataSavePB.setVisibility(View.INVISIBLE);

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
        dataSavePB.setVisibility(View.VISIBLE);

        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setName(nameEt.getText().toString());
        profileRequest.setEmail(emailEt.getText().toString());
        profileRequest.setNID(nidET.getText().toString());
        profileRequest.setAboutMe(aboutEt.getText().toString());
        Matcher matcher = Pattern.compile(emailPattern).matcher(emailEt.getText().toString().trim());

        if (nameEt.length() == 0) {
            nameEt.setError("Input Name");

        } else if (emailEt.length() == 0) {
            emailEt.setError("Input Email");
        } else if (!matcher.matches()) {
            emailEt.setError("Input valid Email");
        } else if (nidET.length() == 0) {
            nidET.setError("Input valid Email");
        } else if (aboutEt.length() == 0) {
            aboutEt.setError("Input about");
        } else {
            Call<ProfileResponse> profileResponseCall = profileApi.getProfileInfo(profileRequest);
            profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    if (response.isSuccessful()) {

                        ProfileResponse profileResponse = response.body();
                        profileResponse.getName();
                        nameEt.setText(null);
                        emailEt.setText(null);
                        aboutEt.setText(null);
                        nidET.setText(null);
                        Toast.makeText(FormActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        dataSavePB.setVisibility(View.INVISIBLE);
                    }

                }


                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    dataSavePB.setVisibility(View.INVISIBLE);
                    String errorMessage =t.getMessage();
                    //String s = String.valueOf(errorMessage);
                    String s = "java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 2 path $";
                    if (s.equals(t.getMessage())){
                        Toast.makeText(FormActivity.this, "Email already exist", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(FormActivity.this, "Error: "+errorMessage, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

}
