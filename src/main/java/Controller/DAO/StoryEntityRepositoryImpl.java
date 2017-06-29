package Controller.DAO;

import Database.DataConnection;
import Model.*;
import org.hibernate.*;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Repository
public class StoryEntityRepositoryImpl implements StoryEntityRepository {

    Connection conn = DataConnection.getConnection();
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void uploadFile(FilesUploadEntity uploadFile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        long id = (Long)session.save(uploadFile);
        System.out.println("$$$$$$$"+id);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<StoryEntity> getAllStory() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.StoryEntity";
        Query query=session.createQuery(hql);
        List<StoryEntity> list=query.list();
        session.flush();
        session.getTransaction().commit();
        session.close();

        if(list.isEmpty()) return null;
        else return list;
        //return null;
    }

    @Override
    public StoryEntity getStoryById(String storyid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.StoryEntity where storyid = :storyid";
        Query query=session.createQuery(hql);
        query.setParameter("storyid",Long.valueOf(storyid).longValue());
        query.setFirstResult(0);
        query.setMaxResults(1);
        StoryEntity storyEntity = (StoryEntity) query.uniqueResult();
        Hibernate.initialize(storyEntity.getStorytagsByStoryid());
        Hibernate.initialize(storyEntity.getChaptersByStoryid());
        Hibernate.initialize(storyEntity.getStorycommentssByStoryid());
        session.flush();
        session.getTransaction().commit();
        session.close();
        return storyEntity;
//        if(list.isEmpty()) return null;
//        else return list(0);
    }

