package Validator;

import Controller.Service.ChapterService;
import Model.ChapterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewChapterValidator implements Validator {

    @Autowired
    ChapterService chapterService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ChapterEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chapterbody", "NotEmpty");
    }
}
