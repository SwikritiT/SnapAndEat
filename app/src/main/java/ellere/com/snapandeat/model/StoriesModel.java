package ellere.com.snapandeat.model;

/**
 * Created by swikriti on 3/15/2020.
 */



public class StoriesModel {

    private String storiesName;
    private int storiesPic;

    public StoriesModel()
    {

    }
    public StoriesModel(String storiesName, int storiesPic) {
        this.storiesName = storiesName;
        this.storiesPic = storiesPic;
    }

    public String getStoriesName() {
        return storiesName;
    }

    public void setStoriesName(String storiesName) {
        this.storiesName = storiesName;
    }

    public int getStoriesPic() {
        return storiesPic;
    }

    public void setStoriesPic(int storiesPic) {
        this.storiesPic = storiesPic;
    }
}
