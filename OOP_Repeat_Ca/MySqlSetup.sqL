-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2025 at 08:34 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `weapondb`
--

-- --------------------------------------------------------

--
-- Table structure for table `weapon`
--

CREATE TABLE `weapon` (
`id` int(11) NOT NULL,
`name` varchar(100) NOT NULL,
`type` varchar(11) NOT NULL,
`weight` double NOT NULL,
`durability` int(11) NOT NULL,
`attack` int(11) NOT NULL,
`motivity` varchar(1) NOT NULL,
`technique` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `weapon`
--

INSERT INTO `weapon` (`id`, `name`, `type`, `weight`, `durability`, `attack`, `motivity`, `technique`) VALUES
(1, 'Death Talons', 'Sword', 9, 60, 109, 'D', 'A'),
(2, 'Monads Rose Sword', 'Sword', 13.6, 110, 165, 'B', 'B'),
(3, 'Pale Knight', 'Greatsword', 14.6, 100, 118, 'B', 'C'),
(4, 'Royal Hornbow', 'Dagger', 7.4, 84, 75, 'D', 'B'),
(5, 'Azure Dragon Crescent Glaive', 'Greatsword', 17.9, 100, 128, 'C', 'B'),
(6, 'Etiquette', 'Dagger', 7.9, 100, 80, 'D', 'A'),
(7, 'Frozen Feast', 'Greatsword', 31.1, 84, 167, 'B', 'C'),
(8, 'Golden Lie', 'Blunt', 12.3, 84, 94, 'C', 'C'),
(9, 'Holy Sword of the Ark', 'Greatsword', 21.2, 100, 140, 'B', 'C'),
(10, 'Noblesse Oblige', 'Large Blunt', 24.7, 84, 153, 'A', 'D'),
(11, 'Proof of Humanity', 'Sword', 20.5, 84, 100, 'B', 'B'),
(12, 'Puppet Ripper', 'Sword', 16.4, 100, 132, 'C', 'B'),
(13, 'Seven Coil Spring Sword', 'Greatsword', 18.7, 100, 129, 'B', 'C'),
(14, 'Trident of the Covenant', 'Blunt', 16.4, 117, 115, 'C', 'B'),
(15, 'Two Dragons Sword', 'Sword', 12.9, 100, 107, 'D', 'A'),
(16, 'Uroboross Eye', 'Sword', 16.5, 100, 124, 'C', 'B');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `weapon`
--
ALTER TABLE `weapon`
    ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
