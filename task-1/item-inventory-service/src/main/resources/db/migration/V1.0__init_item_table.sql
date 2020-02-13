CREATE TABLE `iteminventorydb`.`item` (
	`item_no` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
    `man_id` INT NOT NULL,
    `qty` INT NOT NULL,
    `price` INT NOT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY(`item_no`)
);

CREATE TABLE `iteminventorydb`.`image_link`(
	`id` BIGINT AUTO_INCREMENT,
    `link` VARCHAR(255) NOT NULL,
    `item_no` BIGINT,
	CONSTRAINT PK_IMAGE_LINK PRIMARY KEY(`id`),
    CONSTRAINT FK_ITEM_IMAGE_LINK Foreign KEY (`item_no`) REFERENCES `item` (`item_no`)
);
