-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2014 at 06:16 AM
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
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `account_no` bigint(10) NOT NULL,
  `member_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `is_active` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`account_no`),
  KEY `accountAndUser` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_no`, `member_id`, `amount`, `is_active`) VALUES
(1545151, 996368, 5000, 'true'),
(6987456, 996364, 5000, 'true'),
(8765433, 996369, 400, 'true'),
(56352145, 996363, 550.2799999999997, 'true');

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

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`member_id`, `user_name`, `password`, `site_key`) VALUES
(996363, 'cust', 'cust', 'eclipse'),
(996364, 'mer', 'mer', 'helio'),
(996368, 'emp', 'emp', 'helio'),
(996369, 'admin', 'admin', 'helio');

-- --------------------------------------------------------

--
-- Table structure for table `payment_request`
--

CREATE TABLE IF NOT EXISTS `payment_request` (
  `payment_id` bigint(12) NOT NULL,
  `merchant_account_id` bigint(10) NOT NULL,
  `merchant_member_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` double NOT NULL,
  `merchant_first_name` varchar(25) NOT NULL,
  `merchant_last_name` varchar(25) NOT NULL,
  `authorizer_member_id` int(11) NOT NULL,
  `authorizer_account_id` bigint(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `memberId` (`merchant_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `request_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `request_type` varchar(45) DEFAULT NULL,
  `authorizing_member_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `authorizing_authority` varchar(20) NOT NULL,
  `authority_user_type_id` int(11) NOT NULL,
  `request_date` date NOT NULL,
  `processed_date` date DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `request_user` (`member_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56349 ;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` bigint(10) NOT NULL,
  `member_id` int(11) NOT NULL,
  `from_account` bigint(10) DEFAULT NULL,
  `to_account` bigint(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `transaction_type` varchar(45) DEFAULT NULL,
  `is_critical` tinyint(1) DEFAULT NULL,
  `is_authorized` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `transaction_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `member_id`, `from_account`, `to_account`, `date`, `amount`, `status`, `transaction_type`, `is_critical`, `is_authorized`) VALUES
(1234564, 996363, 56352145, 56352145, '2014-10-27 01:18:20', 300, 'APPROVED', 'Debit', 0, 1),
(3305011, 996363, 56352145, 56352145, '2014-10-27 21:10:17', 3000, 'Pending', 'Credit', 1, 0),
(3571418, 996363, 56352145, 56352145, '2014-10-27 02:41:12', 98, 'APPROVED', 'Debit', 0, 1),
(3875286, 996363, 56352145, 56352145, '2014-10-27 20:50:31', 100, 'APPROVED', 'Debit', 0, 1),
(4116140, 996368, 56352145, 1545151, '2014-10-27 21:14:07', 1000, 'APPROVED', 'Credit', 0, 1),
(4207114, 996363, 56352145, 56352145, '2014-10-27 20:50:03', 100, 'APPROVED', 'Credit', 0, 1),
(4490948, 996363, 56352145, 56352145, '2014-10-27 20:55:40', 10000, 'Pending', 'Credit', 1, 0),
(4817206, 996363, 56352145, 56352145, '2014-10-27 20:43:29', 1000, 'APPROVED', 'Credit', 0, 1),
(4825394, 996363, 56352145, 56352145, '2014-10-27 20:50:45', 100, 'APPROVED', 'Credit', 0, 1),
(4830852, 996363, 56352145, 56352145, '2014-10-27 20:53:12', 1000, 'APPROVED', 'Credit', 0, 1),
(4898306, 996363, 56352145, 56352145, '2014-10-27 20:53:01', 1000, 'APPROVED', 'Debit', 0, 1),
(4911640, 996363, 56352145, 56352145, '2014-10-27 20:42:07', 100, 'APPROVED', 'Debit', 0, 1),
(5078599, 996363, 56352145, 1545151, '2014-10-27 21:14:06', 1000, 'APPROVED', 'Debit', 0, 1),
(5083286, 996363, 56352145, 56352145, '2014-10-27 21:10:08', 1000, 'APPROVED', 'Credit', 0, 1),
(5117827, 996363, 56352145, 56352145, '2014-10-27 20:45:57', 100, 'APPROVED', 'Debit', 0, 1),
(5130745, 996363, 56352145, 56352145, '2014-10-27 20:45:42', 1000, 'APPROVED', 'Credit', 0, 1),
(5222875, 996363, 56352145, 56352145, '2014-10-27 02:23:36', 100, 'APPROVED', 'Debit', 0, 1),
(5226408, 996368, 56352145, 1545151, '2014-10-27 21:18:19', 1000, 'APPROVED', 'Credit', 0, 1),
(5357242, 996363, 56352145, 56352145, '2014-10-27 20:55:32', 10000, 'Pending', 'Credit', 1, 0),
(5380784, 996363, 56352145, 56352145, '2014-10-27 20:50:57', 1000, 'APPROVED', 'Debit', 0, 1),
(5566677, 996363, 56352145, 56352145, '2014-10-27 20:40:06', 100, 'APPROVED', 'Credit', 0, 1),
(5589927, 996363, 56352145, 56352145, '2014-10-27 20:41:54', 1, 'APPROVED', 'Debit', 0, 1),
(5651034, 996363, 56352145, 56352145, '2014-10-27 20:42:52', 2500, 'Pending', 'Debit', 1, 0),
(5651307, 996363, 56352145, 56352145, '2014-10-27 20:41:08', 100, 'APPROVED', 'Credit', 0, 1),
(5772612, 996363, 56352145, 56352145, '2014-10-27 20:42:19', 150, 'APPROVED', 'Credit', 0, 1),
(5837203, 996363, 56352145, 56352145, '2014-10-27 14:08:12', 300, 'APPROVED', 'Debit', 0, 1),
(5869221, 996363, 56352145, 1545151, '2014-10-27 21:18:18', 1000, 'APPROVED', 'Debit', 0, 1),
(5959976, 996363, 56352145, 56352145, '2014-10-27 20:46:06', 100, 'APPROVED', 'Credit', 0, 1),
(6121071, 996363, 56352145, 56352145, '2014-10-27 14:08:17', 300, 'APPROVED', 'Debit', 0, 1),
(6164320, 996363, 56352145, 56352145, '2014-10-27 20:49:54', 100, 'APPROVED', 'Credit', 0, 1),
(6329804, 996363, 56352145, 56352145, '2014-10-27 21:09:59', 100, 'APPROVED', 'Credit', 0, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=996379 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`member_id`, `first_name`, `last_name`, `email_id`, `contact_no`, `address_1`, `address_2`, `city`, `state`, `zip`, `ssn`, `sec_question_1`, `sec_question_2`, `sec_question_3`, `sec_answer_1`, `sec_answer_2`, `sec_answer_3`, `date_of_birth`, `age`, `isCustomer`, `user_type_id`, `created_by`, `created_date`, `expiry_date`, `is_active`) VALUES
(996363, 'Alice', 'Parker', 'alice@smc.corp', 8890008765, 'xxxxxxx', 'yyyyyyyyy', 'zzzzz', 'AZ', 89963, 345678912, 'What is your favorite place?', 'What is your right eye power?', 'What is the name of your family doctor?', 'dallas', '0.0', 'Dr.Adam', '2014-10-17', 26, 'true', 322, 'SYSTEM', '2014-10-19', '2015-10-19', 'true'),
(996364, 'John', 'Doe', 'john@smc.corp', 1234509876, 'xxxxxxx', 'yyyyyyy', 'zzzzz', 'NY', 23564, 789456123, 'What is your model name of your first phone?', 'What is the last 5 digits in your driving license?', 'what is name of your favorite teacher in high school?', 'XC01', '56897', 'Jennifer', '2014-09-09', 36, 'true', 366, 'SYSTEM', '2014-10-19', '2015-10-19', 'true'),
(996368, 'Bank', 'Employee', 'bank.employee@smcorp', 1234509876, 'xxxxxxx', 'yyyyyyy', 'zzzzz', 'NY', 23564, 789456123, 'What is your model name of your first phone?', 'What is the last 5 digits in your driving license?', 'what is name of your favorite teacher in high school?', 'XC01', '56897', 'Jennifer', '2014-09-09', 36, 'true', 125, 'SYSTEM', '2014-10-19', '2015-10-19', 'true'),
(996369, 'Bank', 'Admin', 'bank.admin@smcorp', 8890008765, 'xxxxxxx', 'yyyyyyyyy', 'zzzzz', 'AZ', 89963, 345678912, 'What is your favorite place?', 'What is your right eye power?', 'What is the name of your family doctor?', 'dallas', '0.0', 'Dr.Adam', '2014-10-17', 26, 'true', 123, 'SYSTEM', '2014-10-19', '2015-10-19', 'true');

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
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `memberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment_request`
--
ALTER TABLE `payment_request`
  ADD CONSTRAINT `payment_request_ibfk_1` FOREIGN KEY (`merchant_member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `requestMemberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transactionMemberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
