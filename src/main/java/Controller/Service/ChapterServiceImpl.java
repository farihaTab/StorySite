package Controller.Service;

import Controller.DAO.ChapterDao;
import Model.ChapterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by MiNNiE on 29-Apr-17.
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao chapterDao;

    public ChapterEntity getChapterByFilter(Map<String, List<String>> filterParams) {
        try {
            return chapterDao.getChapterByFilter((filterParams));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ChapterEntity getNewChapterByFilter(Map<String, List<String>> filterParams) {
        try {
            return chapterDao.getNewChapterByFilter((filterParams));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public long insertChapter(ChapterEntity chapter) throws SQLException {
        return chapterDao.insertChapter(chapter);
    }

    @Override
    public boolean insertIntoPublishedList(ChapterEntity chapter) {
        return chapterDao.insertIntoPublishedList(chapter);
    }

    @Override
    public long getNextChapter(long chapterId,long storyid) {
        return chapterDao.getNextChapter(chapterId,storyid);
    }

    @Override
    public boolean doExist(long chapterId,long storyid) {
        return chapterDao.doExist(chapterId,storyid);
    }

    @Override
    public boolean updateChapter(ChapterEntity chapter) {
        return chapterDao.updateChapter(chapter);
    }

    @Override
    public boolean updatePreviousChapter(ChapterEntity chapter) {
        return chapterDao.updatePreviousChapter(chapter);
    }

    @Override
    public boolean updateNextOfPrevChapter(ChapterEntity chapter) {
        return chapterDao.updateNextOfPrevChapter(chapter);
    }
}
