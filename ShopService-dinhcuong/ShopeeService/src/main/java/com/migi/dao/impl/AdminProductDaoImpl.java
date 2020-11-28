package com.migi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.migi.dao.AdminProductDao;
import com.migi.model.ClassifyDTO;
import com.migi.model.ProductDTO;
import com.migi.utils.DBconnection;

@Repository
@Transactional
public class AdminProductDaoImpl implements AdminProductDao {

	@Override
	public String addProductInfo(ProductDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addProductInfo ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "INSERT INTO `product`(masanpham,tensanpham,giahientai,maloaisp,image,manhasanxuat,soluongton)  VALUES (?,?,?,?,?,?,?)";

		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, emp.getProductCode());
			pt.setString(2, emp.getProductName());
			pt.setFloat(3, emp.getPrice());
			pt.setInt(4, emp.getTypeId());
			pt.setString(5, emp.getImage() != null ? emp.getImage() : "default.png");
			pt.setInt(6, emp.getBrandCode());
			pt.setInt(7, emp.getSoluongton());
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addProductInfo");
		return rs;

	}

	public static String getQuery(long iteration_count, String query, int count) {

		StringBuilder builder = new StringBuilder(query);

		int COLUMN_COUNT = count;
		String placeholders = getInsertPlaceholders(COLUMN_COUNT);
		for (int i = 0; i < iteration_count; i++) {
			if (i != 0) {
				builder.append(",");
			}
			builder.append(placeholders);
		}
		return builder.toString();
	}

	public static String getInsertPlaceholders(int placeholderCount) {
		final StringBuilder builder = new StringBuilder("(");
		for (int i = 0; i < placeholderCount; i++) {
			if (i != 0) {
				builder.append(",");
			}
			builder.append("?");
		}
		return builder.append(")").toString();
	}

