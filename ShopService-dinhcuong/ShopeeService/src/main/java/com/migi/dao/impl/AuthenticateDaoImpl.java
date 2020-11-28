package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

import com.migi.dao.AuthenticateDao;
import com.migi.dao.EmployeeDao;
import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;
 
 

@Repository
@Transactional
public class AuthenticateDaoImpl implements AuthenticateDao{
	 
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public UserDTO authenticate(UserDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao");
		Connection conn = DBconnection.getConnection();
		String sql = "select * from  user a,  role b where a.username=? "
				+ " AND a.password=? "
				+ " AND a.roleid = b.id ";
		PreparedStatement pt = null;
		UserDTO userDTO = new UserDTO();
		try {
			//conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, emp.getUserName());
			pt.setString(2, emp.getPassWord());
			ResultSet rs = pt.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password  = rs.getString("password");
				if(username!=null && password!=null) {
					userDTO.setUserName(username);
					userDTO.setPassWord(password);
					if(username.equals(emp.getUserName())   &&  password.equals(emp.getPassWord())  ) {
						int userId  = rs.getInt("userId");  
						String authenCode = rs.getString("code");
						userDTO.setUserID(userId);
						userDTO.setRoleCode(authenCode);
						userDTO.setFullName(rs.getString("fullname"));
					}
				}
			}
			userDTO.setError("Tài khoản hoặc mật khẩu sai");
			//conn.commit();
			pt.close();
			rs.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
//			 try {
//				//conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao");
		System.out.println(userDTO);
		return userDTO;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkRole(UserDTO user,String role) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkRole");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from  user a,  role b where a.userID=? "
				+ " AND a.roleid = b.id AND b.code = ?";
		PreparedStatement pt = null;
		try {
			//conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, user.getUserID());
			pt.setString(2, role);
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				System.out.println("---Di vao isBefore first");
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkRole");
		return check;
	}

	@Override
	public boolean checkDuplicateUser(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkDuplicateUser");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from  user a  where a.username=? "
				+ " OR a.email = ? ";
		PreparedStatement pt = null;
		try {
			//conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, user.getUserName());
			pt.setString(2, user.getEmail());
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				System.out.println("---Di vao isBefore first checkDuplicateUser");
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkDuplicateUser");
		return check;
	}

	@Override
	public String register(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao registerDAO ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "INSERT INTO `user`(username,password,fullname,status,roleid,diachi,ngaysinh,email,sodienthoai)  "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pt = null;System.out.println(user.getDateBirth());
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String formattedDate = formatter.format(user.getDateBirth());
//		Date date = new Date(formattedDate);
//		System.out.println(date);
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, user.getUserName());
			pt.setString(2, user.getPassWord());
			pt.setString(3, user.getFullName());
			pt.setInt(4, 1); // user.getStatus()
			pt.setInt(5, 2);   // roleId = 2 auto
			pt.setString(6, user.getAddress());
			pt.setDate(7, new java.sql.Date(user.getDateBirth().getTime()));
			pt.setString(8, user.getEmail());
			pt.setString(9, user.getPhone());
		 
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao registerDAO");
		return rs;
	}

	@Override
	public UserDTO getUserById(int userId) {
		 
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao");
		Connection conn = DBconnection.getConnection();
		String sql = "select * from  user a,  role b where a.userID=? "
				+ " AND a.roleid = b.id AND b.code = ? ";
		PreparedStatement pt = null;
		UserDTO userDTO = new UserDTO();
		try {
			pt = conn.prepareStatement(sql);
			pt.setInt(1, userId);
			pt.setString(2, "user");
			ResultSet rs = pt.executeQuery();
			while(rs.next()) {
				userDTO.setEmail(rs.getString("email"));
				userDTO.setPhone(rs.getString("sodienthoai"));
				userDTO.setUserID(rs.getInt("userID"));
				userDTO.setFullName(rs.getString("fullname"));
				userDTO.setAddress(rs.getString("diachi"));
			}
			//conn.commit();
			pt.close();
			rs.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
//			 try {
//				//conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao");
		System.out.println(userDTO);
		return userDTO;
		// TODO Auto-generated method stub
	}
	 
	
	 
	 
	
}
