package com.kys.lg.testscheduler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tl;
    private Toolbar toolbar;
    private Context context;

    RecyclerView recyclerView;
    ArrayList<Board> boardsArrayList= new ArrayList<>();
    RecyclerviewAdapter recyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp=findViewById(R.id.vp);
        tl=findViewById(R.id.tabs);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setTitle("Test Scheduler");



        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager());
        //getSupportFragmentManager()
        //액티비티내의 모든 fragment에 접근 가능하게 해준다.
        vp.setAdapter(pagerAdapter);

        tl.setupWithViewPager(vp);
        vp.setCurrentItem(0);


    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Test Scheduler");

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setMessage("종료하시겠습니까");
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

}
