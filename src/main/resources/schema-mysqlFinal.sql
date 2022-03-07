CREATE TABLE IF NOT EXISTS `category`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `brand`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `category_brand_specification`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `brand_id`    BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
    FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
);

CREATE TABLE IF NOT EXISTS `mandatory_external_photo_tags`
(
    `category_brand_specification_id` BIGINT       NOT NULL,
    `tag`                             VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`category_brand_specification_id`) REFERENCES `category_brand_specification` (`id`)
);


CREATE TABLE IF NOT EXISTS `external_photo_tags`
(
    `category_brand_specification_id` BIGINT       NOT NULL,
    `tag`                             VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`category_brand_specification_id`) REFERENCES `category_brand_specification` (`id`)
);


CREATE TABLE IF NOT EXISTS `mandatory_internal_photo_tags`
(
    `category_brand_specification_id` BIGINT       NOT NULL,
    `tag`                             VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`category_brand_specification_id`) REFERENCES `category_brand_specification` (`id`)
);


CREATE TABLE IF NOT EXISTS `internal_photo_tags`
(
    `category_brand_specification_id` BIGINT       NOT NULL,
    `tag`                             VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`category_brand_specification_id`) REFERENCES `category_brand_specification` (`id`)
);


CREATE TABLE IF NOT EXISTS `trade_in_request`
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `email`            VARCHAR(255) NOT NULL,
    `first_name`       VARCHAR(255) NOT NULL,
    `last_name`        VARCHAR(255) NOT NULL,
    `mobile_number`    VARCHAR(255) NOT NULL,
    `shipping_country` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product`
(
    `id`                  BIGINT       NOT NULL AUTO_INCREMENT,
    `product_condition`   VARCHAR(255) NOT NULL,
    `details`             VARCHAR(255) NULL DEFAULT NULL,
    `model`               VARCHAR(255) NOT NULL,
    `photos_folder_url`   VARCHAR(255) NOT NULL,
    `request_status`      VARCHAR(255) NOT NULL,
    `brand`               BIGINT       NOT NULL,
    `category`            BIGINT       NOT NULL,
    `trade_in_request_id` BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`trade_in_request_id`) REFERENCES `trade_in_request` (`id`),
    FOREIGN KEY (`category`) REFERENCES `category` (`id`),
    FOREIGN KEY (`brand`) REFERENCES `brand` (`id`)
);

CREATE TABLE IF NOT EXISTS `shoes`
(
    `shoes_size` SMALLINT NOT NULL,
    `id`         BIGINT   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS `bag`
(
    `bag_size` VARCHAR(255) NOT NULL,
    `id`       BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `product` (`id`)
);

CREATE TABLE IF NOT EXISTS `bag_extra`
(
    `bag_id` BIGINT       NOT NULL,
    `extra`  VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`bag_id`) REFERENCES `bag` (`id`)
);


INSERT IGNORE INTO `category`(`id`, `name`)
VALUES (1, 'Bag'),
       (2, 'Shoes');

INSERT IGNORE INTO `brand`(`id`, `name`)
VALUES (1, 'Alexander McQueen'),
       (2, 'Alexander Wang'),
       (3, 'Balenciaga'),
       (4, 'Bottega Veneta'),
       (5, 'Burberry'),
       (6, 'Bvlgari'),
       (7, 'Cartier'),
       (8, 'Celine'),
       (9, 'Chanel'),
       (10, 'Chloé'),
       (11, 'Dior'),
       (12, 'Dolce & Gabbana'),
       (13, 'Fendi'),
       (14, 'Givenchy'),
       (15, 'Gucci'),
       (16, 'Hermès'),
       (17, 'Loewe'),
       (18, 'Louis Vuitton'),
       (19, 'MCM'),
       (20, 'Miu Miu'),
       (21, 'Mulberry'),
       (22, 'Off-White'),
       (23, 'Prada'),
       (24, 'Salvatore Ferragamo'),
       (25, 'Stella McCartney'),
       (26, 'The Row'),
       (27, 'Valentino'),
       (28, 'YSL');
