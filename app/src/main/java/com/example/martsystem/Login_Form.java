package com.example.martsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Form extends AppCompatActivity {
    EditText txtfullname;
    EditText txtuser;
    EditText txtEmail;
    EditText txtpassword;
    EditText txtconfirmpass;
    Button bt_register;

    private FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Registration Form");

        txtfullname=findViewById(R.id.fullname);
        txtuser=findViewById(R.id.username);
        txtEmail=findViewById(R.id.email);
        txtpassword = findViewById(R.id.Password);
        txtconfirmpass= findViewById(R.id.confirmpassword);
        bt_register=findViewById(R.id.btn_register);


        firebaseAuth= FirebaseAuth.getInstance();

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname=txtfullname.getText().toString().trim();
                String username=txtuser.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                String confirmpass=txtconfirmpass.getText().toString().trim();



                if (TextUtils.isEmpty(fullname)) {

                    Toast.makeText(Login_Form.this, "Please Enter Fullname", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(username)) {

                    Toast.makeText(Login_Form.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(Login_Form.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(confirmpass)) {

                    Toast.makeText(Login_Form.this, "Please Confirm password", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (password.length() < 6) {
                    Toast.makeText(Login_Form.this, "Password Too Short", Toast.LENGTH_SHORT).show();

                }

                if (password.equals(confirmpass)){

                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        //new MainActivity(new Intent(getApplicationContext(), MainActivity.class));

                                        Toast.makeText(Login_Form.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Login_Form.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }

    }
