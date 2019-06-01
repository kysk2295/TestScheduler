package com.kys.lg.testscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

  Button next;
    EditText email,password,confrim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.SignupActivity_edittext_email);
        password=findViewById(R.id.SignupAcitivity_edittext_password);
        confrim=findViewById(R.id.SignupAcitivity_edittext_confrimpassword);
        next=findViewById(R.id.SignupActivity_button_next);


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(password.getText().toString().trim().equals(confrim.getText().toString().trim()) &&
                            !(password.getText().toString().trim()==null) && !(email.getText().toString()==null)) {
                        Intent i = new Intent(SignupActivity.this, Signup2Activity.class);
                        i.putExtra("email", email.getText().toString());
                        i.putExtra("password", password.getText().toString());
                        i.putExtra("confrim", confrim.getText().toString());
                        startActivity(i);
                    }
                    else{
                            Toast.makeText(SignupActivity.this,"비밀번호가 같지 않습니다.",Toast.LENGTH_SHORT).show();
                            return;
                        }
                }
            });
        }
    }

