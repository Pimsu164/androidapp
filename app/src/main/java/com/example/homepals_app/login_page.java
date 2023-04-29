package com.example.homepals_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class login_page extends AppCompatActivity {

    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);


        EditText userName= findViewById(R.id.userName);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText input_password = findViewById(R.id.input_password);
        input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username == null || username.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(login_page.this);
                    builder.setMessage("Please Enter Username")
                            .setTitle("Error")
                            .setPositiveButton("Ok",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (password == null || password.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(login_page.this);
                    builder.setMessage("Please Enter Password")
                            .setTitle("Error")
                            .setPositiveButton("Ok",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else Log.d("login_page","OK"+username+" "+password);
            }
        });


        Button go_signup = findViewById(R.id.go_signup);
        go_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, signup_page.class);
                startActivity(intent);

            }
        });

    }
}