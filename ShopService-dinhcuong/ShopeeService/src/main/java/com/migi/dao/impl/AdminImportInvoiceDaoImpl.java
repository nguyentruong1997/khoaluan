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
import com.migi.dao.AdminProductDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.EmployeeDao;
import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;
 
@Repository
@Transactional
public class AdminImportInvoiceDaoImpl implements AdminImportInvoiceDao{

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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updatePriceImportInvoice");
		return rs;
	}
 
 

	 
	 
}
