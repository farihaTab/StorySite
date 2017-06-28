package Controller.DAO;

import Controller.Service.StoryEntityService;
import Database.DataConnection;
import Model.ChapterEntity;
import Model.StoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MiNNiE on 29-Apr-17.
 */
@Repository
public class ChapterDaoImpl implements ChapterDao {

    Connection conn = DataConnection.getConnection();

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StoryEntityService storyService;



    public ChapterEntity getChapterByFilter(Map<String, List<String>> filterParams) {
        try {

            Set<String> criterias = filterParams.keySet();
            if (criterias.contains("story") && criterias.contains("chapter")) {

                int storyid=Integer.parseInt(filterParams.get("story").get(0));
                int chapterid= Integer.parseInt(filterParams.get("chapter").get(0));
                ChapterEntity chapter = new ChapterEntity();

                String query = "SELECT  * FROM CHAPTER WHERE CHAPTERID=? AND BELONGSTO=?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, Integer.parseInt(filterParams.get("chapter").get(0)));
                stmt.setInt(2, Integer.parseInt(filterParams.get("story").get(0)));


                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    chapter.setBelongsto(rs.getLong("belongsto"));
                    chapter.setChapterid(rs.getLong("chapterid"));
                    chapter.setTitle(rs.getString("title"));
                    chapter.setChapterbody(rs.getString("chapterbody"));
                    return chapter;
                }

                /*if( (storyService.getStory(Integer.parseInt(filterParams.get("story").get(0)))!=null)
                    && Integer.parseInt(filterParams.get("chapter").get(0)) -
                        storyService.getChapterCount(Integer.parseInt(filterParams.get("story").get(0))) == 1)
                   {
                        chapter.setStoryID(rs.getInt("storyid"));
                        chapter.setChapterID(Integer.parseInt(filterParams.get("chapter").get(0)));
                        return chapter;
                    }*/

            }

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        return null;
    }


    //update 2
    public ChapterEntity getNewChapterByFilter(Map<String, List<String>> filterParams) {

        System.out.println("hellloooooooooooooooooooo");
        Set<String> criterias = filterParams.keySet();
        if (criterias.contains("story") && criterias.contains("chapter")) {
            int storyid = Integer.parseInt(filterParams.get("story").get(0));
            int chapterid = Integer.parseInt(filterParams.get("chapter").get(0));
            ChapterEntity chapter = new ChapterEntity();
            chapter.setChapterid((long) chapterid);
            chapter.setBelongsto((long) storyid);



            System.out.println("------CC" + storyService.getChapterCount(storyid) + "ID" + chapterid + "st" + storyid + "LC" + storyService.getLastChapter(storyid) + "------");
            if ((storyService.getChapterCount(storyid) == 0 && chapterid == 1)) {

                System.out.println("------no Chapter------");
                return chapter;
            } else if ((chapterid - storyService.getLastChapter(storyid) == 1))
            {
                System.out.println("------111111111111111------");
                return chapter;
            }



        }
        return null;
    }

    //update 2
    public boolean updateStoryWhileInsertingChapter(ChapterEntity chapter)
    {
        int chapterCount=storyService.getChapterCount((int) chapter.getBelongsto())+1;
        String query = "UPDATE STORY SET CHAPTERCOUNT=?,LASTCHAPTER=? WHERE STORYID=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, chapterCount);
            stmt.setLong(2, chapter.getChapterid());
            stmt.setLong(3, chapter.getBelongsto());

            int rs = stmt.executeUpdate();
            if (rs == 0) {
                System.out.println("not updated");
                return false;}
            System.out.println("Done updating CC and LC");


            if(chapterCount==1) {

                query = "UPDATE STORY SET FIRSTCHAPTER=? WHERE STORYID=?";

                stmt = conn.prepareStatement(query);
                stmt.setLong(1, chapter.getChapterid());
                stmt.setLong(2, chapter.getBelongsto());

                rs = stmt.executeUpdate();
                if (rs == 0) {
                    System.out.println("not updated");
                    return false;
                }

            }

            System.out.println("get CC");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }




    //update2
    public boolean updateNextOfPrevChapter(ChapterEntity chapter) {

        String query = "UPDATE CHAPTER SET NEXTCHAPTER=? WHERE CHAPTERID=? AND BELONGSTO=?";
        PreparedStatement stmt = null;
        try {
            System.out.println("PREV"+chapter.getPrevchapter()+" IT"+chapter.getChapterid());
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, chapter.getChapterid());
            stmt.setLong(2, chapter.getPrevchapter());
            stmt.setLong(3, chapter.getBelongsto());

            int rs = stmt.executeUpdate();
            if (rs == 0) {
                System.out.println("not updated");return false;}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteChapter(ChapterEntity chapter) {
        return false;
    }

    //update2
    @Override
    public boolean insertIntoPublishedList(ChapterEntity chapter) {
        String query = "UPDATE CHAPTER SET ISPUBLISHED=1 WHERE CHAPTERID=? AND BELONGSTO=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, chapter.getChapterid());
            stmt.setLong(2, chapter.getBelongsto());

            int rs = stmt.executeUpdate();
            if (rs == 0) {
                System.out.println("not updated");return false;}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //update2
    @Override
    public long getNextChapter(long chapterId, long storyid) {
        try{
            System.out.println("chapterid"+chapterId);
            String query = "SELECT  NEXTCHAPTER FROM CHAPTER WHERE CHAPTERID=? AND BELONGSTO=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, chapterId);
            stmt.setLong(2, storyid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("NEXTCHAPTER");
            }
        }
        catch (Exception e){e.printStackTrace();}
        return -1;
    }


    //update2
    @Override
    public boolean doExist(long chapterId,long storyid) {
        try{
            System.out.println("chapterid"+chapterId);
            String query = "SELECT  * FROM CHAPTER WHERE CHAPTERID=? AND BELONGSTO=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, chapterId);
            stmt.setLong(2, storyid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (Exception e){e.printStackTrace();}
        return false;
    }

    //update2
    @Override
    public boolean updateChapter(ChapterEntity chapter) {
        String query = "UPDATE CHAPTER SET TITLE=? , CHAPTERBODY=? WHERE CHAPTERID=? AND BELONGSTO=?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1,chapter.getTitle());
            stmt.setString(2,chapter.getChapterbody());
            stmt.setLong(3, chapter.getChapterid());
            stmt.setLong(4, chapter.getBelongsto());

            int rs = stmt.executeUpdate();
            if (rs == 0) {
                System.out.println("not updated");return false;}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //update2
    @Override
    public boolean updatePreviousChapter(ChapterEntity chapter) {
        String query = "UPDATE CHAPTER SET PREVCHAPTER=? WHERE CHAPTERID=? AND BELONGSTO=?";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);

        stmt.setLong(1, chapter.getPrevchapter());
        stmt.setLong(2, chapter.getChapterid());
        stmt.setLong(3, chapter.getBelongsto());

            int rs = stmt.executeUpdate();
        if (rs == 0) {
            System.out.println("not updated");
            return false;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public long insertChapter(ChapterEntity chapter) {


        StoryEntity storyEntity=new StoryEntity();
        int chapterCount=storyService.getChapterCount((int) chapter.getBelongsto());
        storyEntity.setChaptercount((short) chapterCount);
        System.out.println("CCC"+chapterCount);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(chapter);
        session.getTransaction().commit();
        session.close();

        updateStoryWhileInsertingChapter(chapter);


        return chapter.getChapterid();




    }


}
