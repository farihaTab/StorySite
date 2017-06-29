

CREATE TABLE AccountUser
(
username VARCHAR2(20),
password VARCHAR2(20),
FirstName VARCHAR2(20) NOT NULL,
LastName VARCHAR2(20) NOT NULL,
Email VARCHAR2(30) UNIQUE NOT NULL,
ImageURL VARCHAR2(100),
UserType  VARCHAR2(10),
CreatedAt DATE default sysdate,
UpdatedAt DATE default sysdate,
CONSTRAINT pk_accountuser PRIMARY KEY (username)
);


CREATE TABLE UserProfile
(
writer VARCHAR2(20),
aboutWriter VARCHAR2(4000),
CONSTRAINT pk_userprofile PRIMARY KEY (writer),
CONSTRAINT fk_userprofile FOREIGN KEY (writer) REFERENCES AccountUser(username)  ON DELETE CASCADE
);


CREATE TABLE FollowTable
(
follower  VARCHAR2(20) not null,
followed 	VARCHAR2(20) not null,
CONSTRAINT pk_followtable PRIMARY KEY (follower, followed),
Constraint fk_ft_follower FOREIGN KEY(follower) REFERENCES AccountUser(username)  ON DELETE CASCADE ,
Constraint fk_ft_followed FOREIGN KEY(followed) REFERENCES AccountUser(username)  ON DELETE CASCADE
);

CREATE TABLE CopyRightType
(
--copyrightid INTEGER,
copyright VARCHAR2(40) ,
CONSTRAINT pk_crt PRIMARY KEY (copyright)
);

CREATE TABLE Category
(
--categoryid INTEGER,
catname VARCHAR2(30),
CONSTRAINT pk_category PRIMARY KEY (catname)
);

CREATE TABLE languageTable
(
--languageid INTEGER,
languageid VARCHAR2(30),
CONSTRAINT pk_langtable PRIMARY KEY(languageid)
);

CREATE TABLE Story
(
StoryId INTEGER,
writerId VARCHAR2(20),
title VARCHAR2(100) not null,
lang VARCHAR2(30) not null,--??
description VARCHAR2(4000) ,
firstChapter INTEGER default 0,
lastChapter INTEGER default 0,
categoryName VARCHAR2(30) ,--??
isMature NUMBER(1,0) default 0 check( isMature in (0,1)),
isPublished NUMBER(1,0) default 0 check( isPublished in (0,1)),
isCompleted NUMBER(1,0) default 0 check( isCompleted in (0,1)),
readCount NUMBER(9,0) default 0,
Chaptercount Number(5,0) default 0,
CommentCount NUMBER(9,0) default 0,
LikeCount NUMBER(9,0) Default 0,
Rating NUMBER(3,1) default 0.0 CHECK (Rating<= 5.0 and rating>=0.0) ,
RatedByCount NUMBER(9,0) default 0,
storyCopyRight VARCHAR2(40),--??
CreatedAt DATE default SYSDATE,
UpdatedAt DATE default SYSDATE,
CONSTRAINT pk_story PRIMARY KEY(StoryId) ,
CONSTRAINT fk_story_writerid FOREIGN KEY(writerId) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_story_copyright FOREIGN KEY (storyCopyRight) REFERENCES CopyRightType(copyright) ON DELETE SET NULL ,
CONSTRAINT fk_story_category FOREIGN KEY (categoryName) REFERENCES category(catname) ON DELETE SET NULL ,
CONSTRAINT fk_story_lang FOREIGN KEY (lang) REFERENCES languageTable(languageid) ON DELETE SET NULL
);

--story has chapter

CREATE TABLE Chapter
(
chapterId INTEGER check(chapterId<>0),
belongsTo INTEGER, --storyId
nextChapter INTEGER default 0,
prevChapter INTEGER default 0,
Title VARCHAR2(100) ,
isPublished NUMBER(1,0) default 0 check( isPublished in (0,1)) ,
imageURL VARCHAR2(50),
ChapterBody CLOB,--??
CreatedAt DATE default SYSDATE,
UpdatedAt DATE default sysdate,
CONSTRAINT pk_chapter PRIMARY KEY (chapterId,belongsTo),
CONSTRAINT fk_chapter FOREIGN KEY(belongsTo) REFERENCES Story(storyId) ON DELETE CASCADE
);



--story has tag

CREATE TABLE StoryTag
(
taggedStory INTEGER,
tagname VARCHAR2(100),
CONSTRAINT pk_storytag PRIMARY KEY (taggedStory,tagname),
CONSTRAINT fk_storytag FOREIGN KEY(taggedStory) REFERENCES Story(storyId) ON DELETE SET NULL
);



--user likes story
CREATE TABLE Likes
(
likedBy VARCHAR2(20),
likedStory INTEGER,
CONSTRAINT pk_likes PRIMARY KEY (likedBy,likedStory),
CONSTRAINT fk_likes_username FOREIGN KEY(likedBy) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_likes_storyid FOREIGN KEY (likedStory) REFERENCES Story(storyId) ON DELETE CASCADE
);

