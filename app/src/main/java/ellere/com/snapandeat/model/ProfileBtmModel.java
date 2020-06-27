package ellere.com.snapandeat.model;

/**
 * Created by DELL on 6/24/2020.
 */

public class ProfileBtmModel {
    int postedPictures;
    public ProfileBtmModel(){
    }

    public ProfileBtmModel(int postedPictures){
        this.postedPictures=postedPictures;
    }

    public  int getPostedPictures(){return postedPictures;}
    public void setPostedPictures(int postedPictures){
        this.postedPictures=postedPictures;
    }
}
