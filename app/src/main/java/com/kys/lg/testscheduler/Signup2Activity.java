package com.kys.lg.testscheduler;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class Signup2Activity extends AppCompatActivity {

    private Intent intent;
    private String email,password,confrim;
    private EditText school,name,age;
    private ImageView profile;
    private Uri imageurl;
    private static final int PICK_FROM_ALBUM = 10;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        profile = findViewById(R.id.Signup2Activity_imageview_profile);
        school = findViewById(R.id.Signup2Activity_edittext_school);
        name = findViewById(R.id.SignupAcitivity_edittext_name);
        age = findViewById(R.id.Signup2Acitivity_edittext_age);
        signup = findViewById(R.id.Signup2Activity_button_signup);

        intent = getIntent();

        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        confrim = intent.getStringExtra("confrim");

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(i, PICK_FROM_ALBUM);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")|| school.getText().toString().equals("")|| email.toString().equals("")||
                        password.toString().equals("")|| age.toString().equals("")){

                    Toast.makeText(Signup2Activity.this,"입력이 안된 값이 있습니다.",Toast.LENGTH_SHORT).show();

            }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), password.toString())
                        .addOnCompleteListener(Signup2Activity.this, new OnCompleteListener<AuthResult>() {

                            int x=1;

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                //이미지 파이어베이스에 업로드
                                final String uid = task.getResult().getUser().getUid();
                                FirebaseStorage.getInstance().getReference()
                                        .child("userimages").child(uid).putFile(imageurl)
                                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                String imageUrl = task.getResult().getDownloadUrl().toString();

                                                UserModel model = new UserModel();
                                                model.imageUrl = imageUrl;
                                                model.schoolname = school.getText().toString().trim();
                                                model.userage = age.getText().toString().trim();
                                                model.username = name.getText().toString().trim();
                                                model.passward=password.trim();


                                                FirebaseDatabase.getInstance().getReference()
                                                        .child("users").child("user"+x).child(uid)
                                                        .setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {


                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                       Intent i = new Intent(Signup2Activity.this,LoginActivity.class);
                                                       startActivity(i);

                                                    }
                                                });
                                                x++;

                                            }
                                        });


                            }
                        });


            }
        });


    }
                            @Override
                            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
                                    profile.setImageURI(data.getData());//가운데 뷰를 바꿈
                                    imageurl = data.getData(); //이미지경로 원본
                                }
                            }

                        }
