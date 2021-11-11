package com.example.realtimedatabasefirebase_197pm31152;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
EditText email, pw;
Button signup, signin, cancel, forget;
TextView error;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        matching();
        auth =FirebaseAuth.getInstance();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = email.getText().toString().trim();
                String spw = pw.getText().toString().trim();
                if (TextUtils.isEmpty(semail)) {
                    error.setText("Hãy Nhập email");
                    return;
                }
                if (spw.length() <= 6) {
                    error.setText("Password quá ngắn ");
                    return;
                }
                auth.createUserWithEmailAndPassword(semail, spw).addOnCompleteListener(SignUpActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    error.setText(task.getException().getMessage().toString());
                                } else {
                                    Toast.makeText(getApplicationContext(), "ĐĂNG KÍ THÀNH CÔNG" , Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void matching() {
        email = (EditText) findViewById(R.id.register_et_email);
        pw = (EditText) findViewById(R.id.register_et_pw);
        signin = (Button) findViewById(R.id.register_btn_signin);
        signup = (Button) findViewById(R.id.register_btn_register);
        cancel = (Button) findViewById(R.id.register_btn_cancel);
        forget = (Button) findViewById(R.id.register_btn_forget);
        error = (TextView) findViewById(R.id.register_tv_error);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}