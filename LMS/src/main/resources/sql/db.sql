-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assessment` (
                              `assessment_id` int NOT NULL AUTO_INCREMENT,
                              `due_date` date DEFAULT NULL,
                              `title` varchar(100) NOT NULL,
                              `type` enum('ASSIGNMENT','QUIZ') NOT NULL,
                              `course_course_id` int NOT NULL,
                              PRIMARY KEY (`assessment_id`),
                              KEY `FK688anunmt3lmvi8xxvanodqr8` (`course_course_id`),
                              CONSTRAINT `FK688anunmt3lmvi8xxvanodqr8` FOREIGN KEY (`course_course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment`
--

LOCK TABLES `assessment` WRITE;
/*!40000 ALTER TABLE `assessment` DISABLE KEYS */;
INSERT INTO `assessment` VALUES (1,'2024-02-12','1','ASSIGNMENT',1);
/*!40000 ALTER TABLE `assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
                              `attendance_id` int NOT NULL AUTO_INCREMENT,
                              `attendance_date` date NOT NULL,
                              `status` enum('ABSENT','PRESENT') NOT NULL,
                              `course_course_id` int NOT NULL,
                              `student_user_id` int NOT NULL,
                              PRIMARY KEY (`attendance_id`),
                              KEY `FKieguh16mdawgunnele198jeaw` (`course_course_id`),
                              KEY `FK7fqae5faghmfrxiwlabmah7c9` (`student_user_id`),
                              CONSTRAINT `FK7fqae5faghmfrxiwlabmah7c9` FOREIGN KEY (`student_user_id`) REFERENCES `user` (`user_id`),
                              CONSTRAINT `FKieguh16mdawgunnele198jeaw` FOREIGN KEY (`course_course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
                          `course_id` int NOT NULL AUTO_INCREMENT,
                          `description` text,
                          `title` varchar(100) NOT NULL,
                          `instructor_user_id` int NOT NULL,
                          PRIMARY KEY (`course_id`),
                          KEY `FKqj44k6xd069ub1i197a4cljts` (`instructor_user_id`),
                          CONSTRAINT `FKqj44k6xd069ub1i197a4cljts` FOREIGN KEY (`instructor_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'This course serves as an introduction to java Programming. The course focuses on giving the attendees highlights on the java history and evolution of java from a newly introduced language to becoming a standard in application development.','Java',1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
                              `enrollment_id` int NOT NULL AUTO_INCREMENT,
                              `course_course_id` int NOT NULL,
                              `student_user_id` int NOT NULL,
                              PRIMARY KEY (`enrollment_id`),
                              KEY `FKsrohxsncebva8ssubg604hh7c` (`course_course_id`),
                              KEY `FKol08mf7ybydnd5iy6o47d8ngs` (`student_user_id`),
                              CONSTRAINT `FKol08mf7ybydnd5iy6o47d8ngs` FOREIGN KEY (`student_user_id`) REFERENCES `user` (`user_id`),
                              CONSTRAINT `FKsrohxsncebva8ssubg604hh7c` FOREIGN KEY (`course_course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (1,1,3);
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
                         `grade_id` int NOT NULL AUTO_INCREMENT,
                         `feedback` text,
                         `grade` float DEFAULT NULL,
                         `assessment_assessment_id` int NOT NULL,
                         `student_user_id` int NOT NULL,
                         PRIMARY KEY (`grade_id`),
                         KEY `FKgwu98p7gnjkb0vh2mjbu4q2n3` (`assessment_assessment_id`),
                         KEY `FKnvqynd3k0ipc8dlykw01emq59` (`student_user_id`),
                         CONSTRAINT `FKgwu98p7gnjkb0vh2mjbu4q2n3` FOREIGN KEY (`assessment_assessment_id`) REFERENCES `assessment` (`assessment_id`),
                         CONSTRAINT `FKnvqynd3k0ipc8dlykw01emq59` FOREIGN KEY (`student_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'well',9,1,3);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
                          `lesson_id` int NOT NULL AUTO_INCREMENT,
                          `content` varchar(255) DEFAULT NULL,
                          `title` varchar(100) NOT NULL,
                          `course_course_id` int NOT NULL,
                          PRIMARY KEY (`lesson_id`),
                          KEY `FKludiv7d49615l8eqnxdxwoi05` (`course_course_id`),
                          CONSTRAINT `FKludiv7d49615l8eqnxdxwoi05` FOREIGN KEY (`course_course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'The History and Evolution of Java','The History and Evolution of Java',1),(2,'An Overview of Java','An Overview of Java',1),(3,'Datatypes ,Variables and Arrays','Datatypes ,Variables and Arrays',1);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
                                 `notification_id` int NOT NULL AUTO_INCREMENT,
                                 `content` varchar(255) NOT NULL,
                                 `is_read` bit(1) NOT NULL,
                                 `user_user_id` int NOT NULL,
                                 PRIMARY KEY (`notification_id`),
                                 KEY `FKi1jt6tdacc8gqnr658imcldrf` (`user_user_id`),
                                 CONSTRAINT `FKi1jt6tdacc8gqnr658imcldrf` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'you hava lesson ',_binary '',1);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(100) NOT NULL,
                        `full_name` varchar(100) DEFAULT NULL,
                        `password` varchar(255) NOT NULL,
                        `role` enum('ADMIN','INSTRUCTOR','STUDENT') NOT NULL,
                        `username` varchar(50) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
                        UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'master@mail.com','master','$2a$10$XawGJ6a/CKjZmv9zTdUL2e6DCWq2oJAA/dgJ1BxHQ98daXlKdFMtG','INSTRUCTOR','master'),(2,'admin@gmail.com','admin','$2a$10$ZcphnOjLtmsUcuFtGNUsmucoVdeS1QzpirFQhVLpLjMRr.jqHUhqO','ADMIN','admin'),(3,'student@gmail.com','student','$2a$10$ZcphnOjLtmsUcuFtGNUsmucoVdeS1QzpirFQhVLpLjMRr.jqHUhqO','STUDENT','STUDENT');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-20 15:50:59
