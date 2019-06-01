package com.kys.lg.testscheduler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button signup,login;
    private TextView forget;
    private CheckBox remember;
    private EditText email_login,passward_login;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.LoginActivity_button_signup);
        login=findViewById(R.id.LoginActivity_button_login);
        forget=findViewById(R.id.LoginActivity_textview_forget);
        remember=findViewById(R.id.LoginActivity_checkbox_remember);
        email_login=findViewById(R.id.LoginActivity_edittext_loginemail);
        passward_login=findViewById(R.id.LoginActivity_edittext_loginpassward);
        dialog=new ProgressDialog(this);
        dialog.setMessage("로그인중...");
        dialog.setCancelable(false);

        firebaseAuth= FirebaseAuth.getInstance();


        //체크박스가 클릭되면 다음에 이용할 때 로그인 할 필요가 없음.
        if(remember.isChecked()) {


        }
        else{
            firebaseAuth.signOut();
        }

        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
                    loginevent();

                }
            });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    dialog.dismiss();
                    finish();
                }else{
                    //로그아웃
                }
            }
        };


    }
    void loginevent(){
        firebaseAuth.signInWithEmailAndPassword(email_login.getText().toString(),passward_login.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!(task.isSuccessful())){
                            Toast.makeText(LoginActivity.this,"로그인 오류",Toast.LENGTH_SHORT).show();

                        }else {

                        }

                    }
                });

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

}
