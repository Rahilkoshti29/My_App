package com.login_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText username,contact,email,password,confirmpassword;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    CheckBox term;
    Button signup;

    ImageView rpasshide,rpassshow;
    ImageView rconpasshide,rconpassshow;

    SQLiteDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        db = openOrCreateDatabase("MyLoginApp.db",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT ,USERNAME VARCHAR(100),CONTACT BIGINT(10),EMAIL VARCHAR(100),PASSWORD VARCHAR(100))";
        db.execSQL(tableQuery);

        username = findViewById(R.id.register_username);
        contact = findViewById(R.id.register_contact);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirmpassword = findViewById(R.id.register_confirm_password);
        term = findViewById(R.id.register_term);
        signup = findViewById(R.id.register_button);
        rpasshide = findViewById(R.id.register_pass_hide);
        rpassshow = findViewById(R.id.register_pass_show);
        rconpasshide = findViewById(R.id.register_con_pass_hide);
        rconpassshow = findViewById(R.id.register_con_pass_show);

        rpassshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassshow.setVisibility(View.GONE);
                rpasshide.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);
            }
        });
        rpasshide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpassshow.setVisibility(View.VISIBLE);
                rpasshide.setVisibility(View.GONE);
                password.setTransformationMethod(new PasswordTransformationMethod());

            }
        });
        rconpassshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rconpassshow.setVisibility(View.GONE);
                rconpasshide.setVisibility(View.VISIBLE);
                confirmpassword.setTransformationMethod(null);
            }
        });
        rconpasshide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rconpassshow.setVisibility(View.VISIBLE);
                rconpasshide.setVisibility(View.GONE);
                confirmpassword.setTransformationMethod(new PasswordTransformationMethod());

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (password.getText().toString().trim().equals("")) {
                            password.setError("Pass required");


                        } else if (password.getText().toString().length() < 6) {
                            password.setError("Min 6 char required ");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                confirmpassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (confirmpassword.getText().toString().equals("")) {
                            confirmpassword.setError("Confirm password");
                        } else if (!password.getText().toString().trim().matches(confirmpassword.getText().toString())) {
                            confirmpassword.setError("Pass does not match");

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                if (username.getText().toString().trim().equals("")) {
                    username.setError("username required");

                } else if (email.getText().toString().trim().equals("")) {
                    email.setError("Email id required");

                } else if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Valid emailid required");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("password is required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 char is required");
                } else if (confirmpassword.getText().toString().trim().equals("")) {
                    confirmpassword.setError("password is required");
                } else if (confirmpassword.getText().toString().trim().length() < 6) {
                    confirmpassword.setError("Min. 6 char is required");
                } else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact no. required");

                } else if (!term.isChecked()) {
                    term.setError("Please select the terms & conditions");
                }
                else{
                        String insertQuery = "INSERT INTO USERS(NULL,'" + username.getText().toString() + "','" + contact.getText().toString() + "','" + email.getText().toString() + "','" + password.getText().toString() + "')";
                        db.execSQL(insertQuery);
                        Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }

            }
});
    }
    }
