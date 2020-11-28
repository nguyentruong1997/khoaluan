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

import com.migi.dao.AdminProductDao;
import com.migi.dao.AuthenticateDao;
import com.migi.dao.EmployeeDao;
import com.migi.dao.ProductDao;
import com.migi.entity.Employee;
import com.migi.entity.User;
import com.migi.model.CartItemDTO;
import com.migi.model.ClassifyDTO;
import com.migi.model.EnterCouponDTO;
import com.migi.model.InvoicesDTO;
import com.migi.model.ProductDTO;
import com.migi.model.ProductTypeDTO;
import com.migi.model.UserDTO;
import com.migi.utils.DBconnection;
 
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{

	@Override
	public ProductDTO getProductDetail(ProductDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getProductDetail");
		ProductDTO product = new  ProductDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT a.*,c.tennhasanxuat FROM  product a join product_type b on a.maloaisp = b.maloaisp left join supplier c on a.manhasanxuat = c.manhasanxuat where 1=1 ");
		if(null != obj.getProductCode() ) {
			sql.append(" AND a.masanpham = ? ");
		}
		PreparedStatement pt=null;
		try {
			//conn.setAutoCommit(false); 
			pt = conn.prepareStatement(sql.toString());  
			int i = 1;
			if(null != obj.getProductCode()) {
				pt.setString(i,obj.getProductCode());
			}
			
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					System.out.println("Image:"+rs.getString("image"));
					product.setBrandName(rs.getString("tennhasanxuat")==null?"No Brand":rs.getString("tennhasanxuat"));
					product.setImage(rs.getString("image"));
					product.setPrice(rs.getFloat("giahientai"));
					product.setProductName(rs.getString("tensanpham"));
					product.setProductCode(rs.getString("masanpham"));
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getProductDetail");
		return product;
	}

	@Override
	public List<ClassifyDTO> getProductClassify(ClassifyDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getProductClassify");
		List<ClassifyDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  d.* FROM  product a join product_type b on a.maloaisp = b.maloaisp " + 
				" left join supplier c on a.manhasanxuat = c.manhasanxuat " + 
				" join classify_product d on a.masanpham = d.masanpham " + 
				" where 1=1  ");
		if(null != obj.getProductCode() ) {
			sql.append(" AND a.masanpham = ? ");
		}
		PreparedStatement pt=null;
		try {
			//conn.setAutoCommit(false); 
			pt = conn.prepareStatement(sql.toString());  
			int i = 1;
			if(null != obj.getProductCode()) {
				pt.setString(i,obj.getProductCode());
			}
			
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ClassifyDTO classDto = new ClassifyDTO();
					classDto.setId(rs.getInt("maphanloaisp"));
					classDto.setProductCode(rs.getString("masanpham")); 
					classDto.setColor(rs.getString("color"));
					classDto.setSize(rs.getString("size"));
					classDto.setNumber(rs.getInt("soluong"));;
					list.add(classDto);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getProductClassify");
		return list;
	}

	@Override
	public ClassifyDTO getProductClassifyNumber(ClassifyDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getProductClassifyNumber");
		System.out.println(obj.getId());
		ClassifyDTO  classDto  = new ClassifyDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  d.* FROM  product a join product_type b on a.maloaisp = b.maloaisp " + 
				" left join supplier c on a.manhasanxuat = c.manhasanxuat " + 
				" join classify_product d on a.masanpham = d.masanpham " + 
				" where 1=1  ");
		if(null != obj.getProductCode() ) {
			sql.append(" AND a.masanpham = ? ");
		}
		if(-1 != obj.getId() ) {
			sql.append(" AND d.maphanloaisp = ? ");
		}
		PreparedStatement pt=null;
		try {
			//conn.setAutoCommit(false); 
			pt = conn.prepareStatement(sql.toString());  
			int i = 1;
			if(null != obj.getProductCode()) {
				pt.setString(i++,obj.getProductCode());
			}
			if(-1 != obj.getId()) {
				pt.setInt(i,obj.getId());
			}
			
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
//					classDto.setId(rs.getInt("maphanloaisp"));
//					classDto.setProductCode(rs.getString("masanpham")); 
//					classDto.setColor(rs.getString("color"));
//					classDto.setSize(rs.getString("size"));
					classDto.setNumber(rs.getInt("soluong"));;
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getProductClassifyNumber");
		return classDto;
	}

	@Override
	public String createInvoice(int customerId,Date date,float sum) { 
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao createInvoiceCustomer ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		//Date date = new Date();
		//System.out.println(date.toString());
		StringBuilder invoiceCode = new StringBuilder("HdInvoice_"+date.toString());
		String sql = "INSERT INTO `invoices`(mahoadon,makhachhang,ngaylap,status,statusInvoice,tongHoaDon) "
				+ " VALUES (?,?,?,?,?,?)";
	     
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1,invoiceCode.toString());
			pt.setInt(2, customerId);  
			pt.setDate( 3, new java.sql.Date(date.getTime())); 
			pt.setInt(4,1);
			pt.setInt(5,0);
			pt.setFloat(6, sum);
			pt.executeUpdate();
			conn.commit();
			rs = "success";
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao createInvoiceCustomer");
		return rs;
	}

	@Override
	public InvoicesDTO getInvoices(int customerId,String code) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getInvoices");
		InvoicesDTO invoice = new  InvoicesDTO();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT a.* FROM invoices a join customer b on a.makhachhang = b.makhachhang where 1=1 "
		 +" AND b.makhachhang = ? AND a.mahoadon = ? ");
		  
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			pt.setInt(1, customerId);
			pt.setString(2, code);
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					invoice.setMaKhachHang(rs.getInt("makhachhang"));
					invoice.setMaHoaDon(rs.getString("mahoadon"));
					invoice.setNgayLap(rs.getDate("ngaylap"));
