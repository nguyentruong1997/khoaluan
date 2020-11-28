package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminTypeDao;
import com.migi.model.ProductTypeDTO;
import com.migi.utils.DBconnection;

@Repository
@Transactional
public class AdminTypeDaoImpl implements AdminTypeDao {

	@Override
	public boolean checkProductTypeExists(ProductTypeDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkProductTypeExists");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from product_type a where a.maloaisp=? ";
		PreparedStatement pt = null;
		try {
			//conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, emp.getTypeId());
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				System.out.println("check checkProductTypeExists = true");
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkProductTypeExists");
		return check;

	}

	@Override
	public String addProductType(ProductTypeDTO productTypeDTO) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addProductType ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "INSERT INTO `product_type`(tenloaisp,parentId)  VALUES (?,?)";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, productTypeDTO.getName());
			if(productTypeDTO.getParentId() != -1)
				pt.setInt(2, productTypeDTO.getParentId());
			else 
				pt.setInt(2 , 0);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addProductType");
		return rs;
	}

	@Override
	public List<ProductTypeDTO> getListProductType() {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListProductType");
		List<ProductTypeDTO> list = new ArrayList<>();
		Connection conn = DBconnection.getConnection();
		String sql = "select * from product_type";
		PreparedStatement pt = null;
		try {
			// conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					ProductTypeDTO type = new ProductTypeDTO();
					type.setName(rs.getString("tenloaisp"));
					type.setTypeId(rs.getInt("maloaisp"));
					type.setParentId(rs.getInt("parentId"));
					list.add(type);
				}
			}
			// conn.commit();
			pt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListProductType");
		return list;
	}

	@Override
	public List<ProductTypeDTO> getListProductTypeBySearch(ProductTypeDTO productTypeDTO) {
				System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListProductTypeBySearch");
				List<ProductTypeDTO> list = new ArrayList<>();
				Connection conn = DBconnection.getConnection();
				StringBuilder sql = new StringBuilder("select a.*, b.tenloaisp as parentName from product_type a  left join product_type b on a.parentID = b.maloaisp where 1=1 ");
				if(null != productTypeDTO.getNameSearch()) {
					sql.append(" AND a.tenloaisp like ? ");
				}
					
//				else if(productTypeDTO.getCodeSearch()!=0) {
//					sql = new StringBuilder("WITH RECURSIVE getListType AS " + 
//							" ( " + 
//							" SELECT a.maloaisp , a.tenloaisp ,a.parentID  FROM product_type a " + 
//							" WHERE objects.parentID = 0  " + 
//							" UNION ALL  " + 
//							" SELECT b.maloaisp , b.tenloaisp ,b.parentID  " + 
//							" FROM product_type AS b INNER JOIN getListType AS recur ON b.parentID = recur.maloaisp " + 
//							" ) " + 
//							" select  c.maloaisp , c.tenloaisp ,c.parentID  from product_type c where 1=1 " + 
//							" and c.maloaisp in ( SELECT getListType.maloaisp AS maloaisp FROM getListType )"+
//							" and c. "
//							);
//					
//					sql.append(" AND a.tenloaisp like '%?%' ");
//				}
				PreparedStatement pt=null;
				try {
					//conn.setAutoCommit(false); 
					pt = conn.prepareStatement(sql.toString());  
					if(null != productTypeDTO.getNameSearch()) {
						pt.setString(1, "%"+productTypeDTO.getNameSearch().trim()+"%");
					}
					
					ResultSet rs = pt.executeQuery();
					if(rs.isBeforeFirst()) {
						while(rs.next()) {
							ProductTypeDTO type = new ProductTypeDTO();
							type.setName(rs.getString("tenloaisp"));
							type.setTypeId(rs.getInt("maloaisp")); 
							type.setParentId(rs.getInt("parentId")); 
							type.setParentName(rs.getString("parentName"));
							list.add(type);
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
				System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListProductTypeBySearch");
				return list;
	}

	@Override
	public String removeProductType(ProductTypeDTO productTypeDTO) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao removeProductType ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "DELETE FROM `product_type` a where maloaisp = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, productTypeDTO.getTypeId());
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao removeProductType");
		return rs;
	}

	@Override
	public String updateProductType(ProductTypeDTO productTypeDTO) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updateProductType ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "UPDATE `product_type` a SET a.tenloaisp = ? , a.parentId = ? WHERE a.maloaisp = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, productTypeDTO.getName());
			pt.setInt(2, productTypeDTO.getParentId());
			pt.setInt(3, productTypeDTO.getTypeId());
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updateProductType");
		return rs;
	}

}
