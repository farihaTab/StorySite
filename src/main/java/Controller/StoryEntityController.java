package Controller;

import Controller.Service.StoryEntityService;
import Model.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by DELL on 5/13/2017.
 */
@Controller
@RequestMapping("/story")
public class StoryEntityController {
    @Autowired
    private StoryEntityService storyEntityService;
    private String username = "fariha";


    private static Logger logger = Logger.getLogger(StoryEntityController.class.getName());

    @RequestMapping
    public String list(Model model) {
        logger.info("testing stories...");
        List<StoryEntity> list = storyEntityService.getAllStory();
        model.addAttribute("stories", list );
        logger.info("list.stories : "+list.size()+" "+list.toString());
        return "stories";
    }

    @RequestMapping(value = "/story" ,method = RequestMethod.GET)
    public String getStoryById(@RequestParam("id") String storyid, Model model) {

        logger.info("testing story...");
        StoryEntity story = storyEntityService.getStoryById(storyid);
        model.addAttribute("story", story);
        logger.info("/story : "+story);
        Collection<StorytagEntity> storytagsByStoryid = story.getStorytagsByStoryid();
        model.addAttribute("storytags", storytagsByStoryid);
        logger.info("/story : "+storytagsByStoryid);
        Collection<ChapterEntity> chaptersByStoryid = story.getChaptersByStoryid();
        model.addAttribute("chapters",chaptersByStoryid);
        Collection<StorycommentsEntity> comments = story.getStorycommentssByStoryid();
        model.addAttribute("comments",comments);
        logger.info("comments: "+comments);
        List<ReadinglistEntity> readinglistEntityList = storyEntityService.getReadingListByUsername(username);
        model.addAttribute("list",readinglistEntityList);
        logger.info("reading list "+readinglistEntityList);
        ReadingEntity readingEntity = storyEntityService.storyReadByUser(storyid,username);
        model.addAttribute("reading",readingEntity);
        logger.info("reading "+readingEntity);
        boolean liked = storyEntityService.storyLikedByUser(username, storyid);
        model.addAttribute("liked",liked);

        ReadingEntity readingEntity1 = new ReadingEntity();
        model.addAttribute("formReading",readingEntity1);
        StorycommentsEntity storycommentsEntity = new StorycommentsEntity();
        model.addAttribute("formStoryComment",storycommentsEntity);
        StorycommentsEntity storyReviewEntity = new StorycommentsEntity();
        model.addAttribute("formStoryReview",storyReviewEntity);
        LikesEntity likesEntity = new LikesEntity();
        model.addAttribute("formLikes",likesEntity);

        model.addAttribute("username",username);

        return "storyDetails";
    }

