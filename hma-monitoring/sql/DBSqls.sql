Drop table user if exists;
drop table activities if exists;
drop table knownactivities if exists;
drop table target if exists;
DROP TABLE IF EXISTS BiologicalProfile;

drop sequence userid_seq if exists;
drop sequence targetid_seq if exists;
drop sequence activityid_seq if exists;

CREATE TABLE User (
userid number,
    UserName VARCHAR (50) NOT NULL,
	Password VARCHAR(20) NOT NULL,
	Email VARCHAR(50),
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	BirthDay DATE,

    PRIMARY KEY(userid)
);
alter table user add column age number;

create sequence userid_seq start with 1 increment by 1;
create sequence targetid_seq start with 1 increment by 1;
create sequence activityid_seq start with 1 increment by 1;

insert into user values(((select userid_seq.nextval from dual)), 'chan','12345','chan@uncc.edu','Chanikya','Varma', {ts '2012-09-17 18:47:52.69'}, 25);


CREATE TABLE BiologicalProfile (
	Userid number NOT NULL,
	Height number ,
    Weight number,
    Hip number ,
    Neck number ,
    Waist number ,
    Wrist number ,
activefrom date,
activetill date default {ts '9999-12-31 00:00:00.0'},
    SystolicBloodPressure number ,
    DiastolicBloodPressure number ,
    RestingHeartRate number ,
	FOREIGN KEY (Userid) REFERENCES User (Userid)
);

CREATE TABLE Activities (
ActivityID number,
	UserID number NOT NULL,
	ActivityTypeid number NOT NULL,
	StartDate DATETIME NOT NULL,
	EndDate DATETIME,
    CaloriesBurned number,
    Temperature number,
    Humidity number,

	PRIMARY KEY (activityid),
	FOREIGN KEY (Userid) REFERENCES User (userid)
);
insert into activities values((select activityid_seq.nextval from dual), 1, 1, {ts '2014-11-17 00:00:00.0'}, {ts '2014-11-17 01:00:00.0'}, 350, null, null);
CREATE TABLE KnownActivities (
activityTypeId number,
	ActivityType VARCHAR(35),
	CaloriesBurnedPerHour number,
	PRIMARY KEY (ActivityTypeid)
);


INSERT INTO KnownActivities
	(ActivityTypeId, ActivityType, CaloriesBurnedPerHour)
VALUES
	(1,'Sleeping', 45),
(2,'Watching TV', 56),
(3,'Sitting', 102),
(4,'Light Household Chores', 95),
(5,'Standing', 100),
(6,'Playing with dog', 115),
(7,'Playing with kids (not-rigorous)', 120),
(8,'Driving', 120),
(9,'Shopping', 135),
(10,'Eating', 140),
(11,'Bowling', 145),
(12,'Cooking', 186),
(13,'Dancing (slow, waltz, foxtrot)', 224),
(14,'Weight Lifting (light)', 224),
(15,'Household Chores', 225),
(16,'Walking', 230),
(17,'Golfing riding in cart)', 260),
(18,'Softball', 260),
(19,'Skateboarding', 275),
(20,'Gardening', 300),
(21,'Stretching', 300),
(22,'Light Yoga', 300),
(23,'Lifting Weights vigorous)', 446), 
(24,'Downhill Skiing', 315),
(25,'Lawn Mowing', 410),
(26,'Golfing walking with bag)', 410),
(27,'Volleyball', 340),
(28,'Baseball', 365),
(29,'Walking (~4.5mph)', 372),
(30,'Power Walking', 400),
(31,'Dancing (disco, ballroom, square)', 410),
(32,'Hiking', 440),
(33,'Shoveling Snow', 446),
(34,'Water Skiing', 446),
(35,'Cross Country Skiing', 500),
(36,'Basketball', 510),
(37,'Tennis', 520),
(38,'Swimming', 520),
(39,'Scuba Diving', 520),
(40,'Step Aerobics (light impact)', 520),
(41,'Soccer', 520),
(42,'Biking (fast-paced)', 530),
(43,'Circuit Weight Training', 540),
(44,'Football', 600),
(45,'Rock Climbing', 600),
(46,'Hockey', 600),
(47,'Stairclimber', 600),
(48,'Running (~5mph)', 600),
(49,'Rowing', 632),
(50,'Elliptical Machine', 670),
(51,'Boxing', 670),
(52,'Water Aerobics', 720),
(53,'Racquetball', 740),
(54,'Step Aerobics heavy impact)', 744),
(55,'Spinning', 782),
(56,'Jumping Rope', 900),
(57,'Running (~7.5mph)', 940),
(58,'Bicycling (>20mph)', 1220)
;
commit;

DROP TABLE IF EXISTS Target;

CREATE TABLE Target (
targetid number,
	Userid VARCHAR(50) NOT NULL,
	Calories number NOT NULL,
	StartDate DATETIME NOT NULL,
	EndDate DATETIME NOT NULL,
	ActivityTypeId number NOT NULL,
	CurrentProgress number default 0,
	duration number,
	PRIMARY KEY (targetid),
	FOREIGN KEY (userid) REFERENCES User (Userid),
FOREIGN KEY (activitytypeid) REFERENCES knownactivities(activitytypeid)

);