--story has comments by user
CREATE TABLE StoryComments
(
sCommentId INTEGER,
sCommentText VARCHAR2(4000),
scommentedOn INTEGER, --storyid
sCommentedBy VARCHAR2(20),
scommentedAt DATE default SYSDATE,
sCommentType VARCHAR(20), --review / normal
CONSTRAINT pk_scomments PRIMARY KEY (sCommentId),
CONSTRAINT fk_scomments_storyid FOREIGN KEY (scommentedOn) REFERENCES Story(storyId) ON DELETE CASCADE ,
CONSTRAINT fk_scomments_username FOREIGN KEY(sCommentedBy) REFERENCES AccountUser(username) ON DELETE SET NULL
);

CREATE TABLE ChapterComments
(
cCommentId INTEGER,
cCommentText VARCHAR2(4000),
ccommentedOnStory INTEGER, --storyid
ccommentedOnChapter INTEGER, --chapterid
cCommentedBy VARCHAR2(20),
ccommentedAt DATE default SYSDATE,
sCommentType VARCHAR2(20), --review/normal
CONSTRAINT pk_comments PRIMARY KEY (cCommentId),
--CONSTRAINT fk_comments_storyid FOREIGN KEY (cCommentedOnStory) REFERENCES Story(storyId) ON DELETE CASCADE ,
CONSTRAINT fk_comments_chapterid FOREIGN KEY (cCommentedOnChapter,ccommentedOnStory) REFERENCES Chapter(chapterId, belongsTo) ON DELETE CASCADE ,
CONSTRAINT fk_comments_username FOREIGN KEY(cCommentedBy) REFERENCES AccountUser(username) ON DELETE SET NULL
);


--user rates story
CREATE TABLE rates
(
ratedBy VARCHAR2(20),
ratedStory INTEGER,
rating NUMBER(3,1) check(rating<=5.0 and rating>=0.0),
CONSTRAINT pk_rates PRIMARY KEY (ratedStory,ratedBy),
CONSTRAINT fk_rates_username FOREIGN KEY(ratedBy) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_rates_storyid FOREIGN KEY (ratedStory) REFERENCES Story(storyId) ON DELETE CASCADE
);

--user reads story
CREATE TABLE reading
(
readBy VARCHAR2(20),
readStory INTEGER,
readingPace VARCHAR2(100),
startedAt DATE default sysdate,
lastreadat DATE default sysdate,
CONSTRAINT pk_reads PRIMARY KEY (readStory,readBy),
CONSTRAINT fk_reads_username FOREIGN KEY(readBy) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_reads_storyid FOREIGN KEY (readStory) REFERENCES Story(storyId) ON DELETE SET NULL
);

--user creates reading list
CREATE TABLE readingList
(
listid INTEGER,
listTitle VARCHAR2(100),
listOwner VARCHAR2(20),
createdAt DATE default sysdate,
updatedAt DATE default sysdate,
CONSTRAINT pk_readingList PRIMARY KEY(listid),
CONSTRAINT fk_readinglist_username FOREIGN KEY(listOwner) REFERENCES AccountUser(username) ON DELETE CASCADE
);

--readinglist contains stories
CREATE TABLE readingListDetails
(
list INTEGER,
storyInList INTEGER, --storyId
AddedAt Date default sysdate,
CONSTRAINT pk_readingListDetails PRIMARY KEY(list,storyInList),
CONSTRAINT fk_readingListDetails_listid FOREIGN KEY(list) REFERENCES readingList(listid) ON DELETE CASCADE ,
CONSTRAINT fk_readingListDetails_storyid FOREIGN KEY(storyInList) REFERENCES Story(storyid) ON DELETE SET NULL
);

CREATE TABLE Viewed
(
viewedBy VARCHAR2(20),
viewedStory INTEGER,
CONSTRAINT pk_viewed PRIMARY KEY (viewedBy,viewedStory),
CONSTRAINT fk_viewed_username FOREIGN KEY(viewedBy) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_viewed_storyid FOREIGN KEY (viewedStory) REFERENCES Story(storyId) ON DELETE CASCADE
);


CREATE TABLE interest
(
interested VARCHAR2(20),--username
interestedIn INTEGER, --storyid
interestRating INTEGER,
interestType VARCHAR2(200),
CONSTRAINT pk_interested PRIMARY KEY (interested,interestedIn),
CONSTRAINT fk_interested_username FOREIGN KEY(interested) REFERENCES AccountUser(username) ON DELETE CASCADE ,
CONSTRAINT fk_interested_storyid FOREIGN KEY (interestedIn) REFERENCES Story(storyId) ON DELETE CASCADE
);


CREATE TABLE Notify
(
notifyId INTEGER,
notify VARCHAR2(20), --username
notifyMsg VARCHAR2(1000),
notifyTime DATE,
notifyLink VARCHAR2(1000),
notifyStatus NUMBER(1,0) default 0 check( notifyStatus in (0,1)), --seen / unseen
CONSTRAINT pk_notify PRIMARY KEY (notifyId,notify),
CONSTRAINT fk_notify FOREIGN KEY (notify) REFERENCES AccountUser(username) On DELETE CASCADE
);
















