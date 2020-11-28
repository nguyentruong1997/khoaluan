package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminImportInvoiceDao;
import com.migi.dao.AdminInvoiceUserDao;
import com.migi.dao.AdminProductDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.EmployeeDao;
import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;
 
@Repository
@Transactional
public class AdminInvoiceUserDaoImpl implements AdminInvoiceUserDao{

	@Override
	public String addImportInvoiceProduct() {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addImportInvoiceProduct ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		Date date = new Date();
		System.out.println(date.toString());
		String sql = "INSERT INTO `enter_coupon`(maphieunhap,ngaynhap)  VALUES (?,?)";
	//  DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//		LocalDateTime localDateTime = LocalDateTime.now();
//		Date createDate = Date.from(localDateTime.toInstant(ZoneOffset.UTC));
//		System.out.println(createDate);
	    StringBuilder maHDNNhap = new StringBuilder("HDN_"+date.toString());
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, maHDNNhap.toString());  
			pt.setDate ( 2, new java.sql.Date(  date.getTime()))  ; 
			System.out.println(date.getTime());
			pt.executeUpdate();
			conn.commit();
			rs = maHDNNhap.toString(); //get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			 try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addImportInvoiceProduct");
		return rs;
	}

	@Override
	public String addImportInvoiceDetails(ProductDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addImportInvoiceDetails ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		Date date = new Date();
		System.out.println(date.toString());
		String sql = "INSERT INTO `entry_form_details`(maphieunhap,masanpham,soluong,gianhap)  VALUES (?,?,?,?)";
		int sumNumber = 0;
		for(ClassifyDTO item : emp.getClassifiedList()) {
			sumNumber += item.getNumber();
		}
		System.out.println("sum : "+sumNumber);
		 
		
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, emp.getImportBillCode());  
			pt.setString(2, emp.getProductCode());  
			pt.setInt(3, sumNumber);  
			pt.setFloat(4, emp.getPriceFirst());  
			pt.executeUpdate();
			conn.commit();
			rs = "success"; //get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			 try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addImportInvoiceDetails");
		return rs;
	}
	@Override
	public String updatePriceImportInvoice(EnterCouponDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updatePriceImportInvoice ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		Date date = new Date();
		System.out.println(date.toString());
		String sql = "UPDATE `enter_coupon` SET tongThanhToan =? WHERE maphieunhap= ?";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setFloat(1, emp.getPriceSum());  
			pt.setString(2, emp.getImportBillCode());  
			pt.executeUpdate();
			conn.commit();
			rs = "success"; //get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			 try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updatePriceImportInvoice");
		return rs;
	}

	@Override
	public String UpdateNumberProduct(InvoicesDTO obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeStatusInvoice(InvoicesDTO obj,int statusInvoice) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updatePriceImportInvoice ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		Date date = new Date();
		System.out.println(date.toString());
		String sql = "UPDATE `invoices` SET statusInvoice =? WHERE mahoadon =?";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, statusInvoice);  
			pt.setString(2, obj.getMaHoaDon());  
			pt.executeUpdate();
			conn.commit();
			rs = "success"; //get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			 try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updatePriceImportInvoice");
		return rs;
	}

	@Override
	public List<InvoicesDTO> getListOrderAdminFilter(InvoicesDTO item) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListOrderWaitingOrFinish:"+item);
		List<InvoicesDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  a.*,c.mahoadon,c.tongHoaDon,c.ngaylap,c.statusInvoice  FROM customer a " + 
				" join invoices c on a.makhachhang = c.makhachhang  where c.status= ? ");
		if(item.getStatusInvoice()!=-1) {
			sql.append(" and c.statusInvoice=?");
		}
		if(item.getTuNgay()!=null) {
			sql.append(" and c.ngaylap >= ?");
		}
		if(item.getDenNgay()!=null) {
			sql.append(" and c.ngaylap <= ?");
		}
		sql.append(" order by c.mahoadon DESC ");
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			pt.setInt(1, 1); //set status
			int i=2;
			if(item.getStatusInvoice()!=-1) {
				pt.setInt(i++, item.getStatusInvoice());
			}
			if(item.getTuNgay()!=null) {
				pt.setDate(i++, new java.sql.Date ( item.getTuNgay().getTime()));
			}
			if(item.getDenNgay()!=null) {
				pt.setDate(i++, new java.sql.Date ( item.getDenNgay().getTime()));
			}
			  
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					InvoicesDTO invoicesDTO = new InvoicesDTO();
//					ClassifyDTO classify = new ClassifyDTO();
//					classify.setColor(rs.getString("color"));
//					classify.setSize(rs.getString("size"));
//					classify.setId(rs.getInt("maphanloaisp"));
					invoicesDTO.setMaHoaDon(rs.getString("mahoadon"));
