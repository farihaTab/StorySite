package Controller.DAO;

import Model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 5/13/2017.
 */
public interface StoryEntityRepository {
    List<StoryEntity> getAllStory();
    StoryEntity getStoryById(String storyid);
    List<ReadinglistEntity> getReadingListByUsername(String username);
    ReadingEntity storyReadByUser(String storyid, String username);
    Boolean insertReadingEntity(ReadingEntity readingEntity);
    ChapterEntity getChapterById(String storyid, String chapterid);
    long insertStoryComment(StorycommentsEntity storycommentsEntity);
    long insertChapterComment(ChaptercommentsEntity chaptercommentsEntity);
    void updateReading(String username, String chapterid, String storyid);
    void insertLikesEntity(LikesEntity likesEntity);
    boolean storyLikedByUser(String username, String storyid);
    boolean writerFollowedByUser(String username, String writerid);
    void deleteLikesEntity(LikesEntity likesEntity);
    ArrayList<ChapterEntity> getChapterTitlesByStoryId(String storyid);
    void insertFollowEntity(FollowtableEntity follow);
    void deleteFollowEntity(FollowtableEntity follow);
    void uploadFile(FilesUploadEntity uploadFile);



    //******************tamanna***********************//



    int insertStory(StoryEntity story);
    int getChapterCount(int storyid);
    int getFirstChapter(int storyid);
    AccountuserEntity getWriter(String name);

    int getLastChapter(int storyid);
    boolean insertTag(StoryEntity newStory);
    List<StoryEntity> getSearchResult(String tagname);


}
