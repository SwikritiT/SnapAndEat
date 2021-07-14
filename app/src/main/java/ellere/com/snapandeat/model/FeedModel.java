package ellere.com.snapandeat.model;

import ellere.com.snapandeat.Constants;

/**
 * Created by swikriti on 3/15/2020.
 */



public class FeedModel {

    private String name;
    private String likedBy;
    private String pizzaPrediction;
    private String calorie;
    private int likes;
    private int proPic;
    private int likerPic;
    private int uploaderPic;
    private String postPic;


    public FeedModel()
    {

    }

    public FeedModel(String name, String likedBy, String pizzaPrediction, int likes, int proPic, int likerPic, int uploaderPic, String postPic,String calorie) {
        this.name= name;
        this.likedBy = likedBy;
        this.pizzaPrediction = pizzaPrediction;
        this.likes = likes;
        this.proPic= proPic;
        this.likerPic = likerPic;
        this.uploaderPic = uploaderPic;
       this.postPic= postPic;
       this.calorie= calorie;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setTime(String calorie) {
        this.calorie = calorie;
    }

    public String getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(String likedBy) {
        this.likedBy = likedBy;
    }

    public String getPizzaPrediction() {
        return pizzaPrediction;
    }

    public void setPizzaPrediction(String pizzaPrediction) {
        this.pizzaPrediction = pizzaPrediction;
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
