package com.example.homepals_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class signup_page extends AppCompatActivity {
    String f_Name,l_Name, username, password,email,phone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup_page);

        EditText get_f_name = findViewById(R.id.get_f_name);
        get_f_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                f_Name = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText get_l_name = findViewById(R.id.get_l_name);
        get_l_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                l_Name = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText get_username = findViewById(R.id.get_username);
        get_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username =  s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText get_password =findViewById(R.id.get_password);
        get_password.addTextChangedListener(new TextWatcher() {
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

        EditText get_email =findViewById(R.id.get_email);
        get_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText get_phonenumber = findViewById(R.id.get_phonenumber);
        get_phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button signup_button = findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username == null || username.equals("")
                        || password == null || password.equals("")
                        || f_Name == null || f_Name.equals("")
                        || l_Name == null || l_Name.equals("")
                        || email == null || email.equals("")
                        || phone == null || phone.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(signup_page.this);
                    builder.setMessage("Please Enter Information")
                            .setTitle("Error")
                            .setPositiveButton("Ok", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (phone.length() != 10) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(signup_page.this);
                        builder.setMessage("Phone Number Must be 10 digits")
                                .setTitle("Error")
                                .setPositiveButton("Ok",null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else if (!Pattern.matches("[0-9]{10}", phone)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(signup_page.this);
                        builder.setMessage("Invalid Phone Number")
                                .setTitle("Error")
                                .setPositiveButton("Ok", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(signup_page.this);
                        builder.setMessage("Registration Successful!")
                                .setTitle("Success")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(signup_page.this, login_page.class);
                                        startActivity(intent);
                                    }
                                }).create().show();
                    }
                }
        });
    }
}