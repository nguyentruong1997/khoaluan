package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.UserDao;
import com.migi.model.CustomerDTO;
import com.migi.model.ProductDTO;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;


@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Override
	public UserDTO getUserInfo(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getUserInfo");
		UserDTO obj = new  UserDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("select * from  user a,  role b where a.userID =? " + 
				" AND a.roleid = b.id AND b.code = ? AND a.status = ? ");
	 
		PreparedStatement pt=null;
		try {
			//conn.setAutoCommit(false); 
			pt = conn.prepareStatement(sql.toString());  
			pt.setInt(1, user.getUserID());
			pt.setString(2, "user");
			pt.setInt(3, 1);
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					obj.setUserID(rs.getInt("userID"));
					obj.setUserName(rs.getString("username")); 
					obj.setFullName(rs.getString("fullname")); 
					obj.setEmail(rs.getString("email"));
					obj.setAddress(rs.getString("diachi"));
					obj.setPhone(rs.getString("sodienthoai"));
					obj.setDateBirth(rs.getDate("ngaysinh"));
				}
			}
			//conn.commit();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getUserInfo");
		return obj;
	}

	@Override
	public String createNewCustomer(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao createNewCustomer ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "";
		if(-1==user.getUserID())
			sql = "INSERT INTO `customer`(hoten,diachi,sodienthoai,email,stk)  "
				+ "VALUES (?,?,?,?)";
		else {
			sql = "INSERT INTO `customer`(hoten,diachi,sodienthoai,email,stk,userID)  "
					+ "VALUES (?,?,?,?,?)";
		}
		PreparedStatement pt = null; 
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, user.getFullName());
			pt.setString(2, user.getAddress());
			pt.setString(3, user.getPhone());
			pt.setString(4, user.getEmail());  
			pt.setString(5, user.getStk());
			if(-1 != user.getUserID()) {
				pt.setInt(6, user.getUserID());  
			}
		 
			pt.executeUpdate();
			conn.commit();
			rs = "success"; // get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(rs);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao createNewCustomer");
		return rs;
	}

	@Override
	public boolean checkCustomerExist(String email) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkCustomerExist");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "SELECT * FROM  customer a where a.email=? ";
		PreparedStatement pt = null;
		try {
			//conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, email);
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				check = true;
			}
			//conn.commit();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(check);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkCustomerExist");
		return check;
	}

	@Override
	public UserDTO getCustomerByEmail(String email) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getCustomerByEmail");
		UserDTO obj = new  UserDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("select * from customer a where a.email = ? ");
	 
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			pt.setString(1, email);
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					obj.setCustomerId(rs.getInt("makhachhang")); 
					obj.setUserID(rs.getInt("userID"));
				}
			}
			//conn.commit();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getCustomerByEmail");
		return obj;
	}

}
