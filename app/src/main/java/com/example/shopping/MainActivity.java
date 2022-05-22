package com.example.shopping;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shopping.ApplicationProgrammingInterface.Adapter;
import com.example.shopping.ApplicationProgrammingInterface.DATA;
import com.example.shopping.ApplicationProgrammingInterface.JPH_API;
import com.example.shopping.ApplicationProgrammingInterface.Model_Class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn_signin,btn_signup;
    EditText edt_email,edt_password;
    View view;
    private FirebaseAuth mAuth;

    LinearLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();


        edt_email=findViewById(R.id.ed_email);
        edt_password=findViewById(R.id.ed_password);

         view=findViewById(R.id.coordinatorLayout);

        btn_signup=findViewById(R.id.sign_up);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,Register_Activity.class);
                startActivity(intent);
            }
        });

        btn_signin=findViewById(R.id.Login);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_User();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(MainActivity.this,homeActivity.class);
            startActivity(intent);
        }
    }

    private void reload() {
    }

    private void Login_User() {
        String L_email=edt_email.getText().toString();
        String L_password=edt_password.getText().toString();
        if (L_email.isEmpty()){
            edt_email.setError("email is required");
            edt_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(L_email).matches()){
            edt_email.setError("please provide valid email");
            edt_email.requestFocus();
            return;
        }
        if (L_password.isEmpty()){
            edt_password.setError("password is required");
            edt_password.requestFocus();
            return;
        }
        if (L_password.length()<6){
            edt_password.setError("password length should be 6 character");
            edt_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(L_email, L_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,homeActivity.class);
                            startActivity(intent);



                        } else {
                            Snackbar snackbar = Snackbar.make(view, "Invalid email or password  or internet issue ", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setDuration(5000);snackbar.show();
                            // If sign in fails, display a message to the user.
//                            Log.w("TAG", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}