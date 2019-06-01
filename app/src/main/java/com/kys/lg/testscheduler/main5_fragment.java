package com.kys.lg.testscheduler;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class main5_fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Board> boardsArrayList= new ArrayList<>();
    RecyclerviewAdapter recyclerviewAdapter;
    FloatingActionButton fab1;
    PhotoViewAttacher photoViewAttacher;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.activity_fragment_main5,container,false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        fab1=(FloatingActionButton)view.findViewById(R.id.fab_write);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),WritingActivity.class);
                startActivityForResult(i,100);



            }
        });

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerviewAdapter= new RecyclerviewAdapter(getActivity(), boardsArrayList);
        recyclerView.setAdapter(recyclerviewAdapter);


        PopulateRecyclerView();

        return view;
    }

    public void PopulateRecyclerView(){
        Board board= new Board(1,5,2,R.drawable.ic_propic1,R.drawable.img_post1,"ko yun seo","30 min","Studty hardly");
        recyclerviewAdapter.add(board);
        Board board1= new Board(2,57,10,R.drawable.ic_propic2,0,"lfsdkjw","2 hrs","Happiness is a hard thing because it is achieved only by making others happy.  ");
        recyclerviewAdapter.add(board1);
        Board board2= new Board(3,1002,876,R.drawable.ic_propic1,R.drawable.img_post2,"helen keler","11 hrs","Many persons have a wrong idea of what constitutes real happiness.\n" +
                "It is not obtained through self-gratification,\n" +
                "but through fidelity to a worthy purpose. ");
        recyclerviewAdapter.add(board2);

        recyclerviewAdapter.notifyDataSetChanged();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==100) {
            Board board5 = new Board(14, 18, 16, R.drawable.ic_propic1, 0, "" + data.getStringExtra("title"), "4 hrs", "" + data.getStringExtra("content"));
            recyclerviewAdapter.add(board5);


        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