//
//	public static void main(String[] args) {
//		String query = "REPLACE INTO bitrix_history (HISTORY_ID,TASK_ID,CREATED_DATE,FIELD,VALUE_FROM,VALUE_TO,USER_ID,USER_NAME,USERT_LAST_NAME,USER_SECOND_NAME,USER_LOGIN) VALUES ";
//
//		String sql = Utils.getQuery(50, query, 11);
//		System.out.println(sql);
//	}
	@Override
	public String addClassifiedProduct(ProductDTO enterCoupon) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao addClassifiedProduct ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		Date date = new Date();
		System.out.println(date.toString());
		// String sql = "INSERT INTO
		// `entry_form_details`(maphieunhap,masanpham,soluong,gianhap) VALUES
		// (?,?,?,?)";
		String sqlOriginal = "INSERT INTO `classify_product`(masanpham,color,size,soluong)  VALUES ";
		String sql = getQuery(enterCoupon.getClassifiedList().size(), sqlOriginal, 4);
		System.out.println(sql);
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			List<ClassifyDTO> listClassify = enterCoupon.getClassifiedList();
			for (int i = 1, j = 0; j < listClassify.size(); i += 4, j++) {
				ClassifyDTO obj = listClassify.get(j);
				System.out.println(obj);
				pt.setString(i, enterCoupon.getProductCode());
				pt.setString(i + 1, obj.getColor());
				pt.setString(i + 2, obj.getSize());
				pt.setInt(i + 3, obj.getNumber());
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao addClassifiedProduct");
		return rs;
	}

	@Override
	public boolean checkProductExisted(ProductDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkProductExisted");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from product a where a.masanpham=? ";
		PreparedStatement pt = null;
		try {
			// conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, emp.getProductCode());
			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				check = true;
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
		System.out.println(check);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkProductExisted");
		return check;
	}

	@Override
	public boolean checkProductTypeItemExisted(ProductDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao checkProductTypeItemExisted");
		boolean check = false;
		Connection conn = DBconnection.getConnection();
		String sql = "select * from product a , product_type b where a.maloaisp = b.maloaisp and b.maloaisp = ? ";
		PreparedStatement pt = null;
		try {
			// conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, emp.getTypeId());
			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				check = true;
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
		System.out.println(check);
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao checkProductTypeItemExisted");
		return check;
	}

	@Override
	public List<ProductDTO> getProductList(ProductDTO emp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListProductTypeBySearch");
		List<ProductDTO> list = new ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder(
				"select a.*, b.tennhasanxuat,dt.gianhap,SUM(dt.soluong) as soluongnhap from product a join product_type c on a.maloaisp = c.maloaisp "
						+ " left join supplier b on a.manhasanxuat = b.manhasanxuat "
						+ " join entry_form_details dt on a.masanpham =dt.masanpham "
						+ " join enter_coupon e on e.maphieunhap = dt.maphieunhap  "
						+ " where 1=1 ");
		if (null != emp.getProductName()) {
			sql.append(" AND a.tensanpham like ? ");
		}
		if (-1 != emp.getTypeId()) {
			sql.append(" AND a.maloaisp = ? ");
		}
		sql.append(" group by a.masanpham  ");
		PreparedStatement pt = null;
		try {
			// conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql.toString());
			int i = 1;
			if (null != emp.getProductName()) {
				pt.setString(i, "%" + emp.getProductName().trim() + "%");
				i++;
			}
			if (-1 != emp.getTypeId()) {
				pt.setInt(i, emp.getTypeId());
			}

			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					// System.out.println("Image:"+rs.getString("image"));
					ProductDTO p = new ProductDTO();
					p.setBrandName(rs.getString("tennhasanxuat") == null ? "No Brand" : rs.getString("tennhasanxuat"));
					p.setImage(rs.getString("image"));
					p.setPrice(rs.getFloat("giahientai"));
					p.setProductName(rs.getString("tensanpham"));
					p.setProductCode(rs.getString("masanpham"));
					p.setPriceFirst(rs.getFloat("gianhap"));
					p.setDescription(rs.getString("mota"));
					p.setTypeId(rs.getInt("maloaisp"));
					p.setSoluongton(rs.getInt("soluongton"));
					list.add(p);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListProductTypeBySearch");
		return list;
	}
//	public List<ProductDTO> getProductDetail(ProductDTO emp) {
//		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getAProductDetailAdmin");
//		List<ProductDTO> list = new ArrayList<>();
//		Connection conn = DBconnection.getConnection();
//		StringBuilder sql = new StringBuilder(
//				"select a.*, b.tennhasanxuat,dt.gianhap from product a join product_type c on a.maloaisp = c.maloaisp "
//						+ " left join supplier b on a.manhasanxuat = b.manhasanxuat "
//						+ " join entry_form_details dt on a.masanpham =dt.masanpham "
//						+ " join enter_coupon e on e.maphieunhap = dt.maphieunhap  where 1=1 ");
//		 
//		if (-1 != emp.getTypeId()) {
//			sql.append(" AND a.masanpham = ? ");
//		}
//		PreparedStatement pt = null;
//		try {
//			pt = conn.prepareStatement(sql.toString());
//			if (-1 != emp.getTypeId()) {
//				pt.setString(1, emp.getProductCode()));
//			}
//			ResultSet rs = pt.executeQuery();
//			if (rs.isBeforeFirst()) {
//				while (rs.next()) {
//					ProductDTO p = new ProductDTO();
//					p.setBrandName(rs.getString("tennhasanxuat") == null ? "No Brand" : rs.getString("tennhasanxuat"));
//					p.setImage(rs.getString("image"));
//					p.setPrice(rs.getFloat("giahientai"));
//					p.setProductName(rs.getString("tensanpham"));
//					p.setProductCode(rs.getString("masanpham"));
//					p.setPriceFirst(rs.getFloat("gianhap"));
//					p.setDescription(rs.getString("mota"));
//					p.setTypeId(rs.getInt("maloaisp"));
//					list.add(p);
//				}
//			}
//			// conn.commit();
//			pt.close();
//			rs.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getAProductDetailAdmin");
//		return list;
//	}
	@Override
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getAdminProductClassify");
		List<ClassifyDTO> list = new ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder(
				"SELECT  d.* FROM  product a join product_type b on a.maloaisp = b.maloaisp "
						+ " left join supplier c on a.manhasanxuat = c.manhasanxuat "
						+ " join classify_product d on a.masanpham = d.masanpham " + " where 1=1  ");
		if (null != obj.getProductCode()) {
			sql.append(" AND a.masanpham = ? ");
		}
		PreparedStatement pt = null;
		try {
			pt = conn.prepareStatement(sql.toString());
			int i = 1;
			if (null != obj.getProductCode()) {
				pt.setString(i, obj.getProductCode());
			}

			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					ClassifyDTO classDto = new ClassifyDTO();
					classDto.setId(rs.getInt("maphanloaisp"));
					classDto.setProductCode(rs.getString("masanpham"));
					classDto.setColor(rs.getString("color"));
					classDto.setSize(rs.getString("size"));
					classDto.setNumber(rs.getInt("soluong"));
					;
					classDto.setPlus(0);
					list.add(classDto);
				}
			}
			pt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getAdminProductClassify");
		return list;
	}

	@Override
	public String updateProductInfo(ProductDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updateProductInfo ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "UPDATE `product` a SET a.tensanpham = ? , a.giahientai = ? ,"
				+ " a.image=? , a.mota=? WHERE a.masanpham = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, obj.getProductName());
			pt.setFloat(2, obj.getPrice());
			pt.setString(3, obj.getImage());
			pt.setString(4, obj.getDescription());
			pt.setString(5, obj.getProductCode());
			;
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updateProductInfo");
		return rs;
	}

	@Override
	public String updateClassifiedProduct(ClassifyDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updateClassifiedProduct ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		String sql = "UPDATE `classify_product` a SET a.soluong =? where a.maphanloaisp =? AND a.masanpham =?";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			//System.out.println("ham dao: "+obj.getNumber() + obj.getPlus());
			pt.setInt(1, obj.getNumber() );
			pt.setInt(2, obj.getId() );
			pt.setString(3, obj.getProductCode());
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updateClassifiedProduct");
		return rs;
	}

	@Override
	public ClassifyDTO getProductClassifyItem(int maphanloai, String masp) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getProductClassifyItem");
		ClassifyDTO classDto = new ClassifyDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM  classify_product a, product b where a.masanpham = b.masanpham "
				+ "and a.maphanloaisp =? and b.masanpham =? ");
		PreparedStatement pt = null;
		try {
			pt = conn.prepareStatement(sql.toString());
			pt.setInt(1, maphanloai); pt.setString(2, masp);
			ResultSet rs = pt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					classDto.setId(rs.getInt("maphanloaisp"));
					classDto.setProductCode(rs.getString("masanpham"));
					classDto.setColor(rs.getString("color"));
					classDto.setSize(rs.getString("size"));
					classDto.setNumber(rs.getInt("soluong"));
				}
			}
			pt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getProductClassifyItem");
		return classDto;
	}

	@Override
	public String updateProductNumber(String masp, int soluongton) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao updateProductNumber ");
		Connection conn = DBconnection.getConnection();
		String rs = null;
		String sql = "UPDATE `product` a SET a.soluongton =? where a.masanpham =? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, soluongton);
			pt.setString(2, masp);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao updateProductNumber");
		return rs;
	}

}
