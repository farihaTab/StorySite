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