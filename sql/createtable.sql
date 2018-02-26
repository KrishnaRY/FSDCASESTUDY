
DROP TABLE IF EXISTS  user  ;
DROP TABLE IF EXISTS  work_out  ;
DROP TABLE IF EXISTS  unit_time  ;
DROP TABLE IF EXISTS  workout_transction  ;


CREATE  TABLE IF NOT EXISTS  user  
(
   userID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   userName varchar(100) NOT NULL,
   password varchar(100) NOT NULL
);

CREATE  TABLE IF NOT EXISTS  work_out
(
   workoutID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   title varchar(100) NOT NULL,
   
   unittime unittime,
   userID int NOT NULL,
   CONSTRAINT fk_userID_work_out
 	FOREIGN KEY (userID)
	REFERENCES user(userID)
	ON DELETE CASCADE
);

CREATE  TABLE IF NOT EXISTS  TASK
(
   Task_ID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Parent_ID int,
   Project_ID int NOT NULL,
   Task varchar(100) NOT NULL,
   Start_Date DATE NOT NULL,
   End_Date DATE NOT NULL,
   Priority int NOT NULL ,
   Status varchar(100) NOT NULL,
   User_ID int NOT NULL,
    CONSTRAINT fk_Project_ID
 	FOREIGN KEY (Project_ID)
	REFERENCES PROJECT(Project_ID)
	ON DELETE CASCADE,
	  CONSTRAINT fk_User_ID_TASK
 	FOREIGN KEY (User_ID)
	REFERENCES USER(User_ID)
	ON DELETE CASCADE
);


insert into ParentTask(Parent_Task) values ('parent task1');




