package Controller;

import Controller.Service.StoryEntityService;
import Database.DataConnection;
import Validator.NewStoryValidator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.*;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Controller
public class StoryController {

    @Autowired
    StoryEntityService storyService;
    @Autowired
    NewStoryValidator newStoryValidator;
    @Autowired
    private SessionFactory sessionFactory;

    private String username = "fariha";//loginController.getUsername();

    @RequestMapping(value = "/database",method = RequestMethod.GET)
    public String users(Model model)
    {
        try {
            Connection conn = DataConnection.getConnection();
            String uType = null;
            String query = "select username,EMAIL from ACCOUNTUSER";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<AccountuserEntity> accountUserArrayList=new ArrayList<AccountuserEntity>();
            while(rs.next()) {
                accountUserArrayList.add(new AccountuserEntity(rs.getString("Username"),rs.getString("Email")));
            }

            model.addAttribute("userList",accountUserArrayList);
            rs.close();
            stmt.close();
            return "User";

        } catch (Exception e) {

            e.printStackTrace();
            return "error";
        }


    }

    @RequestMapping(value = "/newStory", method = RequestMethod.GET)
    public String newStory(Model model) {


        StoryEntity newStory=new StoryEntity();

        model.addAttribute("newStory",newStory);
        return "newStory";
    }


    @RequestMapping(value = "/newStory", method = RequestMethod.POST)
    public String newStorySaving(@ModelAttribute("newStory") StoryEntity newStory,
                                  @RequestParam(required=false, defaultValue="") String next,
                                  @RequestParam(required=false, defaultValue="") String cancel,
                                  @RequestParam(required=false, defaultValue="") String save,
                                 BindingResult res){
        newStoryValidator.validate(newStory,res);

        if(res.hasErrors())
        {
            return "newStory";
        }

        if (next.equals("Next")) {

            int storyId=storyService.insertStory(newStory);

            /*int nextChapter;
            if(storyService.getChapterCount(storyId)==0) nextChapter=1;

            else nextChapter=storyService.getFirstChapter(storyId);
            if(nextChapter==-1) return "error";*/

            if(storyId!=0)
            {
               String path="allstories/newchapter;story="+String.valueOf(storyId)+";chapter="+1;
                return "redirect:/"+path;

            }

            else return "error";

        } else if (cancel.equals("Cancel")) {
            return "redirect:/story";
        }
        else if (save.equals("Save")) {
            storyService.insertStory(newStory);
            return "redirect:/story";
        }


        return "newStory";

    }


    @ModelAttribute("languageList")
    public Map<String, String> getLanguageList()
    {

        Session session = sessionFactory.openSession();
        String hql = "FROM LanguagetableEntity";
        Query query = session.createQuery(hql);
        List<LanguagetableEntity> langList = query.list();
        session.close();

        Map<String, String> languageList = new HashMap<String, String>();
        for(LanguagetableEntity lang:langList) languageList.put(lang.getLanguageid(),lang.getLanguageid());
        return languageList;
    }



    @ModelAttribute("copyrightList")
    public Map<String, String> getCopyrightList()
    {

        Session session = sessionFactory.openSession();
        String hql = "FROM CopyrighttypeEntity ";
        Query query = session.createQuery(hql);
        List<CopyrighttypeEntity> copyList = query.list();
        session.close();

        Map<String, String> copyrightList = new HashMap<String, String>();
       for(CopyrighttypeEntity copy:copyList) copyrightList.put(copy.getCopyright(),copy.getCopyright());
        return copyrightList;
    }



    @ModelAttribute("categoryList")
    public Map<String, String> getcategoryList()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "FROM CategoryEntity ";
        Query query = session.createQuery(hql);
        List<CategoryEntity> catList = query.list();
        session.getTransaction().commit();
        session.close();

        Map<String, String> categoryList = new HashMap<String, String>();
        for(CategoryEntity cat:catList) categoryList.put(cat.getCatname(),cat.getCatname());
        return categoryList;
    }



    //update 2
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchStoryGet(@RequestParam("searchValue") String searchValue,Model model){
        List<StoryEntity> searchResult=storyService.getSearchResult(searchValue);
        System.out.println("SE:---"+searchValue+searchResult.get(0).getStoryid());

        if(searchResult==null) return "error";
        model.addAttribute("stories", searchResult );
        return "stories";

    }

    //update 2
    @RequestMapping(value = "/searchResult", method = RequestMethod.POST)
    public String searchResult(Model model){

        return "redirect:/searchResult";
    }



    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(newStoryValidator);
    }




}
