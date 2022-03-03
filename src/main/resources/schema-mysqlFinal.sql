USE TradeIn;

CREATE TABLE IF NOT EXISTS `TradeIn`.`trade_in_request`
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `email`            VARCHAR(255) NOT NULL,
    `first_name`       VARCHAR(255) NOT NULL,
    `last_name`        VARCHAR(255) NOT NULL,
    `mobile_number`    VARCHAR(255) NOT NULL,
    `shipping_country` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `TradeIn`.`product`
(
    `id`                  BIGINT       NOT NULL AUTO_INCREMENT,
    `brand`               VARCHAR(255) NOT NULL,
    `category`            VARCHAR(255) NOT NULL,
    `product_condition`   VARCHAR(255) NOT NULL,
    `details`             VARCHAR(255) NULL DEFAULT NULL,
    `model`               VARCHAR(255) NOT NULL,
    `photos_folder_url`   VARCHAR(255) NOT NULL,
    `request_status`      VARCHAR(255) NOT NULL,
    `trade_in_request_id` BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`trade_in_request_id`) REFERENCES `TradeIn_dev`.`trade_in_request` (`id`)
);


CREATE TABLE IF NOT EXISTS `TradeIn`.`shoes`
(
    `shoes_size` SMALLINT NOT NULL,
    `id`         BIGINT   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `TradeIn_dev`.`product` (`id`)
);

CREATE TABLE IF NOT EXISTS `TradeIn`.`bag`
(
    `bag_size` VARCHAR(255) NOT NULL,
    `id`       BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `TradeIn_dev`.`product` (`id`)
);


CREATE TABLE IF NOT EXISTS `TradeIn`.`bag_extra`
(
    `bag_id` BIGINT       NOT NULL,
    `extra`  VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY (`bag_id`) REFERENCES `TradeIn_dev`.`bag` (`id`)
);

