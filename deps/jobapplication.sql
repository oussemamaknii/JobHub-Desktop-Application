-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2021 at 02:31 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.3.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jobapplication`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidate_resume`
--

CREATE TABLE `candidate_resume` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `resume_headline` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `skills` tinytext COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '(DC2Type:array)',
  `experience` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `candidate_resume`
--

INSERT INTO `candidate_resume` (`id`, `user_id`, `resume_headline`, `skills`, `experience`) VALUES
(21, 8, 'aa', 's:2:\"aa\";', 'aa');

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `couleur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descriptionc` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `couleur` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `titre`, `descriptionc`, `couleur`) VALUES
(1, 'aa', 'aa', '#9e4c4c');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` int(11) NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `idEvent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `website` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `founded_date` date NOT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `company_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contact_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact_phone` int(11) DEFAULT NULL,
  `company_adress` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `facebook_link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `twitter_link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company_image_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stars` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `website`, `founded_date`, `category`, `country`, `description`, `user_id`, `status`, `company_name`, `contact_email`, `contact_phone`, `company_adress`, `facebook_link`, `twitter_link`, `company_image_name`, `stars`) VALUES
(10, 'aa', '2016-01-01', 'aa', 'aa', 'aa', 9, 1, 'aa', 'aa', 29856547, 'Gafsa, Tunisia', 'http://aa', 'http://aa', 'img6-6064ce9606e5c.jpg', 1),
(12, 'aa', '2016-01-01', 'aa', 'aa', 'aa', 13, 1, 'aa', 'aa', 29856547, 'Tunis, Tunisia', 'http://aa', 'http://aa', 'img6-60682dd7e44b4.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `demande_recrutement`
--

CREATE TABLE `demande_recrutement` (
  `id` int(11) NOT NULL,
  `offre_id` int(11) NOT NULL,
  `candidat_id` int(11) DEFAULT NULL,
  `date_debut` date NOT NULL,
  `dateexpiration` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `education`
--

CREATE TABLE `education` (
  `id` int(11) NOT NULL,
  `resume_id` int(11) NOT NULL,
  `course` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `institute` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prix` double NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbre_place` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `formateur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel` double NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `formation`
--

INSERT INTO `formation` (`id`, `category_id`, `nom`, `formateur`, `description`, `date_debut`, `date_fin`, `adresse`, `mail`, `tel`, `prix`) VALUES
(1, NULL, 'aa', 'aa', 'aa', '2021-05-01', '2022-10-01', 'aa', 'balti.oumaima@esprit.tn', 29856547, 500),
(2, 1, 'aa', 'aa', 'aa', '2022-01-01', '2025-01-01', 'aa', 'balti.oumaima@esprit.tn', 29856547, 0);

-- --------------------------------------------------------

--
-- Table structure for table `offre_emploi`
--

CREATE TABLE `offre_emploi` (
  `id` int(11) NOT NULL,
  `id_candidat_id` int(11) DEFAULT NULL,
  `id_recruteur_id` int(11) DEFAULT NULL,
  `categorie_id` int(11) NOT NULL,
  `titre` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `poste` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_debut` date NOT NULL,
  `date_expiration` date NOT NULL,
  `max_salary` int(11) DEFAULT NULL,
  `min_salary` int(11) DEFAULT NULL,
  `location` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `total_payment` double NOT NULL,
  `state` tinyint(1) NOT NULL,
  `date` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ref` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_cart`
--

