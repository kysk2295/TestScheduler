package com.kys.lg.testscheduler;

public class Board {

    int Bid,likes,comments,propic,postpic;
    String Bname,Btime,status;


    public Board(int bid, int likes, int comments, int propic, int postpic, String bname, String btime, String status) {
        this.Bid = bid;
        this.likes = likes;
        this.comments = comments;
        this.propic = propic;
        this.postpic = postpic;
        this.Bname = bname;
        this.Btime = btime;
        this.status = status;
    }

    public int getBid() {
        return Bid;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getPropic() {
        return propic;
    }

    public int getPostpic() {
        return postpic;
    }

    public String getBname() {
        return Bname;
    }

    public String getBtime() {
        return Btime;
    }

    public String getStatus() {
        return status;
    }
}
