package Model;

import java.util.ArrayList;

/**
 * Created by DELL on 7/1/2017.
 */
public class StoryDetails {

    StoryEntity storyEntity;
    UserprofileEntity userprofileEntity;
    ArrayList<String> tags;


    public StoryDetails(StoryEntity storyEntity, UserprofileEntity userprofileEntity, ArrayList<String> tags) {
        this.storyEntity = storyEntity;
        this.userprofileEntity = userprofileEntity;
        this.tags = tags;
    }

    public StoryEntity getStoryEntity() {
        return storyEntity;
    }

    public void setStoryEntity(StoryEntity storyEntity) {
        this.storyEntity = storyEntity;
    }

    public UserprofileEntity getUserprofileEntity() {
        return userprofileEntity;
    }

    public void setUserprofileEntity(UserprofileEntity userprofileEntity) {
        this.userprofileEntity = userprofileEntity;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
