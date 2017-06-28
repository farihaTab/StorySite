package Controller;

/**
 * Created by DELL on 5/16/2017.
 */
import javax.servlet.http.HttpServletRequest;

import Controller.Service.StoryEntityService;
import Model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Handles requests for the file upload page.
 */
@Controller
@RequestMapping("/uploadfile")
public class UploadController {
    @Autowired
    private StoryEntityService storyEntityService;

    private String username = "fariha";//loginController.getUsername();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showUploadForm(HttpServletRequest request) {
        return "hello";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
                                   @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                FilesUploadEntity uploadFile = new FilesUploadEntity();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setFileData(aFile.getBytes());
                storyEntityService.uploadFile(uploadFile);
            }
        }

        return "test";
    }
}