package ellere.com.snapandeat.model;

/**
 * Created by DELL on 6/24/2020.
 */

public class ProfileTopModel {
    private int profilePic;
    private String userName;
    private String noOfPosts;
    private String noFollowers;
    private  String noFollowing;
    public ProfileTopModel(){}
    public ProfileTopModel(int profilePic,String userName,String noOfPosts, String noFollowers, String noFollowing){
        this.profilePic=profilePic;
        this.userName=userName;
        this.noOfPosts=noOfPosts;
        this.noFollowers=noFollowers;
        this.noFollowing=noFollowing;

    }
    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getNoOfPosts(){

        return noOfPosts;
    }

    public void setNoOfPosts(String noOfPosts){

        this.noOfPosts=noOfPosts;
    }

    public int getProfilePic() {

        return profilePic;
    }

    public void setProfilePic(int profilePic) {

        this.profilePic =profilePic;
    }
    public  String getNoFollowers(){

        return noFollowers;
    }

    public void setNoFollowers(String noFollowers){

        this.noFollowers=noFollowers;
    }
    public  String getNoFollowing(){

        return noFollowing;
    }
    public void setNoFollowing(String noFollowing){

        this.noFollowing=noFollowing;
    }

}
