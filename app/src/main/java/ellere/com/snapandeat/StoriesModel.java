package ellere.com.snapandeat;

/**
 * Created by swikriti on 3/15/2020.
 */



public class StoriesModel {

    private String StoriesName;
    private int StoriesPic;

    public StoriesModel()
    {

    }
    public StoriesModel(String storiesName, int storiesPic) {
        StoriesName = storiesName;
        StoriesPic = storiesPic;
    }

    public String getStoriesName() {
        return StoriesName;
    }

    public void setStoriesName(String storiesName) {
        StoriesName = storiesName;
    }

    public int getStoriesPic() {
        return StoriesPic;
    }

    public void setStoriesPic(int storiesPic) {
        StoriesPic = storiesPic;
    }
}
