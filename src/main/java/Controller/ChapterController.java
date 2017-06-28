package Controller;


import Controller.Service.ChapterService;
import Controller.Service.StoryEntityService;
import Validator.NewChapterValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import Model.*;
import Validator.NewChapterValidator;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Controller
public class ChapterController {

    @Autowired
    ChapterService chapterService;
    @Autowired
    StoryEntityService storyService;
    @Autowired
    NewChapterValidator chapterValidator;
    @Autowired
    private SessionFactory sessionFactory;

    private String username = "fariha";//loginController.getUsername();

    @RequestMapping("/stories")
    public String get(Model model) {
        return "test";
    }
    @RequestMapping("/allstories")
    public String getS(Model model) {
        return "test";
    }



    //update 2
    @RequestMapping("/allstories/{newchapter}")
    public String getProductsByFilter1(@MatrixVariable(pathVar = "newchapter") Map<String, List<String>> filterParams, Model model) throws SQLException {

        Set<String> criterias = filterParams.keySet();
            if(chapterService.getNewChapterByFilter(filterParams)==null){
            return "redirect:/error";}

        model.addAttribute("chapter", chapterService.getNewChapterByFilter(filterParams));
        return "chapter";
    }

    //update 2
    @RequestMapping(value = "/allstories/{newchapter}", method = RequestMethod.POST)
    public String newStorySaving(@ModelAttribute("chapter") ChapterEntity chapter,
                                 @RequestParam(required=false, defaultValue="") String publish,
                                 @RequestParam(required=false, defaultValue="") String nextPart,
                                 @RequestParam(required=false, defaultValue="") String save,
                                 BindingResult res,
                                 @MatrixVariable(pathVar = "newchapter") Map<String, List<String>> filterParams) throws SQLException {


        chapterValidator.validate(chapter,res);
        if(res.hasErrors())
        {
            String path = "allstories/newchapter;story=" + chapter.getBelongsto() + ";chapter=" + chapter.getChapterid();
            return "redirect:/" + path;

        }


        int storyid = (int)chapter.getBelongsto();
        chapter.setNextchapter((long)0);
        long chapterId = chapterService.insertChapter(chapter);
        int nextChapter=storyService.getLastChapter(storyid) + 1;
        chapter.setPrevchapter((long) (storyService.getLastChapter(storyid)-1));
        chapterService.updatePreviousChapter(chapter);

        if(chapter.getPrevchapter()!=0) chapterService.updateNextOfPrevChapter(chapter);


        if (nextPart.equals("Next Part")) {

                if (chapterId != 0) {


                    String path = "allstories/newchapter;story=" + String.valueOf(storyid) + ";chapter=" + nextChapter;
                    return "redirect:/" + path;
                } else return "error";

            } else if (publish.equals("Publish")) {
//                chapterService.insertChapter(chapter);
                chapterService.insertIntoPublishedList(chapter);
                return "redirect:/story";
            } else if (save.equals("Save")) {
               // chapterService.insertChapter(chapter);
                return "redirect:/story";
            }


        return "error";
    }


    //update 2
    @RequestMapping("/stories/{chapterwise}")
    public String getProductsByFilter2(@MatrixVariable(pathVar = "chapterwise") Map<String, List<String>> filterParams, Model model) throws SQLException {
        if(chapterService.getChapterByFilter(filterParams)==null){
            return "redirect:/error";}

        model.addAttribute("chapter", chapterService.getChapterByFilter(filterParams));
        return "chapter";
    }

    //update 2
    @RequestMapping(value = "/stories/{chapterwise}", method = RequestMethod.POST)
    public String newStorySaving2(@ModelAttribute("chapter") ChapterEntity chapter,
                                 @RequestParam(required=false, defaultValue="") String publish,
                                 @RequestParam(required=false, defaultValue="") String nextPart,
                                 @RequestParam(required=false, defaultValue="") String save,
                                 BindingResult res,
                                 @MatrixVariable(pathVar = "chapterwise") Map<String, List<String>> filterParams) throws SQLException {

            long storyid=chapter.getBelongsto();
            long chapterId=chapter.getChapterid();

            long nextChapter=chapterService.getNextChapter(chapterId,storyid);

            if(nextChapter==0) nextChapter=chapterId+1;


            if (nextPart.equals("Next Part")) {

            boolean chapterExists=chapterService.doExist(nextChapter,storyid);
               // long chapterId = chapterService.insertChapter(chapter);
                if (chapterExists) {
                    System.out.println("exist");
                    String path = "stories/chapterwise;story=" + String.valueOf(storyid) + ";chapter=" + nextChapter;
                    return "redirect:/" + path;
                } else
                {
                    System.out.println("not exist"+nextChapter);
                    String path="allstories/newchapter;story="+String.valueOf(storyid)+";chapter="+nextChapter;
                    return "redirect:/"+path;
                }

            } else if (publish.equals("Publish")) {
                chapterService.updateChapter(chapter);
                chapterService.insertIntoPublishedList(chapter);
                return "redirect:/story";
            } else if (save.equals("Save")) {
                chapterService.updateChapter(chapter);
                return "redirect:/users";
            }


        return "error";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(chapterValidator);
    }



}
