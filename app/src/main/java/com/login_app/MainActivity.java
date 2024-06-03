package com.login_app;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView passwordhide,passwordshow;
    EditText username,password;
    TextView account;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        passwordhide = findViewById(R.id.main_pass_hide);
        passwordshow = findViewById(R.id.main_pass_show);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        account = findViewById(R.id.dont_have_account);

        account.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        passwordshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordshow.setVisibility(View.GONE);
                passwordhide.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);


            }
        });
        passwordhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordshow.setVisibility(View.VISIBLE);
                passwordhide.setVisibility(View.GONE);
                password.setTransformationMethod(new PasswordTransformationMethod());

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("")){
                    username.setError("Username required");
                }
                else if(password.getText().toString().trim().equals(""))
                {
                    password.setError("password is required");
                }
                else if(password.getText().toString().trim().length()<6)
                {
                    password.setError("Min 6 Char is required");
                }
                else{
                    System.out.println("Login Successfully!");
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        };
    }

