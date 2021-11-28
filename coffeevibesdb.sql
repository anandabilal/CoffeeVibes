-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2021 at 05:18 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeevibesdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `salary` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeId`, `positionId`, `name`, `status`, `salary`, `username`, `password`) VALUES
(1, 1, 'Budi Sentosa', 'Working', 1000, 'budisentosa', 'budisentosa'),
(2, 2, 'Sarah Fariha', 'Working', 2000, 'sarahfariha', 'sarahfariha'),
(3, 3, 'Eko Sebastian', 'Working', 3000, 'ekosebastian', 'ekosebastian'),
(4, 4, 'Jaya Hartono', 'Working', 4000, 'jayahartono', 'jayahartono'),
(5, 1, 'Kartika Dewi', 'Working', 1010, 'kartikadewi', 'kartikadewi'),
(6, 2, 'Wulandari', 'Working', 2020, 'wulandari', 'wulandari'),
(7, 3, 'Bayu Reza', 'Working', 3010, 'bayureza', 'bayureza');

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `positionId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`positionId`, `name`) VALUES
(1, 'Barista'),
(2, 'Product Admin'),
(3, 'Manager'),
(4, 'Human Resource Department');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productId`, `name`, `description`, `price`, `stock`) VALUES
(1, 'Espresso', 'Espresso is both the name of a coffee beverage and the method of brewing coffee that originated in Italy', 11000, 6),
(2, 'Cappuccino', 'Cappuccino is an Italian coffee made with espresso and steam-foamed milk', 20000, 8),
(3, 'Cortado', 'Cortado is a Spanish beverage made by pouring a small amount of espresso in a small glass cup', 24700, 7),
(4, 'Café au lait', 'This French version of coffee and warmed milk is mostly made with brewed coffee, traditionally using the French press', 40500, 1),
(5, 'Ristretto', 'Ristretto, which means restricted in Italian, is half of a single shot of espresso', 21200, 5),
(6, 'Turkish Coffee', 'Extra finely ground roasted coffee beans are combined with cold water and, optionally, sugar', 13500, 15),
(7, 'Frappé', 'Made by combining instant coffee with water and ice', 12000, 18),
(8, 'Flat White', 'Flat white is a coffee variety that couples a double shot of freshly brewed espresso and milk', 35000, 0),
(9, 'Caffè Americano', 'Italian caffè Americano is prepared by adding hot water to an already extracted espresso shot', 32500, 7),
(10, 'Long Black', 'Long black is a coffee variety that is often considered to be a close relative of caffé Americano', 17520, 9),
(11, 'Ipoh White Coffee', 'Roasted technique in which coffee beans are lightly roasted in margarine before they are ground and brewed', 21000, 15),
(12, 'Galão', 'Galão is rich and creamy, though the coffee flavor is not overpowering', 27500, 18),
(13, 'Macchiato', 'A coffee variety that is made by pulling a shot of espresso and topping it with only one or two teaspoons of steamed milk', 35000, 17),
(14, 'Wiener melange', 'Wiener melange is a specialty blend of coffee and milk that is more similar to caffe latte', 21500, 11),
(15, 'Eiskaffee', 'This German-style ice coffee is a combination of chilled brewed coffee and ice cream', 18000, 2),
(16, 'Arabic Coffee', 'The beverage is characterized by its dark color and a uniquely strong and bitter flavor and odor', 24300, 22),
(17, 'Kopi tubruk', 'Thick and rich kopi tubruk is the most popular coffee in Indonesia', 7000, 5),
(18, 'Kopi luwak', 'It is made from coffee beans that are digested and excreted by the civet', 15000, 21);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionId` int(11) NOT NULL,
  `employeeId` int(11) NOT NULL,
  `voucherId` int(11) DEFAULT NULL,
  `purchaseDate` date NOT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transactionId`, `employeeId`, `voucherId`, `purchaseDate`, `totalPrice`) VALUES
(1, 1, NULL, '2021-11-16', 72350),
(2, 1, NULL, '2021-11-16', 72312),
(3, 1, NULL, '2021-11-16', 36000),
(4, 1, NULL, '2021-11-16', 154500),
(5, 1, NULL, '2021-11-16', 24000),
(6, 1, 5, '2021-11-16', 92196),
(7, 1, 2, '2021-11-18', 84630);

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `transactionId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`transactionId`, `productId`, `quantity`) VALUES
(1, 2, 2),
(1, 3, 1),
(1, 6, 3),
(1, 7, 1),
(1, 12, 1),
(2, 2, 1),
(2, 6, 1),
(2, 7, 1),
(2, 8, 1),
(2, 16, 1),
(3, 7, 3),
(4, 7, 3),
(4, 12, 3),
(4, 15, 2),
(5, 7, 2),
(6, 2, 1),
(6, 5, 1),
(6, 7, 1),
(6, 9, 2),
(7, 2, 2),
(7, 6, 2),
(7, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `voucher`
--

CREATE TABLE `voucher` (
  `voucherId` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `voucher`
--

INSERT INTO `voucher` (`voucherId`, `discount`, `status`) VALUES
(1, 5, 'available'),
(2, 7, 'expired'),
(3, 10, 'available'),
(4, 15, 'available'),
(5, 22, 'expired'),
(6, 25, 'available'),
(7, 32, 'available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`),
  ADD KEY `FK_employee_positionId` (`positionId`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`positionId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionId`),
  ADD KEY `FK_transaction_employeeId` (`employeeId`),
  ADD KEY `FK_transaction_voucherId` (`voucherId`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD KEY `FK_transactionDetail_transactionId` (`transactionId`),
  ADD KEY `FK_transactionDetail_productId` (`productId`);

--
-- Indexes for table `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`voucherId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `positionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `voucher`
--
ALTER TABLE `voucher`
  MODIFY `voucherId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_cart_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_employee_positionId` FOREIGN KEY (`positionId`) REFERENCES `position` (`positionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_transaction_employeeId` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_transaction_voucherId` FOREIGN KEY (`voucherId`) REFERENCES `voucher` (`voucherId`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD CONSTRAINT `FK_transactionDetail_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_transactionDetail_transactionId` FOREIGN KEY (`transactionId`) REFERENCES `transaction` (`transactionId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
