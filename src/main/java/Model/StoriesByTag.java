package Model;

import java.util.ArrayList;

/**
 * Created by DELL on 7/1/2017.
 */
public class StoriesByTag {
    String tag;
    ArrayList<StoryDetails> stories;

    public StoriesByTag(String tag, ArrayList<StoryDetails> stories) {
        this.tag = tag;
        this.stories = stories;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<StoryDetails> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StoryDetails> stories) {
        this.stories = stories;
    }
}
