CREATE OR REPLACE PROCEDURE findInterestedCategory ( uname IN VARCHAR2) IS
  i NUMBER;
BEGIN
  i:=0;

  FOR R IN ( select STORY.CATEGORYNAME , count(*) total
              from likes join STORY
              On LIKES.LIKEDSTORY = STORY.STORYID
                WHERE LIKES.LIKEDBY = uname
              GROUP BY STORY.CATEGORYNAME
              ORDER BY total DESC
  )
  LOOP
    DBMS_OUTPUT.PUT_LINE('cat '||R.CATEGORYNAME) ;
    INSERT INTO RECOMMENDEDCATEGORY (INTERESTED, INTERESTEDIN, INTERESTRATING)
      VALUES (uname, R.CATEGORYNAME, i+1);
    i := i+1;
    EXIT WHEN (i = 5 ) ;
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
  DBMS_OUTPUT.PUT_LINE('I dont know what happened!') ;

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
    EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('I dont know what happened!') ;
  END ;
/

CREATE OR REPLACE PROCEDURE findInterestedStory ( uname IN VARCHAR2) IS
  i NUMBER;
  j NUMBER;
  nbour VARCHAR2(20);
  sid NUMBER;
  BEGIN
    i:=0;
    j := 0;
    FOR R IN (
            select RC.INTERESTED neighbour , COUNT(RC.INTERESTED) total
            from RECOMMENDEDCATEGORY rc join RECOMMENDEDTAG rt
            on RC.INTERESTED = RT.INTERESTED
            WHERE RT.INTERESTED <> uname
            AND RC.INTERESTEDIN in (SELECT r.INTERESTEDIN from RECOMMENDEDCATEGORY r WHERE r.INTERESTED = uname)
            AND RT.INTERESTEDIN in (SELECT rr.INTERESTEDIN from RECOMMENDEDTAG rr WHERE rr.INTERESTED = uname)
            GROUP BY RC.Interested
            ORDER BY total desc
    )
    LOOP
      DBMS_OUTPUT.PUT_LINE('neighbour '||R.neighbour) ;
      nbour := R.neighbour;

      FOR R IN (  SELECT LIKEDSTORY
                  FROM LIKES
                  WHERE LIKEDBY = nbour
                  AND LIKEDSTORY not in (SELECT LIKEDSTORY from LIKES WHERE LIKEDBY = uname)
                  AND LIKEDSTORY IN (SELECT storyid
                  FROM STORY
                  WHERE CATEGORYNAME IN  (SELECT INTERESTEDIN
                  FROM RECOMMENDEDCATEGORY
                  WHERE INTERESTED = uname)
                  )
      )
      LOOP
        SELECT count(INTERESTEDIN) into sid FROM RECOMMENDEDSTORY WHERE INTERESTED = uname AND INTERESTEDIN = R.LIKEDSTORY;
        IF sid = 0 THEN
          INSERT INTO RECOMMENDEDSTORY (INTERESTED, INTERESTEDIN, INTERESTRATING)
          VALUES (uname, R.LIKEDSTORY, j+1);
          j := j+1;
          END IF ;
      END LOOP;
      i := i+1;
      EXIT WHEN (j = 25 ) ;
    END LOOP;
/*EXCEPTION
WHEN OTHERS THEN
DBMS_OUTPUT.PUT_LINE('I dont know what happened!') ;*/
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