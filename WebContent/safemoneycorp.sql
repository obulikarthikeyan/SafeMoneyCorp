-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2014 at 06:56 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `safemoneycorp`
--
CREATE DATABASE IF NOT EXISTS `safemoneycorp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `safemoneycorp`;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `member_id` int(11) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `site_key` varchar(20) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `memberid` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `email_id` varchar(30) NOT NULL,
  `contact_no` bigint(10) NOT NULL,
  `address_1` varchar(50) NOT NULL,
  `address_2` varchar(50) DEFAULT NULL,
  `city` varchar(15) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip` bigint(5) NOT NULL,
  `ssn` bigint(9) DEFAULT NULL,
  `sec_question_1` varchar(200) NOT NULL,
  `sec_question_2` varchar(200) NOT NULL,
  `sec_question_3` varchar(200) NOT NULL,
  `sec_answer_1` varchar(25) NOT NULL,
  `sec_answer_2` varchar(25) NOT NULL,
  `sec_answer_3` varchar(25) NOT NULL,
  `date_of_birth` date NOT NULL,
  `age` int(3) DEFAULT NULL,
  `isCustomer` varchar(5) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `created_by` varchar(10) NOT NULL,
  `created_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `is_active` varchar(5) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `email_id` (`email_id`),
  KEY `member_id` (`member_id`),
  KEY `user_type_idx` (`user_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=996365 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`member_id`, `first_name`, `last_name`, `email_id`, `contact_no`, `address_1`, `address_2`, `city`, `state`, `zip`, `ssn`, `sec_question_1`, `sec_question_2`, `sec_question_3`, `sec_answer_1`, `sec_answer_2`, `sec_answer_3`, `date_of_birth`, `age`, `isCustomer`, `user_type_id`, `created_by`, `created_date`, `expiry_date`, `is_active`) VALUES
(996363, 'Alice', 'Parker', 'alice@smc.corp', 8890008765, 'xxxxxxx', 'yyyyyyyyy', 'zzzzz', 'AZ', 89963, 345678912, 'What is your favorite place?', 'What is your right eye power?', 'What is the name of your family doctor?', 'dallas', '0.0', 'Dr.Adam', '2014-10-17', 26, 'true', 322, 'SYSTEM', '2014-10-19', '2015-10-19', 'true'),
(996364, 'John', 'Doe', 'john@smc.corp', 1234509876, 'xxxxxxx', 'yyyyyyy', 'zzzzz', 'NY', 23564, 789456123, 'What is your model name of your first phone?', 'What is the last 5 digits in your driving license?', 'what is name of your favorite teacher in high school?', 'XC01', '56897', 'Jennifer', '2014-09-09', 36, 'true', 322, 'SYSTEM', '2014-10-19', '2015-10-19', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE IF NOT EXISTS `user_type` (
  `user_type_id` int(11) NOT NULL,
  `user_type` varchar(15) NOT NULL,
  `description` varchar(50) NOT NULL,
  `is_active` varchar(5) NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`user_type_id`, `user_type`, `description`, `is_active`) VALUES
(123, 'INT_BANK_ADMIN', 'Internal User - BanK Admin', 'true'),
(125, 'INT_BANK_EMP', 'Internal User - Bank Employee', 'true'),
(322, 'EXT_IND_CUST', 'External User - Individual Customer', 'true'),
(366, 'EXT_MERCHANT', 'External User - Merchant / Organization', 'true');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
