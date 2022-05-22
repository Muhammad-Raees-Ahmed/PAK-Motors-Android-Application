package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {

    EditText input_fullname,input_age,input_email,input_password;
    Button btn_register;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        input_fullname = (EditText) findViewById(R.id.ed1_fullName);
        input_email = (EditText) findViewById(R.id.ed1_email);
        input_age = (EditText) findViewById(R.id.ed1_age);
        input_password = (EditText) findViewById(R.id.ed1_password);


        
        
        btn_register=findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register_User();
            }
        });
    }

    private void Register_User() {
        String fullName=input_fullname.getText().toString();
        String age=input_age.getText().toString();
        String email=input_email.getText().toString();
        String password=input_password.getText().toString();
        if (fullName.isEmpty()){
            input_fullname.setError("full name is required");
            input_fullname.requestFocus();
            return;
        }
        if (age.isEmpty()){
            input_age.setError("age is required");
            input_age.requestFocus();
            return;
        }
        if (email.isEmpty()){
            input_email.setError("email is required");
            input_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            input_email.setError("please provide valid email");
            input_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            input_password.setError("password is required");
            input_password.requestFocus();
            return;
        }
        if (password.length()<6){
            input_password.setError("password length should be 6 character");
            input_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Register_Activity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Register_Activity.this,MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(Register_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        input_fullname.setText("");
        input_age.setText("");
        input_email.setText("");
        input_password.setText("");

    }
}