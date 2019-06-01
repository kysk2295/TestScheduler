package com.kys.lg.testscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WritingActivity extends AppCompatActivity {

    EditText title,content;
    Button writie_btn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        title=findViewById(R.id.writingactivity_edittext_title);
        content=findViewById(R.id.writingactivity_edittext_content);
        writie_btn=findViewById(R.id.writing_btn);

        writie_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("title",title.getText().toString());
                i.putExtra("content",content.getText().toString());
                setResult(100,i);

                finish();

                ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPosition(0);


            }
        });


    }
}
