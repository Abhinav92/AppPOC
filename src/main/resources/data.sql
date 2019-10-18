--CREATE TABLE RELEASE_PARAMETERS 
--(REPORTID varchar(255 CHAR),
--ADDITIONALINFO varchar(255 CHAR), 
--ATTACHMENT varchar(255 CHAR),
--BUSINESSCONTACT varchar(255 CHAR),
--DESCRIPTION varchar(255 CHAR),
--LOB varchar(255 CHAR),
--NAME varchar(255 CHAR),
--PROGRESSBAR varchar(255 CHAR),
--YEAR varchar(255 CHAR)
--TITLE varchar(255 CHAR),
--QUARTER varchar(255 CHAR),
--RELEASES varchar(255 CHAR),
--PRIMARY KEY ("REPORTID")
-- );
-- 
-- 
-- CREATE TABLE "JIRA_HOLDER_TABLE" 
-- ( 
-- "ID" VARCHAR2(255 CHAR),
-- "EFFORT" VARCHAR2(255 CHAR), 
-- "USAGE" VARCHAR2(255 CHAR), 
-- "STORY" VARCHAR2(255 CHAR), 
-- "BUSINESS" VARCHAR2(255 CHAR),
-- PRIMARY KEY ("ID"),
--  KEY `fk_jira` (`REPORTID`),
--  CONSTRAINT `fk_jira` FOREIGN KEY (`REPORTID`) REFERENCES `RELEASE_PARAMETERS` (`REPORTID`)
-- );

insert into entityReleaseParameters(REPORTID,ADDITIONALINFO,ATTACHMENT,BUSINESSCONTACT,DESCRIPTION,LOB,NAME,PROGRESSBAR,YEAR,TITLE,QUARTER,RELEASES,RISK,ASSUMPTION,ISSUES,DEPENDENCY)
values(10001,'The story is blocked.','Yes','Abhinav','Detailed Description','Cloud','ITSDEO-7004','Yellow','2019','Billing documents for Commitments-01', '08','RD01','Risk is low','No Assumptions','Issue related to IT-FR31','No dependency');

insert into entityReleaseParameters(REPORTID,ADDITIONALINFO,ATTACHMENT,BUSINESSCONTACT,DESCRIPTION,LOB,NAME,PROGRESSBAR,YEAR,TITLE,QUARTER,RELEASES,RISK,ASSUMPTION,ISSUES,DEPENDENCY)
values(10002,'The story is new.','No','Poulomi','Detailed Description','On-prim','ITSDEO-7005','Red','2017','Billing documents for Commitments-02','07','FD01','Risk is low','No Assumptions','Issue related to IT-FR31','No dependency');

insert into entityReleaseParameters(REPORTID,ADDITIONALINFO,ATTACHMENT,BUSINESSCONTACT,DESCRIPTION,LOB,NAME,PROGRESSBAR,YEAR,TITLE,QUARTER,RELEASES,RISK,ASSUMPTION,ISSUES,DEPENDENCY)
values(10003,'The story is blocked.','Yes','Poulomi','Detailed Description','Services','ITSDEO-7006','Yellow','2020','Billing documents for Commitments-03','04', 'FD05','Risk is low','No Assumptions','Issue related to IT-FR31','No dependency');

insert into entityReleaseParameters(REPORTID,ADDITIONALINFO,ATTACHMENT,BUSINESSCONTACT,DESCRIPTION,LOB,NAME,PROGRESSBAR,YEAR,TITLE,QUARTER,RELEASES,RISK,ASSUMPTION,ISSUES,DEPENDENCY)
values(10004,'No info about this story.','Yes','Abhinav','Detailed Description','Cloud','ITSDEO-7008','Red','2020','Billing documents for Commitments-04','07', 'RD10','Risk is low','No Assumptions','Issue related to IT-FR31','No dependency');

insert into entityReleaseParameters(REPORTID,ADDITIONALINFO,ATTACHMENT,BUSINESSCONTACT,DESCRIPTION,LOB,NAME,PROGRESSBAR,YEAR,TITLE,QUARTER,RELEASES,RISK,ASSUMPTION,ISSUES,DEPENDENCY)
values(10005,'This is an old story.','No','Abhinav','Detailed Description','On-prim','ITSDEO-7002','Green','2019','Billing documents for Commitments-05','04', 'FD01','Risk is low','No Assumptions','Issue related to IT-FR31','No dependency');

