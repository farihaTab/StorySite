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
