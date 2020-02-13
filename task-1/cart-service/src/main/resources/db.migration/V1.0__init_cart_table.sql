CREATE TABLE `cartdb`.`cart_aggregate` (
  `cart_id` BIGINT AUTO_INCREMENT,
  `cart_status` VARCHAR(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  CONSTRAINT PK_CART PRIMARY KEY(`cart_id`)
);

CREATE TABLE `cartdb`.`item` (
  `item_no` BIGINT,
  `price` INT NOT NULL,
  `qty` INT NOT NULL,
  `cart_id` BIGINT NOT NULL,
  CONSTRAINT PK_CART_ITEM PRIMARY KEY(`cart_id`, `item_no`),
  CONSTRAINT FK_CART_ITEM FOREIGN KEY (`cart_id`) REFERENCES `cart_aggregate` (`cart_id`)
)