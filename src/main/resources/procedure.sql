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
      VALUES (uname, R.CATEGORYNAME, i+1);
    i := i+1;
    EXIT WHEN (i = 5 ) ;
  END LOOP;
END ;
/


CREATE OR REPLACE PROCEDURE findInterestedTag ( uname IN VARCHAR2) IS
  i NUMBER;
  avgcount NUMBER;
  BEGIN
    i:=0;
    avgcount := 0;
    FOR R IN ( select TAGNAME, COUNT(*) TOTAL
               from STORYTAG JOIN LIKES
                   ON STORYTAG.TAGGEDSTORY = LIKES.LIKEDSTORY
               WHERE LIKES.LIKEDBY = uname
               GROUP BY STORYTAG.TAGNAME
               ORDER BY TOTAL ASC)
    LOOP
      avgcount := avgcount+R.TOTAL;
      INSERT INTO RECOMMENDEDTAG (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.TAGNAME, i+1);
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

CREATE OR REPLACE PROCEDURE populateTrendingWriter IS
  prev DATE;
  BEGIN
    prev := SYSDATE - 30;

    FOR R IN ( SELECT FOLLOWED, COUNT(*) TOTAL
               FROM FOLLOWTABLE
               WHERE FOLLOWTABLE.UPDATEDAT > prev
               GROUP BY FOLLOWTABLE.FOLLOWED)
    LOOP
      UPDATE USERPROFILE
        SET CURRENTMONTHFOLLOWER = R.TOTAL
      WHERE USERPROFILE.WRITER = R.FOLLOWED;
    END LOOP;
  END ;
/

CREATE OR REPLACE PROCEDURE findInterestedStory ( uname IN VARCHAR2) IS
  i NUMBER;
  BEGIN
    i:=0;

    FOR R IN (     SELECT RECOMMENDEDCATEGORY.INTERESTED
                   FROM RECOMMENDEDCATEGORY
                   WHERE RECOMMENDEDCATEGORY.INTERESTEDIN = (
                     SELECT INTERESTEDIN FROM RECOMMENDEDCATEGORY
                     WHERE RECOMMENDEDCATEGORY.INTERESTED = uname
                           AND RECOMMENDEDCATEGORY.INTERESTRATING IN (1,2)
                   )
                  INTERSECT
                  SELECT RECOMMENDEDTAG.INTERESTED
                  FROM RECOMMENDEDTAG
                  WHERE RECOMMENDEDTAG.INTERESTEDIN = (
                    SELECT INTERESTEDIN FROM RECOMMENDEDTAG
                    WHERE RECOMMENDEDTAG.INTERESTED = uname
                  )
    )
    LOOP
      SELECT
      FROM READING
      WHERE READBY = R.INTERESTED
        AND READSTORY in (SELECT storyid from STORY WHERE CATEGORYNAME in
                                                         (SELECT INTERESTEDIN
                                                          FROM RECOMMENDEDCATEGORY
                                                         WHERE INTERESTED = uname)
      )
      ORDER BY LASTREADAT DESC;

      INSERT INTO RECOMMENDEDTAG (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.TAGNAME, R.TOTAL);
      i := i+1;
      EXIT WHEN (i = 5 ) ;
    END LOOP;




  END ;
/
