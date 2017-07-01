package Model;

import java.util.ArrayList;

/**
 * Created by DELL on 7/2/2017.
 */
public class StoriesByWriter {
    String writerid;
    UserprofileEntity userprofileEntity;
    ArrayList<StoryEntity> storyEntities;

    public StoriesByWriter(String writerid, UserprofileEntity userprofileEntity, ArrayList<StoryEntity> storyEntities) {
        this.writerid = writerid;
        this.userprofileEntity = userprofileEntity;
        this.storyEntities = storyEntities;
    }

    public String getWriterid() {
        return writerid;
    }

    public void setWriterid(String writerid) {
        this.writerid = writerid;
    }

    public UserprofileEntity getUserprofileEntity() {
        return userprofileEntity;
    }

    public void setUserprofileEntity(UserprofileEntity userprofileEntity) {
        this.userprofileEntity = userprofileEntity;
    }

    public ArrayList<StoryEntity> getStoryEntities() {
        return storyEntities;
    }

    public void setStoryEntities(ArrayList<StoryEntity> storyEntities) {
        this.storyEntities = storyEntities;
    }
}
