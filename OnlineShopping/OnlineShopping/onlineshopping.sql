-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2019 at 07:20 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlineshopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `addtocart`
--

CREATE TABLE `addtocart` (
  `addToCart_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `Total_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addtocart`
--

INSERT INTO `addtocart` (`addToCart_id`, `order_id`, `product_id`, `quantity`, `Total_price`) VALUES
(46, 36, 5, 1, 1000),
(47, 36, 2, 3, 1161),
(48, 36, 6, 2, 1000),
(49, 37, 5, 5, 5000),
(50, 38, 1, 1, 279),
(51, 39, 4, 1, 20000),
(52, 40, 3, 1, 36),
(53, 41, 3, 1, 36),
(54, 42, 4, 1, 20000),
(55, 43, 4, 1, 20000),
(56, 44, 1, 1, 279),
(57, 45, 1, 1, 279),
(58, 46, 1, 1, 279),
(59, 47, 1, 1, 279),
(60, 48, 1, 1, 279),
(61, 49, 1, 1, 279),
(62, 50, 1, 1, 279),
(63, 51, 4, 1, 20000),
(64, 52, 1, 1, 279),
(65, 53, 4, 1, 20000),
(66, 54, 3, 1, 36),
(68, 55, 5, 1, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(20) NOT NULL,
  `category_code` varchar(20) NOT NULL,
  `category_description` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_code`, `category_description`) VALUES
(1, 'Cadbury', 'Cadbury Celebration Gift'),
(2, 'Air Conditioner', 'Air Condenser'),
(3, 'Bag', 'Casual Laptop Backpack');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phonenumber` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `name`, `username`, `password`, `phonenumber`) VALUES
(1, 'shital', 'shital', 'abc', '1212121'),
(2, 'mergu', 'mergu', 'mergu', 'mergu'),
(5, 'ashish', 'ashish', 'ashish', '777777'),
(6, 'sachin', 'sachin', 'sachin', '9999999'),
(7, 'pooja', 'pooja', 'pooja', '8999123333');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(20) NOT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(50) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `total_price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_date`, `order_status`, `user_name`, `total_price`) VALUES
(36, '2019-11-28', 'Ordered', 'shital', 3161),
(37, '2019-11-28', 'Ordered', 'shital', 5000),
(38, '2019-11-28', 'Ordered', 'shital', 279),
(39, '2019-11-28', 'Ordered', 'shital', 20000),
(40, '2019-11-28', 'Ordered', 'pooja', 36),
(41, '2019-11-28', 'Ordered', 'shital', 36),
(42, '2019-11-28', 'Ordered', 'shital', 20000),
(43, '2019-11-28', 'Ordered', 'shital', 20000),
(44, '2019-11-28', 'Ordered', 'shital', 279),
(45, '2019-11-28', 'Ordered', 'shital', 279),
(46, '2019-11-28', 'Ordered', 'shital', 279),
(47, '2019-11-28', 'Ordered', 'shital', 279),
(48, '2019-11-28', 'Ordered', 'shital', 279),
(49, '2019-11-28', 'Ordered', 'shital', 279),
(50, '2019-11-28', 'Ordered', 'shital', 279),
(51, '2019-11-28', 'Ordered', 'shital', 20000),
(52, '2019-11-28', 'Ordered', 'shital', 279),
(53, '2019-11-29', 'Ordered', 'mergu', 20000),
(54, '2019-11-29', 'Ordered', 'mergu', 36),
(55, '2019-11-29', 'Ordered', 'shital', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productId` int(11) NOT NULL,
  `productCode` varchar(20) NOT NULL,
  `description` varchar(250) NOT NULL,
  `price` float NOT NULL,
  `manfDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productId`, `productCode`, `description`, `price`, `manfDate`) VALUES
(1, 'Chocolate Gift Pack', 'cadbury celebration chocolates gift pack', 279, '2019-09-10'),
(2, 'Dry Fruit Chocolate', 'Cadbury Celebrations Rich Dry Fruit Chocolate Gift Box', 387, '2019-11-04'),
(3, 'Blue Star ', 'Copper, IC315AATU, White', 36, '2019-10-17'),
(4, 'Whirlpool 0.8 Ton 3 ', 'Inverter Split AC (Copper, 0.8T Magicool 3S COPR Inverter, White)', 20000, '2019-11-06'),
(5, 'American Tourister', 'Blue Casual Backpack', 1000, '2019-10-25'),
(6, 'Lenovo ', 'Laptop bag', 500, '2019-11-04');

-- --------------------------------------------------------

--
-- Table structure for table `product1`
--

CREATE TABLE `product1` (
  `product_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `manf_date` datetime DEFAULT NULL,
  `price` float DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `productId` int(30) NOT NULL,
  `category_id` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`productId`, `category_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(6, 3),
(5, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addtocart`
--
ALTER TABLE `addtocart`
  ADD PRIMARY KEY (`addToCart_id`),
  ADD KEY `productOrder` (`product_id`),
  ADD KEY `order` (`order_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productId`);

--
-- Indexes for table `product1`
--
ALTER TABLE `product1`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD KEY `prod` (`productId`),
  ADD KEY `cate` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addtocart`
--
ALTER TABLE `addtocart`
  MODIFY `addToCart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `addtocart`
--
ALTER TABLE `addtocart`
  ADD CONSTRAINT `order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `productOrder` FOREIGN KEY (`product_id`) REFERENCES `product` (`productId`);

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `cate` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `prod` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
