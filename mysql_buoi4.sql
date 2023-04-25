-- Tính tổng doanh thu năm 2006
select sum(TriGia)
from hoadon
where year(NgayMuaHang) = 2006;

-- Cách 1: dùng tham số out
delimiter //
create procedure spRevenueByYear(
	IN pYear int,
    OUT oRevenue double
)
begin
    set oRevenue = (select sum(TriGia)
				from hoadon
				where year(NgayMuaHang) = pYear);
end //


-- Cách 2: có thể trả ra một câu truy vấn
delimiter //
create procedure spRevenueByYearType2(
	IN pYear int
)
begin
    select sum(TriGia)
	from hoadon
	where year(NgayMuaHang) = pYear;
end //


-- 
delimiter //
create procedure spCreateCustomer(
	INOUT pMANV char(4),
    IN pHOTEN varchar(200),
    IN pSDT varchar(20),
    IN pNGAYVAOLAM datetime
)
begin
	declare maxId int;
    declare strMaxId char(4);
    set strMaxId = "";
    -- @strLastId = NV05
    set @strLastId = (select MANV from nhanvien order by MaNV desc limit 1);
	-- @strNumberLastId = 05
	set @strNumberLastId = SUBSTRING(@strLastId, 3, length( @strLastId) - 2) ;
    
    --  maxId = 5 + 1 => maxId = 6
    set maxId = CAST(@strNumberLastId AS DECIMAL) + 1;
    
    if(maxId<10) then
		set strMaxId = concat('NV0',maxId);
        else
			set strMaxId = concat('NV',maxId);
    end if;
    
	INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `SDT`, `NgayVaoLam`) 
    VALUES (strMaxId, pHOTEN, pSDT, pNGAYVAOLAM);
    
    set pMANV = (select MANV from nhanvien order by MaNV desc limit 1);
end //


-- 
delimiter //
create procedure spCreateCustomerType2(
	INOUT pMANV char(4),
    IN pHOTEN varchar(200),
    IN pSDT varchar(20),
    IN pNGAYVAOLAM datetime
)
begin
	declare maxId int;
    declare strMaxId char(4) default "";
    -- @strLastId = NV05
    set @strLastId = (select MANV from nhanvien order by MaNV desc limit 1);
	-- @strNumberLastId = 05
	set @strNumberLastId = SUBSTRING(@strLastId, 3, length( @strLastId) - 2) ;
    
    --  maxId = 5 + 1 => maxId = 6
    set maxId = CAST(@strNumberLastId AS DECIMAL) + 1;
    
    if(maxId<10) then
		set strMaxId = concat('NV0',maxId);
        else
			set strMaxId = concat('NV',maxId);
    end if;
    
	INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `SDT`, `NgayVaoLam`) 
    VALUES (strMaxId, pHOTEN, pSDT, pNGAYVAOLAM);
    
    set pMANV = (select MANV from nhanvien order by MaNV desc limit 1);
end //


	