    @RequestMapping(value = "/chapter" ,method = RequestMethod.GET)
    public String getSChapterById(@RequestParam("id1") String storyid, @RequestParam("id2") String chapterid, Model model) {

        storyEntityService.updateReading(username,storyid,chapterid);
        ChapterEntity chapter = storyEntityService.getChapterById(storyid,chapterid);

        System.out.println(chapter);
        model.addAttribute("chapter", chapter);
        ArrayList<ChapterEntity> chapterTitles = storyEntityService.getChapterTitlesByStoryId(storyid);
        model.addAttribute("chapters",chapterTitles);
        List<ReadinglistEntity> readinglistEntityList = storyEntityService.getReadingListByUsername(username);
        model.addAttribute("list",readinglistEntityList);
        logger.info("reading list "+readinglistEntityList);
        //todo: edit getStory..
        StoryEntity story = chapter.getStoryByBelongsto();
        model.addAttribute("story",story);
        logger.info("story belongs to : "+chapter.getStoryByBelongsto());
        model.addAttribute("comments",chapter.getChaptercommentss());
        logger.info("chapter comments: "+chapter.getChaptercommentss());
        boolean liked = storyEntityService.storyLikedByUser(username, storyid);
        model.addAttribute("liked",liked);
        boolean followed = storyEntityService.writerFollowedByUser(username,story.getWriterid());
        model.addAttribute("followed",followed);

        ChaptercommentsEntity chaptercommentsEntity = new ChaptercommentsEntity();
        model.addAttribute("formChapterComment",chaptercommentsEntity);
        ChaptercommentsEntity chapterReviewEntity = new ChaptercommentsEntity();
        model.addAttribute("formChapterReview",chapterReviewEntity);
        LikesEntity likesEntity = new LikesEntity();
        model.addAttribute("formLikes",likesEntity);
        FollowtableEntity follow = new FollowtableEntity();
        model.addAttribute("formFollow",follow);

        model.addAttribute("username",username);


        return "readChapter";
        //model.addAttribute("message","success");
        //return "hello";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping(value = "/readstory" , method = RequestMethod.POST)
    public String handleForms(@ModelAttribute("formReading") ReadingEntity readingEntity,
                              @RequestParam(required=false, defaultValue="") String read){

        if(read.equals("Read")){
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            readingEntity.setStartedat(time);
            readingEntity.setLastreadat(time);
            logger.info("inserting reading entiry "+readingEntity);
            storyEntityService.insertReadingEntity(readingEntity);
        }
        return "redirect:/story/"+readingEntity.getReadstory()+"/"+readingEntity.getReadingpace();
    }

    @RequestMapping(value = "/like" , method = RequestMethod.POST)
    public String insertLikes(@ModelAttribute("formLikes") LikesEntity likesEntity,
                              @RequestParam(required=false, defaultValue="") String like,
                              HttpServletRequest request){

        if(like.equals("Like")){
                logger.info("inserting like entiry " + likesEntity);
                storyEntityService.insertLikesEntity(likesEntity);

        }else {
            System.out.println("deleting like");
            storyEntityService.deleteLikesEntity(likesEntity);
        }
        return "redirect:/story/story?id="+likesEntity.getLikedstory();
    }

    @RequestMapping(value = "/follow" , method = RequestMethod.POST)
    public String insertFollow(@ModelAttribute("formFollow") FollowtableEntity follow,
                              @RequestParam(required=false, defaultValue="") String like,
                              Model model){

        if(like.equals("Follow")){
            logger.info("inserting follow entiry " + follow);
            storyEntityService.insertFollowEntity(follow);
            model.addAttribute("message","insert");
        }else {
            System.out.println("deleting follow");
            storyEntityService.deleteFollowEntity(follow);
            model.addAttribute("message","delete");
        }
        return "hello";

        //return "redirect:/story/story?id="+1;
    }




    @RequestMapping(value = "/postCommentOnStory" , method = RequestMethod.POST)
    public String postCommentOnStory(@ModelAttribute("formStoryComment")StorycommentsEntity storycommentsEntity,
                                     @RequestParam(required=false, defaultValue="") String postComment,Model model)
    {
        if(postComment.equals("Post")){
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            storycommentsEntity.setScommentedat(time);
            System.out.println("inserting comment in database: "+storycommentsEntity);
            long id = storyEntityService.insertStoryComment( storycommentsEntity);
            model.addAttribute("message","success"+id);
        }

        return "redirect:/story/story?id="+storycommentsEntity.getScommentedon();
    }

    @RequestMapping(value = "/postCommentOnChapter" , method = RequestMethod.POST)
    public String postCommentOnChapter(@ModelAttribute("formChapterComment")ChaptercommentsEntity chaptercommentsEntity,
                                     @RequestParam(required=false, defaultValue="") String postComment,Model model)
    {
        System.out.println("posting comment"+chaptercommentsEntity);
        if(postComment.equals("Post")){
            System.out.println("posting comment: "+chaptercommentsEntity.getCcommenttext());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            chaptercommentsEntity.setCcommentedat(time);
            System.out.println("inserting comment in database: "+chaptercommentsEntity);
            long id = storyEntityService.insertChapterComment( chaptercommentsEntity);
            model.addAttribute("message","success"+id);
        }

        return "redirect:/story/chapter?id1="+chaptercommentsEntity.getCcommentedonstory()
                    +"&id2="+chaptercommentsEntity.getCcommentedonchapter();
    }

    @RequestMapping(value = "/postReviewOnStory" , method = RequestMethod.POST)
    public String postReviewOnStory(@ModelAttribute("formStoryReview")StorycommentsEntity storycommentsEntity,
                                     @RequestParam(required=false, defaultValue="") String postReview,Model model)
    {
        if(postReview.equals("Post")){
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            storycommentsEntity.setScommentedat(time);
            System.out.println("inserting comment in database: "+storycommentsEntity);
            long id = storyEntityService.insertStoryComment( storycommentsEntity);
            model.addAttribute("message","success"+id);
        }

        return "redirect:/story/story?id="+storycommentsEntity.getScommentedon();
    }



}
