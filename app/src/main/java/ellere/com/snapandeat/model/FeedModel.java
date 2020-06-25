package ellere.com.snapandeat.model;

/**
 * Created by swikriti on 3/15/2020.
 */



public class FeedModel {

    private String Name;
    private String Time;
    private String LikedBy;
    private String Tags;

    private int Likes;
    private int ProPic;
    private int LikerPic;
    private int UploaderPic;
    private int PostPic;


    public FeedModel()
    {

    }

    public FeedModel(String name, String time, String likedBy, String tags, int likes, int proPic, int likerPic, int uploaderPic, int postPic) {
        Name = name;
        Time = time;
        LikedBy = likedBy;
        Tags = tags;
        Likes = likes;
        ProPic = proPic;
        LikerPic = likerPic;
        UploaderPic = uploaderPic;
        PostPic = postPic;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLikedBy() {
        return LikedBy;
    }

    public void setLikedBy(String likedBy) {
        LikedBy = likedBy;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public int getProPic() {
        return ProPic;
    }

    public void setProPic(int proPic) {
        ProPic = proPic;
    }

    public int getLikerPic() {
        return LikerPic;
    }

    public void setLikerPic(int likerPic) {
        LikerPic = likerPic;
    }

    public int getUploaderPic() {
        return UploaderPic;
    }

    public void setUploaderPic(int uploaderPic) {
        UploaderPic = uploaderPic;
    }

    public int getPostPic() {
        return PostPic;
    }

    public void setPostPic(int postPic) {
        PostPic = postPic;
    }
}
