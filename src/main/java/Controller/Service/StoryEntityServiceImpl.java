package Controller.Service;

import Controller.DAO.StoryEntityRepository;
import Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 5/13/2017.
 */
@Service
public class StoryEntityServiceImpl implements StoryEntityService {
    @Autowired
    StoryEntityRepository storyEntityRepository;

    @Override
    public void uploadFile(FilesUploadEntity uploadFile) {
        storyEntityRepository.uploadFile(uploadFile);
    }

    @Override
    public List<StoryEntity> getAllStory() {
        return storyEntityRepository.getAllStory();
        //return null;
    }

    @Override
    public List<ReadinglistEntity> getReadingListByUsername(String username){
        return storyEntityRepository.getReadingListByUsername(username);
    }

    @Override
    public ReadingEntity storyReadByUser(String storyid, String username) {
        return storyEntityRepository.storyReadByUser(storyid,username);
    }

    @Override
    public StoryEntity getStoryById(String storyid) {
        return storyEntityRepository.getStoryById(storyid);
    }

    @Override
    public Boolean insertReadingEntity(ReadingEntity readingEntity) {
        return storyEntityRepository.insertReadingEntity(readingEntity);
    }

    @Override
    public ChapterEntity getChapterById(String storyid, String chapterid) {
        return storyEntityRepository.getChapterById(storyid,chapterid);
    }

    @Override
    public void updateReading(String username, String chapterid,String storyid) {
        storyEntityRepository.updateReading(username,chapterid,storyid);
    }

    @Override
    public void insertLikesEntity(LikesEntity likesEntity) {
        storyEntityRepository.insertLikesEntity(likesEntity);
    }

    @Override
    public void insertFollowEntity(FollowtableEntity follow) {
        storyEntityRepository.insertFollowEntity(follow);
    }

    @Override
    public void deleteFollowEntity(FollowtableEntity follow) {
        storyEntityRepository.deleteFollowEntity(follow);
    }

    @Override
    public void deleteLikesEntity(LikesEntity likesEntity) {
        storyEntityRepository.deleteLikesEntity(likesEntity);
    }

    @Override
    public long insertStoryComment(StorycommentsEntity storycommentsEntity) {
        return storyEntityRepository.insertStoryComment(storycommentsEntity);
    }

    @Override
    public long insertChapterComment(ChaptercommentsEntity chaptercommentsEntity) {
        return storyEntityRepository.insertChapterComment(chaptercommentsEntity);
    }

    @Override
    public boolean storyLikedByUser(String username, String storyid) {
        return storyEntityRepository.storyLikedByUser(username,storyid);
    }

    @Override
    public boolean writerFollowedByUser(String username, String writerid) {
        return storyEntityRepository.writerFollowedByUser(username,writerid);
    }

    @Override
    public ArrayList<ChapterEntity> getChapterTitlesByStoryId(String storyid) {
        return storyEntityRepository.getChapterTitlesByStoryId(storyid);
    }





//*******************************tamanna****************************************************//
    @Override
    public int insertStory(StoryEntity story) {
        return storyEntityRepository.insertStory(story);
    }

    @Override
    public int getChapterCount(int storyid) {
        return storyEntityRepository.getChapterCount(storyid);
    }

    @Override
    public AccountuserEntity getWriter(String name) {
        return storyEntityRepository.getWriter(name);
    }

    @Override
    public int getFirstChapter(int storyid) {
        return storyEntityRepository.getFirstChapter(storyid);
    }

    @Override
    public int getLastChapter(int storyid) {
        return storyEntityRepository.getLastChapter(storyid);
    }

    @Override
    public boolean insertTag(StoryEntity newStory) {
        return storyEntityRepository.insertTag(newStory);
    }

    @Override
    public List<StoryEntity> getSearchResult(String tagname) {
        return storyEntityRepository.getSearchResult(tagname);
    }

}
