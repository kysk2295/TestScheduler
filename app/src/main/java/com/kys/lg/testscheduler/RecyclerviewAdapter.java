package com.kys.lg.testscheduler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Board> boardArrayList= new ArrayList<>();
    RequestManager glide;

    public RecyclerviewAdapter(Context context, ArrayList<Board> boardArrayList){

        this.context=context;
        this.boardArrayList=boardArrayList;
        glide= Glide.with(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_recyclerview,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Board board= boardArrayList.get(position);

        holder.tv_comment.setText(board.getComments()+"comments");
        holder.tv_time.setText(board.getBtime());
        holder.tv_status.setText(board.getStatus());
        holder.tv_name.setText(board.getBname());
        holder.tv_likes.setText(String.valueOf(board.getLikes()));


        //sns 이미지 없을 때와 있을 때
        glide.load(board.getPropic()).into(holder.imgview_propic);
        if (board.getPostpic() == 0) {
            holder.imgview_postpic.setVisibility(View.GONE);

        }else{
            holder.imgview_postpic.setVisibility(View.VISIBLE);
            glide.load(board.getPostpic()).into(holder.imgview_postpic);

        }
    }

    @Override
    public int getItemCount() {
        return boardArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comment,tv_name,tv_time,tv_likes,tv_status;
        ImageView imgview_propic,imgview_postpic;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgview_propic=(ImageView)itemView.findViewById(R.id.imageview_proPic);
            imgview_postpic=(ImageView)itemView.findViewById(R.id.tv_image);

            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_likes=(TextView)itemView.findViewById(R.id.tv_like);
            tv_status=(TextView)itemView.findViewById(R.id.tv_status);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time);
            tv_comment=(TextView)itemView.findViewById(R.id.tv_comment);

        }
    }

    //클래스를 묶어서 어댑터에 넣어준 후 이 어댑터 클래스에서 arraylist에 추가해준다.
       public void add(Board board){
        boardArrayList.add(board);
    }
}
