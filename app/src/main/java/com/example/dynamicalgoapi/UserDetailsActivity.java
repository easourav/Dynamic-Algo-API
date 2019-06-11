package com.example.dynamicalgoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {
    TextView nameTv,emailTv, detailsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        nameTv=findViewById(R.id.nameTV);
        emailTv=findViewById(R.id.emailTV);
        detailsTv=findViewById(R.id.detailsTV);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String about = getIntent().getStringExtra("about");

        nameTv.setText(name);
        emailTv.setText(email);
        detailsTv.setText(about);
    }
}
