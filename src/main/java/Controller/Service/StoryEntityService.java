package Controller.Service;

import Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 5/13/2017.
 */
public interface StoryEntityService {
    List<StoryEntity> getAllStory();
    StoryEntity getStoryById(String storyid);
    List<ReadinglistEntity> getReadingListByUsername(String username);
    ReadingEntity storyReadByUser(String storyid, String username);
    ChapterEntity getChapterById(String storyid, String chapterid);
    long insertStoryComment(StorycommentsEntity storycommentsEntity);
    long insertChapterComment(ChaptercommentsEntity chaptercommentsEntity);
    void updateReading(String username, String chapterid,String storyid);
    void insertLikesEntity(LikesEntity likesEntity);
    void insertFollowEntity(FollowtableEntity follow);
    void deleteFollowEntity(FollowtableEntity follow);
    boolean storyLikedByUser(String username, String storyid);
    void deleteLikesEntity(LikesEntity likesEntity);
    ArrayList<ChapterEntity> getChapterTitlesByStoryId(String storyid);
    Boolean insertReadingEntity(ReadingEntity readingEntity);
    boolean writerFollowedByUser(String username, String writerid);
    void uploadFile(FilesUploadEntity uploadFile);


//***************************tamanna*****************************//

    int insertStory(StoryEntity story);
    int getChapterCount(int storyid);
    AccountuserEntity getWriter(String name);
    int getFirstChapter(int storyid);
    int getLastChapter(int storyid);
    boolean insertTag(StoryEntity newStory);
    List<StoryEntity> getSearchResult(String tagname);


}
