-- MySQL dump 10.13  Distrib 5.6.36, for macos10.12 (x86_64)
--
-- Host: localhost    Database: security-test
-- ------------------------------------------------------
-- Server version       8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Resource`
--

DROP TABLE IF EXISTS `Resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Resource`
(
    `resource_id`   int          NOT NULL AUTO_INCREMENT COMMENT '资源ID',
    `resource_name` varchar(255) NOT NULL COMMENT '资源名称',
    `resource_path` varchar(255) NOT NULL COMMENT '资源路径',
    `resource_type` varchar(100) DEFAULT NULL COMMENT '资源类型',
    PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resource`
--

LOCK
TABLES `Resource` WRITE;
/*!40000 ALTER TABLE `Resource` DISABLE KEYS */;
INSERT INTO `Resource`
VALUES (1, '获取user', 'GET:/v1/user', '1'),
       (2, '修改user', 'PUT:/v1/user', '1'),
       (3, '删除user', 'DELETE:/v1/user', '1');
/*!40000 ALTER TABLE `Resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role`
(
    `role_id`     int          NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`   varchar(255) NOT NULL COMMENT '角色名称',
    `description` text COMMENT '描述',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储角色信息的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK
TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role`
VALUES (1, 'admin', '超级管理员'),
       (2, 'agent', '普通用户');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `Role_Resource`
--

DROP TABLE IF EXISTS `Role_Resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role_Resource`
(
    `role_resource_id` int NOT NULL AUTO_INCREMENT COMMENT '角色资源关联ID',
    `role_id`          int NOT NULL COMMENT '角色ID',
    `resource_id`      int DEFAULT NULL,
    PRIMARY KEY (`role_resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储角色和资源之间关联信息的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role_Resource`
--

LOCK
TABLES `Role_Resource` WRITE;
/*!40000 ALTER TABLE `Role_Resource` DISABLE KEYS */;
INSERT INTO `Role_Resource`
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 1);
/*!40000 ALTER TABLE `Role_Resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User`
(
    `user_id`  int          NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` varchar(255) NOT NULL COMMENT '用户名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储用户信息的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK
TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User`
VALUES (1, 'ds', '$2a$10$TUDqKmGfPLp0eTZSQzCDQudDnE40zpP0eIMPY2efA7rSte9ZtObim'),
       (2, 'zhangsan', '$2a$10$5KlvsTKVINiFDkSU4SPD9eFT250kfTzUIguWA51w64OeFgBNxLp/m'),
       (3, 'lisi', '$2a$10$F8dnUaKCKFQGizS7D2/WqOZrsTHCCxTOxX0kCRjlWK3.iu9j5anH2'),
       (4, 'test', '$2a$10$R78Q25s0gPJGDDcz3odr2.3rVsP9EVucjTKhoj5I41eS3Xj/05INa'),
       (5, 'test2', '$2a$10$rBgI/hhYiwivHABti1cxpuY04X3J3FUk5RqhPIQB4WKHjfskgLhwG'),
       (6, 'test3', '$2a$10$6C3bFpBHIwEGU8wH3Ub6g.wlAk/TH6HYCjOI2F1tGEkCzRJRSfdN.'),
       (7, 'test4', '$2a$10$0SCXRfkVXzOWv1jrnFP28eRYSuDhfDrWXxwgFAbsSVECpwHGI18eS');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `User_Role`
--

DROP TABLE IF EXISTS `User_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Role`
(
    `user_role_id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色关联ID',
    `user_id`      int NOT NULL COMMENT '用户ID',
    `role_id`      int NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储用户和角色之间关联信息的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Role`
--

LOCK
TABLES `User_Role` WRITE;
/*!40000 ALTER TABLE `User_Role` DISABLE KEYS */;
INSERT INTO `User_Role`
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 2);
/*!40000 ALTER TABLE `User_Role` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-05  9:54:51