//					invoice.setNgayLap(rs.getDate("ngaylap"));
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getInvoices");
		return invoice;
	}

	@Override
	public String insertInvoiceDetail(InvoicesDTO invoice) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao insertInvoiceDetail ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "INSERT INTO `invoice_details`(masanpham,mahoadon,soluong,maphanloai)  VALUES (?,?,?,?)";
	 
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setString(1, invoice.getMaSanPham());  
			pt.setString(2, invoice.getMaHoaDon() );  
			pt.setInt(3, invoice.getSoLuong());  
			pt.setInt(4, invoice.getClassify().getId());
//			if(emp.getBrandCode()!=-1)
//				pt.setInt(5, emp.getBrandCode());
				
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao insertInvoiceDetail");
		return rs;
	}

	@Override
	public List<InvoicesDTO> getListOrderWaiting(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListOrderWaiting");
		List<InvoicesDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  a.*,c.mahoadon,c.tongHoaDon,e.*,d.soluong as soluongmua ,f.color,f.size,f.maphanloaisp FROM customer a join user b on a.userID = b.userID  " + 
				" join invoices c on a.makhachhang = c.makhachhang join invoice_details d  " + 
				" on c.mahoadon = d.mahoadon join product e on e.masanpham = d.masanpham " + 
				" join classify_product f on  d.maphanloai = f.maphanloaisp where c.status=? and c.statusInvoice=? and a.userID = ? "+
				" order by c.mahoadon "
				);
		
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			int i = 1;
			pt.setInt(i++, 1); pt.setInt(i++, 0);
			pt.setInt(i++, user.getUserID());
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
					list.add(invoicesDTO);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListOrderWaiting");
		return list;
	}

	@Override
	public List<InvoicesDTO> getListOrderWaitingCustomer(UserDTO user) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getListOrderWaiting");
		List<InvoicesDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT  a.*,c.mahoadon,c.tongHoaDon,e.*,d.soluong as soluongmua ,f.color,f.size,f.maphanloaisp FROM customer a " + 
				" join invoices c on a.makhachhang = c.makhachhang join invoice_details d  " + 
				" on c.mahoadon = d.mahoadon join product e on e.masanpham = d.masanpham " + 
				" join classify_product f on  d.maphanloai = f.maphanloaisp where c.status=? and c.statusInvoice=? and a.makhachhang = ? "+
				" order by c.mahoadon "
				);
		
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			int i = 1;
			pt.setInt(i++, 1); pt.setInt(i++, 0);
			pt.setInt(i++, user.getCustomerId());  //set customerId
			 
			
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
					list.add(invoicesDTO);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getListOrderWaiting");
		return list;
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
	@Override
	public String deleteCart(int userID) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao deleteCartUser ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sql = "DELETE FROM `cart` where userID = ? ";
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			pt.setInt(1, userID);
			pt.executeUpdate();
			conn.commit();
			rs = "success"; // get  success
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao deleteCartUser");
		return rs;
	}

	@Override
	public String insertCart(List<CartItemDTO> cartLst) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao insertCartUser ");
		Connection conn = DBconnection.getConnection();
		String rs = "";
		String sqlOriginal = "INSERT INTO `cart`(userID,maphanloaisp,masanpham,soluong)  VALUES ";
		final int numOfParam = 4;
		String sql = getQuery(cartLst.size(), sqlOriginal, numOfParam);
		PreparedStatement pt = null;
		try {
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			for (int i = 1, j = 0; j < cartLst.size(); i += numOfParam, j++) {
				CartItemDTO cart = cartLst.get(j);
				System.out.println(cart);
				pt.setInt(i, cart.getUserID());  
				pt.setInt(i+1, cart.getClassify().getId());  
				pt.setString(i+2, cart.getProductID());  
				pt.setInt(i+3, cart.getSl());
			}
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao insertCartUser");
		return rs;
	}

	@Override
	public List<CartItemDTO> getCartList(CartItemDTO obj) {
		System.out.println(">>>>>>>>>>>>>>>>>Di vao dao getCartList");
		List<CartItemDTO>  list  = new  ArrayList<>();
		Connection conn = DBconnection.getConnection();
		StringBuilder sql = new StringBuilder("SELECT a.userID,a.soluong as soluongmua,d.*,c.tensanpham,c.giahientai,c.image from cart a, user b,product c,classify_product d "
				+ " where  a.userID = b.userID AND b.userID =?  "
				+ " AND a.masanpham = c.masanpham AND a.maphanloaisp = d.maphanloaisp "
			);
		PreparedStatement pt=null;
		try {
			pt = conn.prepareStatement(sql.toString());  
			pt.setInt(1, obj.getUserID());  
			ResultSet rs = pt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					CartItemDTO item = new CartItemDTO();
					ClassifyDTO classify = new ClassifyDTO();
					classify.setColor(rs.getString("color"));
					classify.setSize(rs.getString("size"));
					classify.setId(rs.getInt("maphanloaisp"));
					item.setClassify(classify);
					item.setItemName(rs.getString("tensanpham"));
					item.setProductID(rs.getString("masanpham"));
					item.setSl(rs.getInt("soluongmua"));
					item.setItemPrice(rs.getFloat("giahientai"));
					item.setImage(rs.getString("image"));
					list.add(item);
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
		System.out.println(">>>>>>>>>>>>>>>>>Di ra dao getCartList");
		return list;
	}
	 
	 
}