    @Override
    public List<ReadinglistEntity> getReadingListByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.ReadinglistEntity where listowner = :username";
        Query query=session.createQuery(hql);
        query.setParameter("username",username);
        List<ReadinglistEntity> readinglistEntityList = query.list();
        session.flush();
        session.getTransaction().commit();
        session.close();
        return readinglistEntityList;
    }

    @Override
    public ReadingEntity storyReadByUser(String storyid, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.ReadingEntity where readby = :username and readstory =:storyid";
        Query query=session.createQuery(hql);
        query.setParameter("storyid",Long.valueOf(storyid).longValue());
        query.setParameter("username",username);
        query.setFirstResult(0);
        query.setMaxResults(1);
        ReadingEntity readingEntity = (ReadingEntity) query.uniqueResult();

        session.flush();
        session.getTransaction().commit();
        session.close();
        return readingEntity;
    }

    @Override
    public Boolean insertReadingEntity(ReadingEntity readingEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(readingEntity);
        String hql = "update Model.StoryEntity s set s.readcount=s.readcount+1 where s.storyid = :storyid";
        Query query = session.createQuery(hql);
        query.setParameter("storyid",readingEntity.getReadstory());
        query.executeUpdate();
        session.flush();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ChapterEntity getChapterById(String storyid, String chapterid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.ChapterEntity where chapterid = :chapterid and belongsto =:storyid";
        Query query=session.createQuery(hql);
        System.out.println("?????"+Long.valueOf(chapterid).longValue());
        query.setParameter("storyid",Long.valueOf(storyid).longValue());
        query.setParameter("chapterid",Long.valueOf(chapterid).longValue());
        query.setFirstResult(0);
        query.setMaxResults(1);
        ChapterEntity chapterEntity = (ChapterEntity) query.uniqueResult();
        Hibernate.initialize(chapterEntity.getStoryByBelongsto());
        Hibernate.initialize(chapterEntity.getChaptercommentss());

        session.flush();
        session.getTransaction().commit();
        session.close();

        return chapterEntity;
    }

    @Override
    public long insertStoryComment(StorycommentsEntity storycommentsEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        long id = (Long)session.save(storycommentsEntity);
        System.out.println(storycommentsEntity);

        String hql = "update Model.StoryEntity s set s.commentcount=s.commentcount+1 where s.storyid = :storyid";
        Query query = session.createQuery(hql);
        query.setParameter("storyid",storycommentsEntity.getScommentedon());
        query.executeUpdate();

        session.flush();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public long insertChapterComment(ChaptercommentsEntity chaptercommentsEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        long id = (Long)session.save(chaptercommentsEntity);
        System.out.println(chaptercommentsEntity);

        session.flush();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public void updateReading(String username, String chapterid, String storyid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ReadingEntity readingEntity = (ReadingEntity) session.get(ReadingEntity.class, new ReadingEntityPK("fariha",Long.valueOf(storyid).longValue()));
        if(readingEntity==null){
            ReadingEntity temp = new ReadingEntity();
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            temp.setReadby(username);
            temp.setReadstory(Long.valueOf(storyid).longValue());
            temp.setReadingpace(chapterid);
            temp.setStartedat(time);
            temp.setLastreadat(time);

            String hql = "update Model.StoryEntity s set s.readcount=s.readcount+1 where s.readcount = :storyid";
            Query query = session.createQuery(hql);
            query.setParameter("storyid",temp.getReadstory());
            query.executeUpdate();

            System.out.println("null found");
        }else {
            readingEntity.setReadingpace(chapterid);
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            readingEntity.setLastreadat(time);
            System.out.println("null not found");
        }
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insertLikesEntity(LikesEntity likesEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(likesEntity);
        String hql = "update Model.StoryEntity s set s.likecount=s.likecount+1 where s.storyid = :storyid";
        Query query = session.createQuery(hql);
        query.setParameter("storyid",likesEntity.getLikedstory());
        query.executeUpdate();
        System.out.println(likesEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insertFollowEntity(FollowtableEntity follow) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(follow);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteLikesEntity(LikesEntity likesEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        LikesEntity entity = (LikesEntity)session.get(LikesEntity.class,new LikesEntityPK(likesEntity.getLikedby(),likesEntity.getLikedstory()));
        if(entity==null){
            System.out.println("deleting failed");
        }else {
            session.delete(entity);
            System.out.println("deleting succeed");
            String hql = "update Model.StoryEntity s set s.likecount=s.likecount-1 where s.storyid = :storyid";
            Query query = session.createQuery(hql);
            query.setParameter("storyid",likesEntity.getLikedstory());
            query.executeUpdate();
        }
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void deleteFollowEntity(FollowtableEntity follow) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        FollowtableEntity entity = (FollowtableEntity) session.get(FollowtableEntity.class,
                new FollowtableEntityPK(follow.getFollower(),follow.getFollowed()));

        if(entity==null){
            System.out.println("deleting follow failed");
        }else {
            session.delete(entity);
            System.out.println("deleting follow succeed");
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean storyLikedByUser(String username, String storyid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Model.LikesEntity where likedby = :username and likedstory =:storyid";
        Query query=session.createQuery(hql);
        query.setParameter("username",username);
        query.setParameter("storyid",Long.valueOf(storyid));
        boolean liked = true;
        if (query.list().isEmpty())
            liked = false;

        session.flush();
        session.getTransaction().commit();
        session.close();
        return liked;
    }

    @Override
    public boolean writerFollowedByUser(String username, String writerid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Model.FollowtableEntity where follower = :username and followed =:writerid";
        Query query=session.createQuery(hql);
        query.setParameter("username",username);
        query.setParameter("writerid",writerid);
        boolean followed = true;
        if (query.list().isEmpty())
            followed = false;
        session.flush();
        session.getTransaction().commit();
        session.close();
        return followed;
    }


    @Override
    public ArrayList<ChapterEntity> getChapterTitlesByStoryId(String storyid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long nextChapter = (Long) session.createCriteria(StoryEntity.class)
                .add(Restrictions.eq("storyid",Long.valueOf(storyid)))
                .setProjection(Property.forName("firstchapter"))
                .uniqueResult();
        System.out.println("****** "+nextChapter);
        ArrayList<ChapterEntity> list = new ArrayList<ChapterEntity>();
        ChapterEntity temp;

        while (nextChapter!=0 && nextChapter!=null){
            Query q = session.createQuery("Select s.title, s.nextchapter from Model.ChapterEntity s " +
                    "where s.chapterid = :chapterid and s.belongsto=:storyid");
            q.setParameter("chapterid",nextChapter);
            q.setParameter("storyid",Long.valueOf(storyid));
            List res=  q.list();
            for (Iterator it = res.iterator(); it.hasNext(); ) {
                temp = new ChapterEntity();
                temp.setChapterid(nextChapter);
                Object[] myResult = (Object[]) it.next();
                String title = (String) myResult[0];
                temp.setTitle(title);
                nextChapter = (Long) myResult[1];
                System.out.println( "@@@@ " + title + " " + nextChapter );
                list.add(temp);
            }
        }
        session.flush();
        session.getTransaction().commit();
        session.close();
        return list;
    }


    //*******************************Tamanna****************************//




    @Override
    public int insertStory(StoryEntity story) {
        int id=0;
        try {
            String query = "SELECT STORY_ID_SEQ.NEXTVAL FROM dual";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                id= rs.getInt(1);
                rs.close();

                query = "INSERT INTO STORY(TITLE,DESCRIPTION,CATEGORYNAME,STORYCOPYRIGHT,LANG,STORYID) " +
                        "VALUES(?,?,?,?,?,?) ";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, story.getTitle());
                stmt.setString(2, story.getDescription());
                stmt.setString(3, story.getCategoryname());
                stmt.setString(4, story.getStorycopyright());
                stmt.setString(5, story.getLang());
                stmt.setInt(6, id);
                int rs1 = stmt.executeUpdate();
                if (rs1 == 0) {
                    stmt.close();
                    return 0;
                }

                stmt.close();}
            else return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        story.setStoryid(id);
        insertTag(story);
        return id;
    }


    public AccountuserEntity getWriter(String name)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "from Model.AccountuserEntity c where c.email=:var";
        Query query=session.createQuery(hql);
        query.setParameter("var",name);
        List<AccountuserEntity> list=query.list();
        session.flush();
        session.getTransaction().commit();
        session.close();

        if(list.isEmpty()) return null;
        else return list.get(0);
    }

    //update2
    @Override
    public int getLastChapter(int storyid) {
        try{
            String query = "SELECT  LASTCHAPTER FROM STORY WHERE STORYID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, storyid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                return rs.getInt("LASTCHAPTER");
            }
        }
        catch (Exception e){e.printStackTrace();}

        return -1;
    }


    //update2
    @Override
    public boolean insertTag(StoryEntity newStory) {
        StorytagEntity storytag;
        List<String> values = new ArrayList<String>();
        String[] splitted = newStory.getTags().split(" ");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i = 0; i < splitted.length; ++i) {
            if (!values.contains(splitted[i])) {
                values.add(splitted[i]);

                try {
                    String query = "INSERT INTO STORYTAG(TAGGEDSTORY,TAGNAME) VALUES(?,?) ";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setLong(1, newStory.getStoryid());
                    stmt.setString(2, splitted[i]);
                    System.out.println("tag---"+splitted[i]+newStory.getStoryid());
                    int rs = stmt.executeUpdate();
                    if(rs==0) return false;

                }
                catch (Exception e){e.printStackTrace();}



            }
        }

        if(values.isEmpty()) return false;

        return true;
    }

    //update2
    @Override
    public List<StoryEntity> getSearchResult(String tagname) {
        List<StoryEntity> searchResult = new ArrayList<StoryEntity>();
        try {
            String query = "SELECT * FROM STORY S JOIN STORYTAG T ON ( S.STORYID=T.TAGGEDSTORY)\n" +
                    "WHERE UPPER(T.TAGNAME) LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%"+tagname.toUpperCase()+"%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StoryEntity story = new StoryEntity(rs.getString("CATEGORYNAME"), rs.getShort("CHAPTERCOUNT"), rs.getLong("COMMENTCOUNT"),
                        rs.getTime("CREATEDAT"), rs.getString("DESCRIPTION"), rs.getLong("FIRSTCHAPTER"), rs.getBoolean("isCompleted"),
                        rs.getBoolean("ISMATURE"), rs.getBoolean("ISPUBLISHED"), rs.getString("LANG"), rs.getLong("LASTCHAPTER"),
                        rs.getLong("LIKECOUNT"), rs.getLong("RATEDBYCOUNT"), rs.getLong("RATING"), rs.getLong("READCOUNT"),
                        rs.getString("STORYCOPYRIGHT"), rs.getLong("STORYID"), rs.getString("TITLE"), rs.getTime("UPDATEDAT"),
                        rs.getString("WRITERID"));
                searchResult.add(story);
                System.out.println("SEarch: "+ rs.getLong("STORYID"));

            }

        }

        catch (Exception e){e.printStackTrace();}

        if (searchResult.isEmpty()) searchResult=null;
        return searchResult;
    }


    public int getChapterCount(int storyid)
    {
        try{
            String query = "SELECT  CHAPTERCOUNT FROM STORY WHERE STORYID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, storyid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CHAPTERCOUNT");
            }
        }
        catch (Exception e){e.printStackTrace();}
        return -1;
    }

    //update2
    @Override
    public int getFirstChapter(int storyid) {
        try{
            String query = "SELECT  FIRSTCHAPTER FROM STORY WHERE STORYID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, storyid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("FIRSTCHAPTER");
            }
        }
        catch (Exception e){e.printStackTrace();}
        return -1;
    }


}
