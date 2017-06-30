--alter userprofile

DROP TABLE USERPROFILE;
CREATE TABLE UserProfile
(
writer VARCHAR2(20),
aboutWriter VARCHAR2(4000),
followcount NUMBER (9,0),
currentmonthfollower NUMBER (9,0),--for trending writers
CONSTRAINT pk_userprofile PRIMARY KEY (writer),
CONSTRAINT fk_userprofile FOREIGN KEY (writer) REFERENCES AccountUser(username)  ON DELETE CASCADE
);

DROP TABLE INTEREST;

CREATE TABLE RECOMMENDEDcategory
(
  interested VARCHAR2(20),--username
  interestedIn VARCHAR2(30), --CATEGORYNAME
  interestRating INTEGER,
  CONSTRAINT pk_recCat PRIMARY KEY (interested,interestedIn),
  CONSTRAINT fk_recCat_username FOREIGN KEY(interested) REFERENCES AccountUser(username) ON DELETE CASCADE ,
  CONSTRAINT fk_recCat_cat FOREIGN KEY (interestedIn) REFERENCES CATEGORY(CATNAME) ON DELETE CASCADE
);

CREATE TABLE RECOMMENDEDtag
(
  interested VARCHAR2(20),--username
  interestedIn VARCHAR2(100), --tagNAME
  interestRating INTEGER,
  CONSTRAINT pk_recTag PRIMARY KEY (interested,interestedIn),
  CONSTRAINT fk_recTag_username FOREIGN KEY(interested) REFERENCES AccountUser(username) ON DELETE CASCADE
);

DROP TABLE FollowTable;
CREATE TABLE FollowTable
(
  follower  VARCHAR2(20) not null,
  followed 	VARCHAR2(20) not null,
  updatedAt DATE DEFAULT SYSDATE,
  CONSTRAINT pk_followtable PRIMARY KEY (follower, followed),
  Constraint fk_ft_follower FOREIGN KEY(follower) REFERENCES AccountUser(username)  ON DELETE CASCADE ,
  Constraint fk_ft_followed FOREIGN KEY(followed) REFERENCES AccountUser(username)  ON DELETE CASCADE
);

CREATE TABLE RECOMMENDEDstory
(
  interested VARCHAR2(20),--storyid
  interestedIn INTEGER, --tagNAME
  interestRating NUMBER(9,2),
  CONSTRAINT pk_recStory PRIMARY KEY (interested,interestedIn),
  CONSTRAINT fk_recStory_username FOREIGN KEY(interested) REFERENCES AccountUser(username) ON DELETE CASCADE
);

update STORY
    SET STORY.CHAPTERCOUNT = (select count(*) from CHAPTER GROUP BY CHAPTER.BELONGSTO)
WHERE STORY.STORYID = CHAPTER.BELONGSTO;


INSERT INTO LIKES (LIKEDBY, LIKEDSTORY) VALUES ('fariha',);