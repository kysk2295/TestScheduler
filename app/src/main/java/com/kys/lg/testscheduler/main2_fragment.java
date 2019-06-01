package com.kys.lg.testscheduler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class main2_fragment extends Fragment {

    ArrayList<UserModel> arrayList=new ArrayList<>();
   FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
   DatabaseReference databaseReference = firebaseDatabase.getReference().child("users");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout;

        //파이어베이스 데이터 꺼내기
       ValueEventListener eventListener= new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               arrayList.clear();
               for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                   UserModel userModel= snapshot.getValue(UserModel.class);
                   arrayList.add(userModel);


               }

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };

        return inflater.inflate(R.layout.activity_fragment_main2,container,false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