//					invoicesDTO.setClassify(classify);
//					invoicesDTO.setImage(rs.getString("image"));
//					invoicesDTO.setProductName(rs.getString("tensanpham"));
//					invoicesDTO.setMaSanPham(rs.getString("masanpham"));
//					invoicesDTO.setGiaban(rs.getFloat("giahientai"));
//					invoicesDTO.setDiachi(rs.getString("diachi"));
//					invoicesDTO.setHoten(rs.getString("hoten"));
//					invoicesDTO.setEmail(rs.getString("email"));
					invoicesDTO.setSumPrice(rs.getFloat("tongHoaDon"));
//					invoicesDTO.setSoLuong(rs.getInt("soluongmua"));
					invoicesDTO.setNgayLap(rs.getDate("ngaylap"));
					invoicesDTO.setStatusInvoice(rs.getInt("statusInvoice"));
					list.add(invoicesDTO);
				}
			}
			pt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListOrderWaitingOrFinish");
		return list;
	}

	@Override
	public List<InvoicesDTO> getDetailOrderById(InvoicesDTO item) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getDetailOrderById:"+item);
		List<InvoicesDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  a.*,c.mahoadon,c.tongHoaDon,c.ngaylap,c.statusInvoice,"
				+ " e.*,d.soluong as soluongmua ,f.color,f.size,f.maphanloaisp  FROM customer a " + 
				" join invoices c on a.makhachhang = c.makhachhang join invoice_details d " + 
				" on c.mahoadon = d.mahoadon join product e on e.masanpham = d.masanpham " + 
				" join classify_product f on  d.maphanloai = f.maphanloaisp where e.masanpham=f.masanpham and c.status= ? ");
		sql.append(" AND c.mahoadon = ? ");
		sql.append(" order by c.mahoadon DESC ");
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			pt.setInt(1, 1); //set status
			pt.setString(2, item.getMaHoaDon()); //set status
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					InvoicesDTO invoicesDTO = new InvoicesDTO();
					ClassifyDTO classify = new ClassifyDTO();
					classify.setColor(rs.getString("color"));
					classify.setSize(rs.getString("size"));
					classify.setId(rs.getInt("maphanloaisp"));
					invoicesDTO.setMaHoaDon(rs.getString("mahoadon"));
					invoicesDTO.setClassify(classify);
					invoicesDTO.setImage(rs.getString("image"));
					invoicesDTO.setProductName(rs.getString("tensanpham"));
					invoicesDTO.setMaSanPham(rs.getString("masanpham"));
					invoicesDTO.setGiaban(rs.getFloat("giahientai"));
					invoicesDTO.setDiachi(rs.getString("diachi"));
					invoicesDTO.setHoten(rs.getString("hoten"));
					invoicesDTO.setEmail(rs.getString("email"));
					invoicesDTO.setSumPrice(rs.getFloat("tongHoaDon"));
					invoicesDTO.setSoLuong(rs.getInt("soluongmua"));
					invoicesDTO.setNgayLap(rs.getDate("ngaylap"));
					invoicesDTO.setStatusInvoice(rs.getInt("statusInvoice"));
					list.add(invoicesDTO);
				}
			}
			pt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getDetailOrderById");
		return list;
	}
 
 
 

	 
	 
}
