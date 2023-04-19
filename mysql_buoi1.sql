/**
Cú pháp tạo bảng:
	create table tenbang(
		`tencot` kieudulieu rangbuoc
        rangbuockhac
    )
Cú pháp thêm dữ liệu:
	insert into tenbang(tencot1, tencot2) values (giatricot1, giatricot2);
    
**/
-- câu lệnh truy vấn: * lấy tất cả các cột
SELECT * FROM c0123_customer.customer;
SELECT name from customer;

-- cập nhật cột name là notnull
ALTER TABLE `customer` 
CHANGE COLUMN `name` `name` VARCHAR(45) NULL ;


-- thêm cột email và ràng buộc email là not null
ALTER TABLE `c0123_customer`.`customer` 
ADD COLUMN `email` VARCHAR(45) NOT NULL AFTER `name`;


ALTER TABLE `customer`
ADD COLUMN `email` VARCHAR(45) UNIQUE;

-- Thêm ràng buộc check và đặt tên ràng buộc 
ALTER TABLE `customer`
ADD CONSTRAINT CHK_Name CHECK (length(name) > 5);

-- Thêm ràng buộc defaut sau khi tạo bảng
ALTER TABLE `customer`
ALTER  `name` SET DEFAULT 'Codegym';


ALTER TABLE `c0123_customer`.`customer` 
CHANGE COLUMN `type` `type` INT NOT NULL ;

ALTER TABLE `c0123_customer`.`customer` 
ADD CONSTRAINT `fk_type_customer_type`
  FOREIGN KEY (`type`)
  REFERENCES `c0123_customer`.`customer_type` (`id`);


