package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.SupplierDao;
import com.migi.dao.UserDao;
import com.migi.model.ClassifyDTO;
import com.migi.model.CustomerDTO;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.SupplierDTO;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;


@Repository
@Transactional
public class SupplierDaoImpl implements SupplierDao{
	 
	
	@Override
	public List<SupplierDTO> getListSupplier(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListSupplier");
		List<SupplierDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM  supplier a where 1=1 ");
		if(null != obj.getTenNhaSanXuat() ) {
			sql.append(" AND a.tennhasanxuat LIKE ? ");
		}
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			if(null != obj.getTenNhaSanXuat() ) {
				pt.setString(1, "%"+obj.getTenNhaSanXuat().trim()+"%" );
			}
			
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					SupplierDTO supplierDto = new SupplierDTO();
					supplierDto.setMaNhaSanXuat(rs.getInt("manhasanxuat"));
					supplierDto.setTenNhaSanXuat(rs.getString("tennhasanxuat"));
					supplierDto.setDiaChi(rs.getString("diachi"));
					supplierDto.setSoDienThoai(rs.getString("sodienthoai")); 
					supplierDto.setEmail(rs.getString("email"));
					list.add(supplierDto);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListSupplier");
		return list;
	}
	@Override
	public boolean checkSupplierExists(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkSupplierProductExists");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from supplier a where a.manhasanxuat=? ";
		
		PreparedStatement pt = null;
		try {
			pt = conn.prepareStatement(sql);
			pt.setInt(1, obj.getMaNhaSanXuat());
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				System.out.println("check checkSupplierExists = true");
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
//		check = true;
		System.out.println(check);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkSupplierProductExists");
		return check;

	}
	@Override
	public boolean checkProductSupplierExisted(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkProductSupplierExisted");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from supplier a , product b  where a.manhasanxuat = b.manhasanxuat and a.manhasanxuat = ? ";
		PreparedStatement pt = null;
		try {
			pt = conn.prepareStatement(sql);
			pt.setInt(1, obj.getMaNhaSanXuat());
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				check = true;
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
		System.out.println(check);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkProductSupplierExisted");
		return check;
	}
	@Override
	public String addSupplier(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addSupplier ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "INSERT INTO `supplier`(tennhasanxuat,diachi,sodienthoai,email)  VALUES (?,?,?,?)";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, obj.getTenNhaSanXuat());
			pt.setString(2, obj.getDiaChi());
			pt.setString(3, obj.getSoDienThoai());
			pt.setString(4, obj.getEmail());
			pt.executeUpdate();
			conn.commit();
			rs = "success"; // get Code Bill
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(rs);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addSupplier");
		return rs;
	}
	@Override
	public String removeSupplier(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao removeSupplier ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "DELETE FROM `supplier` a where a.manhasanxuat = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, obj.getMaNhaSanXuat());
			pt.executeUpdate();
			conn.commit();
			rs = "success"; // get  success
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao removeSupplier");
		return rs;
	}

	@Override
	public String updateSupplier(SupplierDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updateSupplier ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "UPDATE `supplier` a SET a.tennhasanxuat = ? , a.diachi = ? ,"
				+ " a.sodienthoai=? , a.email=? WHERE a.manhasanxuat = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, obj.getTenNhaSanXuat());
			pt.setString(2, obj.getDiaChi());
			pt.setString(3, obj.getSoDienThoai());
			pt.setString(4, obj.getEmail());
			pt.setInt(5, obj.getMaNhaSanXuat());
			pt.executeUpdate();
			conn.commit();
			rs = "success"; //  
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updateSupplier");
		return rs;
	}

}
