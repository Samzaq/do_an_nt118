package com.example.desperate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupPhone, signupPassword, confirmPassword;
    Button redirectButton;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        signupName = findViewById(R.id.name);
        signupEmail = findViewById(R.id.email);
        signupPhone = findViewById(R.id.phone);
        signupPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.repassword);
        signupButton = findViewById(R.id.btnregis);
        redirectButton = findViewById(R.id.register);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword() | !validatePhone() | !validateEmail() | !validateConfirmPassword()){
                } else {
                    String name = signupName.getText().toString();
                    String email = signupEmail.getText().toString();
                    String phone = signupPhone.getText().toString();
                    String password = signupPassword.getText().toString();
                    String cpassword = confirmPassword.getText().toString();
                    if (!(password.equals(cpassword))) {
                        confirmPassword.setError("Password doesn't match");
                    } else {
                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference("users");

                        HelperClass helperClass = new HelperClass(name, email, phone, password);
                        reference.child(name).setValue(helperClass);

                        Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername(){
        String val = signupName.getText().toString();
        if (val.isEmpty()){
            signupName.setError("Username cannot be empty");
            return false;
        } else {
            signupName.setError(null);
            return true;
        }
    }

    public Boolean validateEmail(){
        String val = signupEmail.getText().toString();
        if (val.isEmpty()){
            signupEmail.setError("Email cannot be empty");
            return false;
        } else {
            signupEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePhone(){
        String val = signupPhone.getText().toString();
        if (val.isEmpty()){
            signupPhone.setError("Mobile number cannot be empty");
            return false;
        } else {
            signupPhone.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = signupPassword.getText().toString();
        if (val.isEmpty()){
            signupPassword.setError("Password cannot be empty");
            return false;
        } else {
            signupPassword.setError(null);
            return true;
        }
    }

    public Boolean validateConfirmPassword(){
        String val = confirmPassword.getText().toString();
        if (val.isEmpty()){
            confirmPassword.setError("Password cannot be empty");
            return false;
        } else {
            confirmPassword.setError(null);
            return true;
        }
    }
}