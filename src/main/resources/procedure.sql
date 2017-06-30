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
    DBMS_OUTPUT.PUT_LINE('cat '||R.CATEGORYNAME) ;
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
      DBMS_OUTPUT.PUT_LINE('tag '||R.TAGNAME) ;
      avgcount := avgcount+R.TOTAL;
      INSERT INTO RECOMMENDEDTAG (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.TAGNAME, i+1);
      i := i+1;
      EXIT WHEN (i = 5 ) ;
    END LOOP;

  END ;
/

CREATE OR REPLACE PROCEDURE populateRecommendation IS
  i NUMBER;
  BEGIN
    DELETE FROM RECOMMENDEDCATEGORY;
    DELETE FROM RECOMMENDEDTAG;
    DELETE FROM RECOMMENDEDSTORY;

    i:=0;
    DBMS_OUTPUT.PUT_LINE('Starting') ;
    FOR R IN ( select USERNAME from ACCOUNTUSER)
    LOOP
      DBMS_OUTPUT.PUT_LINE('username '||R.USERNAME) ;
      findInterestedCategory(R.USERNAME);
      findInterestedTag(R.USERNAME);
      findInterestedStory(R.USERNAME);
    END LOOP;
  END ;
/

DECLARE
BEGIN
  populateRecommendation ;
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
      DBMS_OUTPUT.PUT_LINE('writer : ' || R.FOLLOWED||' '||R.TOTAL) ;
      UPDATE USERPROFILE
        SET CURRENTMONTHFOLLOWER = R.TOTAL
      WHERE USERPROFILE.WRITER = R.FOLLOWED;
    END LOOP;
  END ;
/

DECLARE
BEGIN
  populateTrendingWriter ;
END ;
/

CREATE OR REPLACE PROCEDURE findInterestedStory ( uname IN VARCHAR2) IS
  i NUMBER;
  j NUMBER;
  sid VARCHAR2(20);
  BEGIN
    i:=0;

    FOR R IN (     SELECT RECOMMENDEDCATEGORY.INTERESTED
                   FROM RECOMMENDEDCATEGORY
                   WHERE RECOMMENDEDCATEGORY.INTERESTEDIN = (
                     SELECT INTERESTEDIN FROM RECOMMENDEDCATEGORY
                     WHERE RECOMMENDEDCATEGORY.INTERESTED = uname
                           --AND RECOMMENDEDCATEGORY.INTERESTRATING IN (1,2)
                   )
                    AND RECOMMENDEDCATEGORY.INTERESTED <> uname
                  INTERSECT
                  SELECT RECOMMENDEDTAG.INTERESTED
                  FROM RECOMMENDEDTAG
       WHERE RECOMMENDEDTAG.INTERESTEDIN IN (
                    SELECT INTERESTEDIN FROM RECOMMENDEDTAG
                    WHERE RECOMMENDEDTAG.INTERESTED = uname
                  )
                    AND RECOMMENDEDTAG.INTERESTED <>uname
    )
    LOOP
      DBMS_OUTPUT.PUT_LINE('neighbour '||R.INTERESTED) ;
      sid := R.INTERESTED;
      j := 0;

      FOR R IN (
      SELECT READSTORY
      FROM READING
      WHERE READBY = sid
            AND READBY <> uname
            AND READSTORY IN (SELECT storyid
                              FROM STORY
                              WHERE CATEGORYNAME IN
                                    (SELECT INTERESTEDIN
                                     FROM RECOMMENDEDCATEGORY
                                     WHERE INTERESTED = uname)
                              )
      ORDER BY LASTREADAT DESC
      )
      LOOP

        INSERT INTO RECOMMENDEDSTORY (INTERESTED, INTERESTEDIN, INTERESTRATING)
        VALUES (uname, R.READSTORY, j+1);
        j := j+1;
        EXIT WHEN (j = 3);
      END LOOP;


      i := i+1;
      EXIT WHEN (i = 5 ) ;
    END LOOP;

  END ;
/


DECLARE
  total number;
  sid number;
BEGIN

  FOR R IN (SELECT BELOngsto , count(*) total FROM CHAPTER GROUP BY BElongsto)
  LOOP
    DBMS_OUTPUT.PUT_LINE(r.BELONGSTO || ' ' ||r.TOTAL);

    update STORY
    SET STORY.CHAPTERCOUNT = r.total
    WHERE STORY.STORYID =R.BELONGSTO;
  END LOOP ;

END ;
/