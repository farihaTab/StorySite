CREATE OR REPLACE PROCEDURE findInterestedCategory ( uname IN VARCHAR2) IS
  i NUMBER;
BEGIN
  i:=0;

  FOR R IN ( select CATEGORYNAME, COUNT(*) TOTAL
              from STORY JOIN LIKES
              ON STORY.STORYID = LIKES.LIKEDSTORY
              WHERE LIKES.LIKEDBY = uname
              GROUP BY STORY.CATEGORYNAME
              ORDER BY TOTAL ASC)
  LOOP
    INSERT INTO RECOMMENDEDCATEGORY (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.CATEGORYNAME, R.TOTAL);
    i := i+1;
    EXIT WHEN (i = 5 ) ;
  END LOOP;
END ;
/


CREATE OR REPLACE PROCEDURE findInterestedTag ( uname IN VARCHAR2) IS
  i NUMBER;
  BEGIN
    i:=0;

    FOR R IN ( select TAGNAME, COUNT(*) TOTAL
               from STORYTAG JOIN LIKES
                   ON STORYTAG.TAGGEDSTORY = LIKES.LIKEDSTORY
               WHERE LIKES.LIKEDBY = uname
               GROUP BY STORYTAG.TAGNAME
               ORDER BY TOTAL ASC)
    LOOP
      INSERT INTO RECOMMENDEDTAG (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.TAGNAME, R.TOTAL);
      i := i+1;
      EXIT WHEN (i = 5 ) ;
    END LOOP;
  END ;
/

CREATE OR REPLACE PROCEDURE populateRecommendation ( uname IN VARCHAR2) IS
  i NUMBER;
  BEGIN
    i:=0;

    FOR R IN ( select USERNAME from ACCOUNTUSER)
    LOOP
      findInterestedCategory(R.USERNAME);
      findInterestedTag(R.USERNAME);
    END LOOP;
  END ;
/
