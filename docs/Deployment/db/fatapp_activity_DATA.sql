-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fatapp
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Cycling, mountain bike, bmx',502,598,695,791),(2,'Cycling, <10 mph, leisure bicycling',236,281,327,372),(3,'Cycling, >20 mph, racing',944,1126,1308,1489),(4,'Cycling, 10-11.9 mph, light',354,422,490,558),(5,'Cycling, 12-13.9 mph, moderate',472,563,654,745),(6,'Cycling, 14-15.9 mph, vigorous',590,704,817,931),(7,'Cycling, 16-19 mph, very fast, racing',708,844,981,1117),(8,'Unicycling',295,352,409,465),(9,'Stationary cycling, very light',177,211,245,279),(10,'Stationary cycling, light',325,387,449,512),(11,'Stationary cycling, moderate',413,493,572,651),(12,'Stationary cycling, vigorous',620,739,858,977),(13,'Stationary cycling, very vigorous',738,880,1022,1163),(14,'Calisthenics, vigorous, pushups, situps…',472,563,654,745),(15,'Calisthenics, light',207,246,286,326),(16,'Circuit training, minimal rest',472,563,654,745),(17,'Weight lifting, body building, vigorous',354,422,490,558),(18,'Weight lifting, light workout',177,211,245,279),(19,'Health club exercise',325,387,449,512),(20,'Stair machine',531,633,735,838),(21,'Rowing machine, light',207,246,286,326),(22,'Rowing machine, moderate',413,493,572,651),(23,'Rowing machine, vigorous',502,598,695,791),(24,'Rowing machine, very vigorous',708,844,981,1117),(25,'Ski machine',413,493,572,651),(26,'Aerobics, low impact',295,352,409,465),(27,'Aerobics, high impact',413,493,572,651),(28,'Aerobics, step aerobics',502,598,695,791),(29,'Aerobics, general',384,457,531,605),(30,'Jazzercise',354,422,490,558),(31,'Stretching, hatha yoga',236,281,327,372),(32,'Mild stretching',148,176,204,233),(33,'Instructing aerobic class',354,422,490,558),(34,'Water aerobics',236,281,327,372),(35,'Ballet, twist, jazz, tap',266,317,368,419),(36,'Ballroom dancing, slow',177,211,245,279),(37,'Ballroom dancing, fast',325,387,449,512),(38,'Running, 5 mph (12 minute mile)',472,563,654,745),(39,'Running, 5.2 mph (11.5 minute mile)',531,633,735,838),(40,'Running, 6 mph (10 min mile)',590,704,817,931),(41,'Running, 6.7 mph (9 min mile)',649,774,899,1024),(42,'Running, 7 mph (8.5 min mile)',679,809,940,1070),(43,'Running, 7.5mph (8 min mile)',738,880,1022,1163),(44,'Running, 8 mph (7.5 min mile)',797,950,1103,1256),(45,'Running, 8.6 mph (7 min mile)',826,985,1144,1303),(46,'Running, 9 mph (6.5 min mile)',885,1056,1226,1396),(47,'Running, 10 mph (6 min mile)',944,1126,1308,1489),(48,'Running, 10.9 mph (5.5 min mile)',1062,1267,1471,1675),(49,'Running, cross country',531,633,735,838),(50,'Running, general',472,563,654,745),(51,'Running, on a track, team practice',590,704,817,931),(52,'Running, stairs, up',885,1056,1226,1396),(53,'Track and field (shot, discus)',236,281,327,372),(54,'Track and field (high jump, pole vault)',354,422,490,558),(55,'Track and field (hurdles)',590,704,817,931),(56,'Archery',207,246,286,326),(57,'Badminton',266,317,368,419),(58,'Basketball game, competitive',472,563,654,745),(59,'Playing basketball, non game',354,422,490,558),(60,'Basketball, officiating',413,493,572,651),(61,'Basketball, shooting baskets',266,317,368,419),(62,'Basketball, wheelchair',384,457,531,605),(63,'Running, training, pushing wheelchair',472,563,654,745),(64,'Billiards',148,176,204,233),(65,'Bowling',177,211,245,279),(66,'Boxing, in ring',708,844,981,1117),(67,'Boxing, punching bag',354,422,490,558),(68,'Boxing, sparring',531,633,735,838),(69,'Coaching: football, basketball, soccer…',236,281,327,372),(70,'Cricket (batting, bowling)',295,352,409,465),(71,'Croquet',148,176,204,233),(72,'Curling',236,281,327,372),(73,'Darts (wall or lawn)',148,176,204,233),(74,'Fencing',354,422,490,558),(75,'Football, competitive',531,633,735,838),(76,'Football, touch, flag, general',472,563,654,745),(77,'Football or baseball, playing catch',148,176,204,233),(78,'Frisbee playing, general',177,211,245,279),(79,'Frisbee, ultimate frisbee',472,563,654,745),(80,'Golf, general',266,317,368,419),(81,'Golf, walking and carrying clubs',266,317,368,419),(82,'Golf, driving range',177,211,245,279),(83,'Golf, miniature golf',177,211,245,279),(84,'Golf, walking and pulling clubs',254,303,351,400),(85,'Golf, using power cart',207,246,286,326),(86,'Gymnastics',236,281,327,372),(87,'Hacky sack',236,281,327,372),(88,'Handball',708,844,981,1117),(89,'Handball, team',472,563,654,745),(90,'Hockey, field hockey',472,563,654,745),(91,'Hockey, ice hockey',472,563,654,745),(92,'Riding a horse, general',236,281,327,372),(93,'Horesback riding, saddling horse',207,246,286,326),(94,'Horseback riding, grooming horse',207,246,286,326),(95,'Horseback riding, trotting',384,457,531,605),(96,'Horseback riding, walking',148,176,204,233),(97,'Horse racing, galloping',472,563,654,745),(98,'Horse grooming, moderate',354,422,490,558),(99,'Horseshoe pitching',177,211,245,279),(100,'Jai alai',708,844,981,1117),(101,'Martial arts, judo, karate, jujitsu',590,704,817,931),(102,'Martial arts, kick boxing',590,704,817,931),(103,'Martial arts, tae kwan do',590,704,817,931),(104,'Krav maga training',590,704,817,931),(105,'Juggling',236,281,327,372),(106,'Kickball',413,493,572,651),(107,'Lacrosse',472,563,654,745),(108,'Orienteering',531,633,735,838),(109,'Playing paddleball',354,422,490,558),(110,'Paddleball, competitive',590,704,817,931),(111,'Polo',472,563,654,745),(112,'Racquetball, competitive',590,704,817,931),(113,'Playing racquetball',413,493,572,651),(114,'Rock climbing, ascending rock',649,774,899,1024),(115,'Rock climbing, rappelling',472,563,654,745),(116,'Jumping rope, fast',708,844,981,1117),(117,'Jumping rope, moderate',590,704,817,931),(118,'Jumping rope, slow',472,563,654,745),(119,'Rugby',590,704,817,931),(120,'Shuffleboard, lawn bowling',177,211,245,279),(121,'Skateboarding',295,352,409,465),(122,'Roller skating',413,493,572,651),(123,'Roller blading, in-line skating',708,844,981,1117),(124,'Sky diving',177,211,245,279),(125,'Soccer, competitive',590,704,817,931),(126,'Playing soccer',413,493,572,651),(127,'Softball or baseball',295,352,409,465),(128,'Softball, officiating',236,281,327,372),(129,'Softball, pitching',354,422,490,558),(130,'Squash',708,844,981,1117),(131,'Table tennis, ping pong',236,281,327,372),(132,'Tai chi',236,281,327,372),(133,'Playing tennis',413,493,572,651),(134,'Tennis, doubles',354,422,490,558),(135,'Tennis, singles',472,563,654,745),(136,'Trampoline',207,246,286,326),(137,'Volleyball, competitive',472,563,654,745),(138,'Playing volleyball',177,211,245,279),(139,'Volleyball, beach',472,563,654,745),(140,'Wrestling',354,422,490,558),(141,'Wallyball',413,493,572,651),(142,'Backpacking, Hiking with pack',413,493,572,651),(143,'Carrying infant, level ground',207,246,286,326),(144,'Carrying infant, upstairs',295,352,409,465),(145,'Carrying 16 to 24 lbs, upstairs',354,422,490,558),(146,'Carrying 25 to 49 lbs, upstairs',472,563,654,745),(147,'Standing, playing with children, light',165,197,229,261),(148,'Walk/run, playing with children, moderate',236,281,327,372),(149,'Walk/run, playing with children, vigorous',295,352,409,465),(150,'Carrying small children',177,211,245,279),(151,'Loading, unloading car',177,211,245,279),(152,'Climbing hills, carrying up to 9 lbs',413,493,572,651),(153,'Climbing hills, carrying 10 to 20 lb',443,528,613,698),(154,'Climbing hills, carrying 21 to 42 lb',472,563,654,745),(155,'Climbing hills, carrying over 42 lb',531,633,735,838),(156,'Walking downstairs',177,211,245,279),(157,'Hiking, cross country',354,422,490,558),(158,'Bird watching',148,176,204,233),(159,'Marching, rapidly, military',384,457,531,605),(160,'Children\'s games, hopscotch, dodgeball',295,352,409,465),(161,'Pushing stroller or walking with children',148,176,204,233),(162,'Pushing a wheelchair',236,281,327,372),(163,'Race walking',384,457,531,605),(164,'Rock climbing, mountain climbing',472,563,654,745),(165,'Walking using crutches',295,352,409,465),(166,'Walking the dog',177,211,245,279),(167,'Walking, under 2.0 mph, very slow',118,141,163,186),(168,'Walking 2.0 mph, slow',148,176,204,233),(169,'Walking 2.5 mph',177,211,245,279),(170,'Walking 3.0 mph, moderate',195,232,270,307),(171,'Walking 3.5 mph, brisk pace',224,267,311,354),(172,'Walking 3.5 mph, uphill',354,422,490,558),(173,'Walking 4.0 mph, very brisk',295,352,409,465),(174,'Walking 4.5 mph',372,443,515,586),(175,'Walking 5.0 mph',472,563,654,745),(176,'Boating, power, speed boat',148,176,204,233),(177,'Canoeing, camping trip',236,281,327,372),(178,'Canoeing, rowing, light',177,211,245,279),(179,'Canoeing, rowing, moderate',413,493,572,651),(180,'Canoeing, rowing, vigorous',708,844,981,1117),(181,'Crew, sculling, rowing, competition',708,844,981,1117),(182,'Kayaking',295,352,409,465),(183,'Paddle boat',236,281,327,372),(184,'Windsurfing, sailing',177,211,245,279),(185,'Sailing, competition',295,352,409,465),(186,'Sailing, yachting, ocean sailing',177,211,245,279),(187,'Skiing, water skiing',354,422,490,558),(188,'Ski mobiling',413,493,572,651),(189,'Skin diving, fast',944,1126,1308,1489),(190,'Skin diving, moderate',738,880,1022,1163),(191,'Skin diving, scuba diving',413,493,572,651),(192,'Snorkeling',295,352,409,465),(193,'Surfing, body surfing or board surfing',177,211,245,279),(194,'Whitewater rafting, kayaking, canoeing',295,352,409,465),(195,'Swimming laps, freestyle, fast',590,704,817,931),(196,'Swimming laps, freestyle, slow',413,493,572,651),(197,'Swimming backstroke',413,493,572,651),(198,'Swimming breaststroke',590,704,817,931),(199,'Swimming butterfly',649,774,899,1024),(200,'Swimming leisurely, not laps',354,422,490,558),(201,'Swimming sidestroke',472,563,654,745),(202,'Swimming synchronized',472,563,654,745),(203,'Swimming, treading water, fast, vigorous',590,704,817,931),(204,'Swimming, treading water, moderate',236,281,327,372),(205,'Water aerobics, water calisthenics',236,281,327,372),(206,'Water polo',590,704,817,931),(207,'Water volleyball',177,211,245,279),(208,'Water jogging',472,563,654,745),(209,'Diving, springboard or platform',177,211,245,279),(210,'Ice skating, < 9 mph',325,387,449,512),(211,'Ice skating, average speed',413,493,572,651),(212,'Ice skating, rapidly',531,633,735,838),(213,'Speed skating, ice, competitive',885,1056,1226,1396),(214,'Cross country snow skiing, slow',413,493,572,651),(215,'Cross country skiing, moderate',472,563,654,745),(216,'Cross country skiing, vigorous',531,633,735,838),(217,'Cross country skiing, racing',826,985,1144,1303),(218,'Cross country skiing, uphill',974,1161,1348,1536),(219,'Snow skiing, downhill skiing, light',295,352,409,465),(220,'Downhill snow skiing, moderate',354,422,490,558),(221,'Downhill snow skiing, racing',472,563,654,745),(222,'Sledding, tobagganing, luge',413,493,572,651),(223,'Snow shoeing',472,563,654,745),(224,'Snowmobiling',207,246,286,326),(225,'General housework',207,246,286,326),(226,'Cleaning gutters',295,352,409,465),(227,'Painting',266,317,368,419),(228,'Sit, playing with animals',148,176,204,233),(229,'Walk / run, playing with animals',236,281,327,372),(230,'Bathing dog',207,246,286,326),(231,'Mowing lawn, walk, power mower',325,387,449,512),(232,'Mowing lawn, riding mower',148,176,204,233),(233,'Walking, snow blower',207,246,286,326),(234,'Riding, snow blower',177,211,245,279),(235,'Shoveling snow by hand',354,422,490,558),(236,'Raking lawn',254,303,351,400),(237,'Gardening, general',236,281,327,372),(238,'Bagging grass, leaves',236,281,327,372),(239,'Watering lawn or garden',89,106,123,140),(240,'Weeding, cultivating garden',266,317,368,419),(241,'Carpentry, general',207,246,286,326),(242,'Carrying heavy loads',472,563,654,745),(243,'Carrying moderate loads upstairs',472,563,654,745),(244,'General cleaning',207,246,286,326),(245,'Cleaning, dusting',148,176,204,233),(246,'Taking out trash',177,211,245,279),(247,'Walking, pushing a wheelchair',236,281,327,372),(248,'Teach physical education,exercise class',236,281,327,372),(249,'Teach exercise classes (& participate)',384,457,531,605);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-19  6:28:07
