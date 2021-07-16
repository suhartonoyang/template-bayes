-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2021 at 04:07 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `template-bayes`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_training`
--

CREATE TABLE `data_training` (
  `id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `parameter_1` varchar(255) NOT NULL,
  `parameter_2` varchar(255) NOT NULL,
  `parameter_3` varchar(255) NOT NULL,
  `parameter_4` varchar(255) NOT NULL,
  `parameter_5` varchar(255) NOT NULL,
  `parameter_6` varchar(255) NOT NULL,
  `parameter_7` varchar(255) NOT NULL,
  `parameter_8` varchar(255) NOT NULL,
  `parameter_9` varchar(255) NOT NULL,
  `parameter_10` varchar(255) NOT NULL,
  `result` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_training`
--

INSERT INTO `data_training` (`id`, `type_id`, `parameter_1`, `parameter_2`, `parameter_3`, `parameter_4`, `parameter_5`, `parameter_6`, `parameter_7`, `parameter_8`, `parameter_9`, `parameter_10`, `result`) VALUES
(1, 1, 'Betina', 'Ya', 'Ya', 'Sedang', 'Parah', 'Sedang', 'Tidak Bagus', '', '', '', 'Scabies'),
(2, 1, 'Jantan', 'Ya', 'Ya', 'Sedang', 'Parah', 'Sedang', 'Tidak Bagus', '', '', '', 'Scabies'),
(3, 1, 'Jantan', 'Tidak', 'Ya', 'Tidak', 'Tidak', 'Sedikit', 'Bagus', '', '', '', 'Ringworm');

-- --------------------------------------------------------

--
-- Stand-in structure for view `data_training_v`
-- (See below for the actual view)
--
CREATE TABLE `data_training_v` (
`id` varchar(36)
,`data_training_id` int(11)
,`type_id` int(11)
,`TYPE` varchar(255)
,`parameter_id` int(11)
,`parameter_name` varchar(255)
,`parameter_seq` int(11)
,`parameter_value` varchar(255)
,`result` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `mst_map_type_parameter`
--

CREATE TABLE `mst_map_type_parameter` (
  `id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `parameter_id` int(11) NOT NULL,
  `parameter_seq` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_map_type_parameter`
--

INSERT INTO `mst_map_type_parameter` (`id`, `type_id`, `parameter_id`, `parameter_seq`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2),
(3, 1, 3, 3),
(4, 1, 4, 4),
(5, 1, 5, 5),
(6, 1, 6, 6),
(7, 1, 7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `mst_parameter`
--

CREATE TABLE `mst_parameter` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_parameter`
--

INSERT INTO `mst_parameter` (`id`, `name`) VALUES
(1, 'jenis_kelamin'),
(2, 'jamuran'),
(3, 'gatal_gatal'),
(4, 'rontok'),
(5, 'garuk_garuk_telinga'),
(6, 'kropeng'),
(7, 'nafsu_makan');

-- --------------------------------------------------------

--
-- Table structure for table `mst_type`
--

CREATE TABLE `mst_type` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_type`
--

INSERT INTO `mst_type` (`id`, `type`) VALUES
(1, 'Dog'),
(2, 'Cat');

-- --------------------------------------------------------

--
-- Structure for view `data_training_v`
--
DROP TABLE IF EXISTS `data_training_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `data_training_v`  AS SELECT uuid() AS `id`, `a`.`id` AS `data_training_id`, `a`.`type_id` AS `type_id`, `a`.`type` AS `TYPE`, `a`.`parameter_id` AS `parameter_id`, `a`.`parameter_name` AS `parameter_name`, `a`.`parameter_seq` AS `parameter_seq`, `a`.`parameter_value` AS `parameter_value`, `a`.`result` AS `result` FROM (select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_1` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 1 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_2` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 2 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_3` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 3 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_4` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 4 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_5` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 5 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_6` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 6 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_7` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 7 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_8` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 8 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_9` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 9 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id` union select `a`.`id` AS `id`,`a`.`type_id` AS `type_id`,`d`.`type` AS `type`,`b`.`parameter_id` AS `parameter_id`,`c`.`name` AS `parameter_name`,`b`.`parameter_seq` AS `parameter_seq`,`a`.`parameter_10` AS `parameter_value`,`a`.`result` AS `result` from (((`data_training` `a` join `mst_map_type_parameter` `b`) join `mst_parameter` `c`) join `mst_type` `d`) where 10 = `b`.`parameter_seq` and `b`.`parameter_id` = `c`.`id` and `a`.`type_id` = `d`.`id` and `b`.`type_id` = `d`.`id`) AS `a` ORDER BY uuid() ASC, `a`.`parameter_id` ASC, `a`.`parameter_seq` ASC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_training`
--
ALTER TABLE `data_training`
  ADD PRIMARY KEY (`id`),
  ADD KEY `data_training_fk1` (`type_id`);

--
-- Indexes for table `mst_map_type_parameter`
--
ALTER TABLE `mst_map_type_parameter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mst_map_type_parameter_fk1` (`type_id`),
  ADD KEY `mst_map_type_parameter_fk2` (`parameter_id`);

--
-- Indexes for table `mst_parameter`
--
ALTER TABLE `mst_parameter`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mst_type`
--
ALTER TABLE `mst_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_training`
--
ALTER TABLE `data_training`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mst_map_type_parameter`
--
ALTER TABLE `mst_map_type_parameter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `mst_parameter`
--
ALTER TABLE `mst_parameter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `mst_type`
--
ALTER TABLE `mst_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_training`
--
ALTER TABLE `data_training`
  ADD CONSTRAINT `data_training_fk1` FOREIGN KEY (`type_id`) REFERENCES `mst_type` (`id`);

--
-- Constraints for table `mst_map_type_parameter`
--
ALTER TABLE `mst_map_type_parameter`
  ADD CONSTRAINT `mst_map_type_parameter_fk1` FOREIGN KEY (`type_id`) REFERENCES `mst_type` (`id`),
  ADD CONSTRAINT `mst_map_type_parameter_fk2` FOREIGN KEY (`parameter_id`) REFERENCES `mst_parameter` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
