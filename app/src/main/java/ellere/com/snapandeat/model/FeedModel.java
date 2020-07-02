package ellere.com.snapandeat.model;

import ellere.com.snapandeat.Constants;

/**
 * Created by swikriti on 3/15/2020.
 */



public class FeedModel {

    private String name;
    private String time;
    private String likedBy;
    private String tags;
    private int likes;
    private int proPic;
    private int likerPic;
    private int uploaderPic;
    private String postPic;


    public FeedModel()
    {

    }

    public FeedModel(String name, String likedBy, String tags, int likes, int proPic, int likerPic, int uploaderPic, String postPic) {
        this.name= name;
        //this.time= time;
        this.likedBy = likedBy;
        this.tags = tags;
        this.likes = likes;
        this.proPic= proPic;
        this.likerPic = likerPic;
        this.uploaderPic = uploaderPic;
       this.postPic= postPic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(String likedBy) {
        this.likedBy = likedBy;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getProPic() {
        return proPic;
    }

    public void setProPic(int proPic) {
        this.proPic = proPic;
    }

    public int getLikerPic() {
        return likerPic;
    }

    public void setLikerPic(int likerPic) {
        this.likerPic = likerPic;
    }

    public int getUploaderPic() {
        return uploaderPic;
    }

    public void setUploaderPic(int uploaderPic) {
        this.uploaderPic = uploaderPic;
    }

    public String getPostPic() {
        return Constants.BASE_URL+postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }
}
