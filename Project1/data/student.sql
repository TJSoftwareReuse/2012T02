-- MySQL dump 10.13  Distrib 5.6.22, for osx10.10 (x86_64)
--
-- Host: localhost    Database: student
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_info` (
  `id` int(11) NOT NULL,
  `team_num` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `github` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_info`
--

LOCK TABLES `student_info` WRITE;
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
INSERT INTO `student_info` VALUES (1152704,7,'方程','男','fdfangbaby'),(1252000,0,'test','male','test'),(1252845,3,'代蒙','男','fengyue'),(1252847,5,'杨宇歆','男','ShadowYang'),(1252848,5,'张良','男',''),(1252850,9,'褚振方','男','tracyCzf'),(1252859,7,'尹巧','女','12aQ12'),(1252862,5,'关清晨','男',''),(1252863,8,'贺志鹏','男','HELoki'),(1252865,0,'秦乙丹','男',''),(1252868,3,'郑勇俊','男','zzzzzzzz131313'),(1252872,6,'张旭晨','男',''),(1252873,2,'李伟','男',''),(1252874,0,'陈薇伊','女',''),(1252875,8,'杨丰','男','YoooFeng'),(1252876,6,'喻帅','男','neveraway'),(1252878,2,'许舰','男','XuJian1252878'),(1252880,4,'彭秋辰','男',''),(1252885,2,'王笑盈','女','wangxiaoying'),(1252895,3,'邓冰茜','女','jessiedbq'),(1252897,4,'杨爽','男',''),(1252899,0,'阮康乐','男',''),(1252907,9,'于自跃','男','yeyeaa'),(1252910,3,'张奕格','女',''),(1252911,7,'黄昕','男','x18jinjz'),(1252914,8,'邱尚昭','男','qsz13'),(1252923,2,'关晨','男',''),(1252927,5,'杨春雨','男','MarK-YANG'),(1252937,1,'许铭淏','男',''),(1252948,6,'刘蕊','女','molly2415'),(1252953,4,'胡文超','女',''),(1252956,7,'赵鹏','男',''),(1252957,8,'丁宇笙','男','dyslove123'),(1252960,3,'胡圣托','男','h1994st'),(1252961,6,'时雨','男',''),(1252966,10,'王远','男',''),(1252973,7,'于航','女',''),(1252974,10,'姜木慧','男',''),(1252976,0,'任子卓','男',''),(1252977,2,'孙琳','女','sllethe'),(1252980,10,'刘禹嘉','男','TJLiuyujia'),(1252984,1,'李亚斯','男','LiArthur'),(1252987,4,'梁竞文','女',''),(1252988,0,'郭静阳','男',''),(1252992,1,'吴逸菲','女','kris1688'),(1252996,1,'黄徐欢','男',''),(1252999,9,'陈启源','男','qycgit'),(1253000,5,'周泽宏','男','superbean'),(1253016,10,'叶剑权','男','yejianquan'),(1253022,0,'陈玉婷','女',''),(1253027,8,'高屹','女',''),(1253036,9,'陈璐','女','o0lazybear0o'),(1253406,6,'韦吾境','男','');
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-15  8:44:03