CREATE TABLE `product_cart` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `idOrder` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `candidate` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stars` double NOT NULL,
  `owner` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `candidate`, `title`, `description`, `stars`, `owner`) VALUES
(1, 10, 'sqdqd', 'sqdsqdsq', 2, 9),
(2, 10, '', '', 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_of_birth` date NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `activated_at` datetime DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `professional_title` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `is_active`, `roles`, `first_name`, `last_name`, `token`, `date_of_birth`, `created_at`, `updated_at`, `activated_at`, `phone`, `image_name`, `adresse`, `professional_title`) VALUES
(3, 'company@test.tn', '$2y$13$sZSvLmGFA1LEAELJZkuKYuzs7FWg9fMUmTH/StRt7nmur041b0j0G', 1, 'a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:9:\"ROLE_USER\";}', 'company', 'company', NULL, '1901-01-05', '2021-03-23 11:24:18', '2021-03-31 21:21:58', '2021-03-23 11:24:19', 29856547, '19143974_1035199573281346_4219495649777378571_o-6044160aa5df0.jpg', 'za', 'dsd'),
(4, 'test@testn.tn', '$2y$13$MI/dh4tSPjhjutZDJ5I2yeHlJKT8YvmCp9YZK4k1kc3/bsTayjFBS', 1, 'a:2:{i:0;s:10:\"ROLE_ADMIN\";i:1;s:9:\"ROLE_USER\";}', 'test', 'test', NULL, '2016-01-01', '2021-03-23 11:32:08', '2021-03-28 21:53:20', '2021-03-23 11:32:09', 2147483647, '19143974_1035199573281346_4219495649777378571_o-6044160aa5df0.jpg', NULL, NULL),
(5, 'nacef.otay@esprit.tn', '$2y$13$Q28g3YXH.M1tWFfvvjKCWOEI0bK3yofOMRvbQ0l/3.zK0llEvWiQq', 1, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'aaa', 'magiko', NULL, '2016-01-01', '2021-03-26 21:58:08', '2021-03-27 00:20:29', '2021-03-26 21:58:09', 2147483647, '19143974_1035199573281346_4219495649777378571_o-6044160aa5df0.jpg', NULL, NULL),
(6, 'aa@aa.aaa', '$2y$13$pSTxv9sG02K7zA8r8WrAWu5bR4Na5UFtgmazYJ6lQQ7/7mC5bWs0e', 0, 'a:1:{i:0;s:9:\"ROLE_USER\";}', NULL, NULL, NULL, '2016-09-01', '2021-03-27 20:33:35', '2021-03-31 14:42:11', '2021-03-27 20:33:35', 29856547, '19143974_1035199573281346_4219495649777378571_o-6044160aa5df0.jpg', NULL, NULL),
(8, 'nacef@test.test', '$2y$13$/BgkSFvJBBTGabaSHLhq9ejmVkpIcbB3c2TI9FyE9ykHDWDnEnXyS', 1, 'a:2:{i:0;s:12:\"ROLE_COMPANY\";i:1;s:9:\"ROLE_USER\";}', 'nacef', 'otay', 'dVa7mzuqEeRlPdM5ykBPx9MBdd_7k-L2exYslMcZ3vE', '2016-05-18', '2021-03-30 00:01:56', '2021-03-30 12:02:31', NULL, 29856547, 'img5-6062f7375e455.jpg', 'Area 51, NV, USA', 'aa'),
(9, 'rayen@test.tn', '$2y$13$KJUJVeE2uRkO5avjunElZedmWsiWBTGIdFKghIr2Rb4iU/Imi27Au', 1, 'a:2:{i:0;s:12:\"ROLE_COMPANY\";i:1;s:9:\"ROLE_USER\";}', NULL, NULL, 'oD9j_MCrGg_X_Uqy1tPRAYRJqVCVl9b51xe5wSjqZHc', '1901-01-01', '2021-03-30 13:52:54', '2021-03-31 21:33:54', NULL, 29856547, 'cap-606311164f7f7.png', 'Tunis, Tunisie', 'AA'),
(10, 'aa@test.test', '$2y$13$ZjBJf9KMYfnNcKRmNo3Y8eLGI6mXQegAcLscL6OYf5hSdH4q78btC', 0, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'aa', 'aa', 'a-bquRMKSSxNN1Mzf39Mx8qejU1dtCcbNpZQwAWH5wA', '1901-01-01', '2021-03-31 14:36:39', '2021-03-31 15:00:21', NULL, 29856547, NULL, 'Tulum, Quintana Roo, Mexico', NULL),
(13, 'merghed.rayen@esprit.tn', '$2y$13$oeV7OEPLyaarSKCPjbxPPeha2ZvWoXL96nWqGAJ6Ce1diHIac/10O', 1, 'a:2:{i:0;s:12:\"ROLE_COMPANY\";i:1;s:9:\"ROLE_USER\";}', 'Rayen', 'Rayen', 'Y8aZ-pGoqTN_f-E5y69-X3Ff_x3kFHhBYjSv-Nm0FeQ', '1901-01-01', '2021-04-01 13:32:21', '2021-04-03 10:57:06', '2021-04-01 13:33:22', 29856547, 'img4-6065af456979e.jpg', 'Tunis, Tunisia', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidate_resume`
--
ALTER TABLE `candidate_resume`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_D4F28E7CA76ED395` (`user_id`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9474526C2C6A49BA` (`idEvent`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_4FBF094FA76ED395` (`user_id`);

--
-- Indexes for table `demande_recrutement`
--
ALTER TABLE `demande_recrutement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_84CC7C6D4CC8505A` (`offre_id`),
  ADD KEY `IDX_84CC7C6D8D0EB82` (`candidat_id`);

--
-- Indexes for table `education`
--
ALTER TABLE `education`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_DB0A5ED2D262AF09` (`resume_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_404021BF12469DE2` (`category_id`);

--
-- Indexes for table `offre_emploi`
--
ALTER TABLE `offre_emploi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_132AD0D110C22675` (`id_candidat_id`),
  ADD KEY `IDX_132AD0D198C92C83` (`id_recruteur_id`),
  ADD KEY `IDX_132AD0D1BCF5E72D` (`categorie_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_cart`
--
ALTER TABLE `product_cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_864BAA16E2EDD085` (`idOrder`),
  ADD UNIQUE KEY `UNIQ_864BAA16C3F36F5F` (`idProduct`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_D8892622C8B28E44` (`candidate`),
  ADD KEY `IDX_D8892622CF60E67C` (`owner`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidate_resume`
--
ALTER TABLE `candidate_resume`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `demande_recrutement`
--
ALTER TABLE `demande_recrutement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `education`
--
ALTER TABLE `education`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `offre_emploi`
--
ALTER TABLE `offre_emploi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_cart`
--
ALTER TABLE `product_cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `candidate_resume`
--
ALTER TABLE `candidate_resume`
  ADD CONSTRAINT `FK_D4F28E7CA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526C2C6A49BA` FOREIGN KEY (`idEvent`) REFERENCES `event` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `company`
--
ALTER TABLE `company`
  ADD CONSTRAINT `FK_4FBF094FA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `demande_recrutement`
--
ALTER TABLE `demande_recrutement`
  ADD CONSTRAINT `FK_84CC7C6D4CC8505A` FOREIGN KEY (`offre_id`) REFERENCES `offre_emploi` (`id`),
  ADD CONSTRAINT `FK_84CC7C6D8D0EB82` FOREIGN KEY (`candidat_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `education`
--
ALTER TABLE `education`
  ADD CONSTRAINT `FK_DB0A5ED2D262AF09` FOREIGN KEY (`resume_id`) REFERENCES `candidate_resume` (`id`);

--
-- Constraints for table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `FK_404021BF12469DE2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `offre_emploi`
--
ALTER TABLE `offre_emploi`
  ADD CONSTRAINT `FK_132AD0D110C22675` FOREIGN KEY (`id_candidat_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_132AD0D198C92C83` FOREIGN KEY (`id_recruteur_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_132AD0D1BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `product_cart`
--
ALTER TABLE `product_cart`
  ADD CONSTRAINT `FK_864BAA16C3F36F5F` FOREIGN KEY (`idProduct`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FK_864BAA16E2EDD085` FOREIGN KEY (`idOrder`) REFERENCES `order` (`id`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `FK_D8892622C8B28E44` FOREIGN KEY (`candidate`) REFERENCES `company` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_D8892622CF60E67C` FOREIGN KEY (`owner`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
