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
    @Override
    public ArrayList<StoryDetails> getRecommendedStories(String username){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("recommending story for : "+username);

        String hql = "select rs.interestedin from RecommendedstoryEntity rs where rs.interested = :username order by rs.interestrating desc ";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setMaxResults(10);
        ArrayList<StoryDetails> recommendedStories = new ArrayList<StoryDetails>();
        List<Long> list = query.list();
        System.out.println("size : "+list.size());

        if(list.size()< 4){
            hql = "CALL populateRecommendationForUser(:username)";
            query = session.createQuery(hql);
            query.setParameter("username",username);
            query.list();

            hql = "select rs.interestedin from RecommendedstoryEntity rs where rs.interested = :username order by rs.interestrating desc ";
            query = session.createQuery(hql);
            query.setParameter("username",username);
            query.setMaxResults(10);

            list = query.list();
            System.out.println("size : "+list.size());

            if(list.size()==0){
                return null;
            }

        }
        for (Long object:list) {
            Long storyid = (Long)object;
            hql = "from Model.StoryEntity where storyid = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",storyid);
            StoryEntity story =(StoryEntity) query.uniqueResult();

            System.out.println("story: "+story.getTitle());
            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",story.getWriterid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
            System.out.println("writer: "+userprofile.getWriter());

            String sql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
            query = session.createQuery(sql);
            query.setParameter("storyid",story.getStoryid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            List<String> temptags = query.list();
            ArrayList<String> tags  = new ArrayList<String>();
            for (String obj:temptags) {
                tags.add(obj);
                System.out.println(obj);
            }
            //Hibernate.initialize(story.getStorytagsByStoryid());

            recommendedStories.add(new StoryDetails(story,userprofile,tags));
        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return recommendedStories;

    }
    @Override
    public ArrayList<StoryDetails> getContinueReadingStories(String username){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("continue reading for "+username);

        String hql = "SELECT  r.readstory FROM Model.ReadingEntity r " +
                "WHERE r.readby = :username " +
                "AND r.readingpace <> (SELECT s.lastchapter FROM Model.StoryEntity s WHERE s.storyid = r.readstory) " +
                "ORDER BY r.lastreadat DESC ";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setMaxResults(10);
        List<Long> list = query.list();
        ArrayList<StoryDetails> continueReading = new ArrayList<StoryDetails>();
        for (Long object:list) {
            Long storyid = object;
            hql = "from Model.StoryEntity where storyid = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",storyid);
            StoryEntity story =(StoryEntity) query.uniqueResult();

            System.out.println("story: "+story.getTitle());
            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",story.getWriterid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
            System.out.println("writer: "+userprofile.getWriter());

            hql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",story.getStoryid());
            List<String> temptags = query.list();
            ArrayList<String> tags  = new ArrayList<String>();
            for (String obj:temptags) {
                tags.add(obj);
                System.out.println(obj);
            }

            continueReading.add(new StoryDetails(story,userprofile,tags));
        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return continueReading;
        /*try{
            String query = "SELECT  READSTORY FROM READING WHERE READBY = ? AND READINGPACE <> " +
                    "(SELECT LASTCHAPTER FROM STORY WHERE STORYID = READING.READSTORY)" ;
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setMaxRows(10);
            ResultSet rs = stmt.executeQuery();
            ArrayList<StoryDetails> storyDetailss = new ArrayList<StoryDetails>();
            if (rs.next()) {
                int storyid = rs.getInt("READSTORY");
                String

            }
        }
        catch (Exception e){e.printStackTrace();}*/

    }


    @Override
    public ArrayList<StoryDetails> getTrendingStories(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //todo: write code for finding trending stories
        session.flush();
        session.getTransaction().commit();
        session.close();
        return null;
    }

    @Override
    public ArrayList<StoriesByTag> getStoriesByUserLikedTags(String username){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("stories by tags");

        String hql = "select rt.interestedin from RecommendedtagEntity rt WHERE rt.interested = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setMaxResults(5);
        List<String> tagsList = query.list();

        ArrayList<StoriesByTag> storiesByTags = new ArrayList<StoriesByTag>();
        for(String tag: tagsList){
            System.out.println("tagname: "+tag);
            hql = "select st.taggedstory from Model.StorytagEntity st , Model.StoryEntity s " +
                    "where st.taggedstory = s.storyid "+
                    "and st.tagname = :tag "+
                    "and st.taggedstory not in (select l.likedstory from Model.LikesEntity l where l.likedby = :username) "+
                    "order by s.likecount desc ";
            query = session.createQuery(hql);
            query.setParameter("tag",tag);
            query.setParameter("username",username);
            query.setMaxResults(10);

            List<Long> storyids = query.list();
            ArrayList<StoryDetails> storyDetails=new ArrayList<StoryDetails>();
            for(Long storyid : storyids){

                hql = "from Model.StoryEntity where storyid = :storyid";
                query = session.createQuery(hql);
                query.setParameter("storyid",storyid);
                StoryEntity story =(StoryEntity) query.uniqueResult();

                System.out.println("story: "+story.getTitle());
                hql = "from Model.UserprofileEntity where  writer = :writerid";
                query = session.createQuery(hql);
                query.setParameter("writerid",story.getWriterid());
                query.setFirstResult(0);
                query.setMaxResults(1);
                UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
                System.out.println("writer: "+userprofile.getWriter());

                hql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
                query = session.createQuery(hql);
                query.setParameter("storyid",story.getStoryid());
                List<String> temptags = query.list();
                ArrayList<String> tags  = new ArrayList<String>();
                for (String obj:temptags) {
                    tags.add(obj);
                    System.out.println(obj);
                }
                storyDetails.add(new StoryDetails(story,userprofile,tags));
            }
            if(storyDetails.size()>0)
                storiesByTags.add(new StoriesByTag(tag,storyDetails));

        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return storiesByTags;
    }

    @Override
    public ArrayList<StoriesByWriter> getTrendingWritersStories(String username){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("stories by writers");

        String hql = "SELECT up.writer " +
                "from Model.UserprofileEntity up " +
                "where up.writer not in (SELECT ft.followed from Model.FollowtableEntity ft WHERE ft.follower = :username) " +
                "ORDER BY up.currentmonthfollower DESC";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setMaxResults(5);
        List<String> writers = query.list();

        ArrayList<StoriesByWriter> storiesByWriters = new ArrayList<StoriesByWriter>();
        for(String writer: writers){
            System.out.println("writer: "+writer);
            hql = "from Model.StoryEntity s where s.writerid = :writer order by s.likecount desc ";
            query = session.createQuery(hql);
            query.setParameter("writer",writer);
            query.setMaxResults(10);
            ArrayList<StoryEntity> storyEntities = (ArrayList<StoryEntity>) query.list();

            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",writer);
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();

            if(storyEntities.size()>0)
                storiesByWriters.add(new StoriesByWriter(writer,userprofile,storyEntities));

        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return storiesByWriters;
    }

    @Override
    public ArrayList<StoryDetails> getStorySuggestionsFromAlikeUsers(String storyId, String categoryname){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("stories by writers");

        String hql = "SELECT l.likedstory  " +
                "FROM Model.LikesEntity l " +
                "WHERE l.likedstory <> :storyid " +
                "and l.likedby IN ( " +
                "  SELECT l2.likedby " +
                "  FROM Model.LikesEntity l2 " +
                "  WHERE l2.likedstory = :storyid " +
                ") AND ( " +
                "        l.likedstory IN ( " +
                "          SELECT s.storyid " +
                "          FROM Model.StoryEntity s " +
                "          WHERE s.categoryname = :category " +
                "        ) " +
                "        OR l.likedstory IN ( " +
                "          SELECT st.taggedstory " +
                "          FROM Model.StorytagEntity st " +
                "          WHERE st.tagname IN ( " +
                "            SELECT st2.tagname " +
                "            FROM Model.StorytagEntity st2 " +
                "            WHERE st2.taggedstory = :storyid " +
                "        ) " +
                "      ) " +
                " ) " +
                "GROUP BY l.likedstory " +
                "ORDER BY count(l.likedby) DESC ";
        Query query = session.createQuery(hql);
        query.setParameter("storyid",Long.valueOf(storyId));
        query.setParameter("category",categoryname);
        query.setMaxResults(6);
        List<Long> list = query.list();
        ArrayList<StoryDetails> suggestions = new ArrayList<StoryDetails>();

        for (Long object:list) {
            Long storyid = object;
            hql = "from Model.StoryEntity where storyid = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",storyid);
            StoryEntity story =(StoryEntity) query.uniqueResult();

            System.out.println("story: "+story.getTitle());
            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",story.getWriterid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
            System.out.println("writer: "+userprofile.getWriter());

            hql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",story.getStoryid());
            List<String> temptags = query.list();
            ArrayList<String> tags  = new ArrayList<String>();
            for (String obj:temptags) {
                tags.add(obj);
                System.out.println(obj);
            }

            suggestions.add(new StoryDetails(story,userprofile,tags));
        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return suggestions;
    }

    @Override
    public ArrayList<StoryDetails> getTopStoriesByCategoryForUser(String username, String category){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("stories by writers");

        String hql = "select s.storyid from Model.StoryEntity s where s.categoryname = :category and "+
                    "s.storyid not in (select l.likedstory from Model.LikesEntity l where l.likedby = :username ) "+
                    "order by likecount desc ";
        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setParameter("category",category);
        query.setMaxResults(6);
        List<Long> list = query.list();
        ArrayList<StoryDetails> suggestions = new ArrayList<StoryDetails>();

        for (Long object:list) {
            Long storyid = object;
            hql = "from Model.StoryEntity where storyid = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",storyid);
            StoryEntity story =(StoryEntity) query.uniqueResult();

            System.out.println("story: "+story.getTitle());
            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",story.getWriterid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
            System.out.println("writer: "+userprofile.getWriter());

            hql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid",story.getStoryid());
            List<String> temptags = query.list();
            ArrayList<String> tags  = new ArrayList<String>();
            for (String obj:temptags) {
                tags.add(obj);
                System.out.println(obj);
            }

            suggestions.add(new StoryDetails(story,userprofile,tags));
        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return suggestions;
    }

    /*
    neighbour
    String hql = "select rc.interested " +
                "            from Model.RecommendedcategoryEntity rc , Model.RecommendedtagEntity rt\n" +
                "            where rt.interested <> :username " +
                "            and rc.interested = rt.interested " +
                "            AND rc.interestedin in (SELECT r.interestedin from Model.RecommendedcategoryEntity r WHERE r.interested = :username) " +
                "            AND rt.interestedin in (SELECT rr.interestedin from Model.RecommendedtagEntity rr WHERE rr.interested = :username) " +
                "            GROUP BY rc.interestedin " +
                "            ORDER BY COUNT(rc.interestedin) desc";
     */

    @Override
    public ArrayList<StoriesByWriter> getSuggestedProfileForUser(String username) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("stories by writers");

        String hql = "select f.followed from Model.FollowtableEntity f "+
                "where f.follower in ( "+
                "select rc.interested " +
                "            from Model.RecommendedcategoryEntity rc , Model.RecommendedtagEntity rt\n" +
                "            where rt.interested <> :username " +
                "            and rc.interested = rt.interested " +
                "            AND rc.interestedin in (SELECT r.interestedin from Model.RecommendedcategoryEntity r WHERE r.interested = :username) " +
                "            AND rt.interestedin in (SELECT rr.interestedin from Model.RecommendedtagEntity rr WHERE rr.interested = :username) " +
                " ) "+
                "group by f.followed "+
                "order by count(f.follower) desc ";

        Query query = session.createQuery(hql);
        query.setParameter("username",username);
        query.setMaxResults(10);
        List<String> writers = query.list();

        ArrayList<StoriesByWriter> storiesByWriters = new ArrayList<StoriesByWriter>();
        for(String writer: writers){
            System.out.println("writer: "+writer);
            hql = "from Model.StoryEntity s where s.writerid = :writer order by s.likecount desc ";
            query = session.createQuery(hql);
            query.setParameter("writer",writer);
            query.setMaxResults(10);
            ArrayList<StoryEntity> storyEntities = (ArrayList<StoryEntity>) query.list();

            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid",writer);
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();

            if(storyEntities.size()>0)
                storiesByWriters.add(new StoriesByWriter(writer,userprofile,storyEntities));

        }

        session.flush();
        session.getTransaction().commit();
        session.close();
        return storiesByWriters;
    }

    @Override
    public ArrayList<StoryDetails> getTopStories() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Top stories");

        String hql = "select s.storyid from Model.StoryEntity s order by s.likecount desc ";
        Query query = session.createQuery(hql);
        query.setMaxResults(15);
        List<Long> list = query.list();
        ArrayList<StoryDetails> topStories = new ArrayList<StoryDetails>();

        for (Long object : list) {
            Long storyid = object;
            hql = "from Model.StoryEntity where storyid = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid", storyid);
            StoryEntity story = (StoryEntity) query.uniqueResult();

            System.out.println("story: " + story.getTitle());
            hql = "from Model.UserprofileEntity where  writer = :writerid";
            query = session.createQuery(hql);
            query.setParameter("writerid", story.getWriterid());
            query.setFirstResult(0);
            query.setMaxResults(1);
            UserprofileEntity userprofile = (UserprofileEntity) query.uniqueResult();
            System.out.println("writer: " + userprofile.getWriter());

            hql = "select st.tagname from Model.StorytagEntity st where st.taggedstory = :storyid";
            query = session.createQuery(hql);
            query.setParameter("storyid", story.getStoryid());
            List<String> temptags = query.list();
            ArrayList<String> tags = new ArrayList<String>();
            for (String obj : temptags) {
                tags.add(obj);
                System.out.println(obj);
            }

            topStories.add(new StoryDetails(story, userprofile, tags));
        }
        return topStories;
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
