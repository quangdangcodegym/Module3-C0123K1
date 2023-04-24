-- Hiển thị ra doanh thu theo tháng trong năm 2006 lớn hơn 5000000
select month(ngaymuahang) as thang, sum(trigia) as doanhthu from hoadon
where year(ngaymuahang) = 2006
group by month(ngaymuahang)
having doanhthu > 5000000
order by thang;

-- Tìm họ tên khách hàng đã mua hóa đơn có trị giá cao nhất trong năm 2006
-- cách 1:
select kh.HoTen
from khachhang kh
join hoadon hd on kh.makh = hd.MaKH
where hd.trigia >= (select max(TriGia) from hoadon where year(ngaymuahang) = 2006);
-- cách 2
select temp.HoTen
from (
	select kh.HoTen, max(TriGia)
	from khachhang kh
	join hoadon hd on kh.makh = hd.MaKH
	where year(ngaymuahang) = 2006) as temp;
    
--  In ra danh sách các sản phẩm (MASP, TENSP) có giá bán bằng 1 trong 3 mức giá cao nhất.
-- cách 1:
select sp.masp, sp.tensp, sp.gia
from sanpham sp
	join (select distinct gia from sanpham order by gia desc limit 3) as temp
    on sp.gia = temp.gia;
-- cách 2:
select sp.masp, sp.tensp, sp.gia
from sanpham sp
where sp.gia in (
	select gia 
    from (select distinct gia from sanpham order by gia desc limit 3) as temp);    
    
    
-- 35. Tìm khách hàng (MAKH, HOTEN) có số lần mua hàng nhiều nhất.
select hd.MaKH, kh.HoTen, count(hd.SoHoaDon) as sl
from hoadon hd join khachhang kh on kh.MaKH = hd.MaKH
group by hd.MAKH
having sl >= all (
		select count(hd.SoHoaDon) as sl
		from hoadon hd
		group by hd.MAKH
);


select hd.MaKH, kh.HoTen
from hoadon hd join khachhang kh on kh.MaKH = hd.MaKH
group by hd.MAKH
having count(hd.SoHoaDon)= (
	select max(temp.slt)
	from (
		select count(hd.sohoadon) as slt from hoadon hd group by hd.makh) as temp
);


-- 31. Với từng nước sản xuất, tìm giá bán cao nhất, thấp nhất, trung bình của các sản phẩm.
select nuocsx,max(gia), min(gia), avg(gia)
from sanpham
group by nuocsx;

-- 38. Mỗi nước sản xuất, tìm sản phẩm (MASP,TENSP) có giá bán cao nhất.
select sp.nuocsx, sp.tensp
from sanpham sp
join (
	select nuocsx,max(gia) as maxgia
	from sanpham
	group by nuocsx) as temp on sp.nuocsx = temp.nuocsx and sp.gia = temp.maxgia;

select sp.NuocSX, 
	(
		select sp1.TenSP
		from sanpham sp1
		where sp1.NuocSX = sp.NuocSX and sp1.Gia = (select max(Gia) from sanpham where NuocSX = sp.NuocSX)) as tensp
from sanpham sp
group by sp.nuocsx
    
    










    




