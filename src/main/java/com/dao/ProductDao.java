package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.ProductBean;

@Repository
public class ProductDao {
	@Autowired
	JdbcTemplate stmt;

	public void addProduct(ProductBean productBean) {
		stmt.update("insert into products (name , price , category , qty) values (?,?,?,?)", productBean.getName(),
				productBean.getPrice(), productBean.getCategory(), productBean.getQty());
	}
	public ProductBean getProductById(Integer productId) {
		try {
			String Query1 = "select * from products where productId = ?";
			ProductBean productById = stmt.queryForObject(Query1, new BeanPropertyRowMapper<ProductBean>(ProductBean.class),new Object[] {productId});
			if (productById != null) {
				return productById; 
			}
		} catch (Exception e) {              
			// TODO: handle exception
		}
		return null;
	}
	
	public List<ProductBean> getAllProductList() {
		try {
			String Query1 = "select * from products";
			List<ProductBean> listOfProduct = stmt.query(Query1, new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
			if (listOfProduct != null) {
				return listOfProduct; 
			}
		} catch (Exception e) {              
			// TODO: handle exception
		}
		return null;
	}
	public void deleteProductById(Integer productId) {
		try {
			String Query1 = "delete from products where productId = ?";
			stmt.update(Query1, productId);
		} catch (Exception e) {              
			// TODO: handle exception
		}
	}
	
	public void updateProductById(Integer productId , ProductBean productBean) {
		try {
			String Query1 = "update products set name = ? , price = ? , category = ? , qty = ?  where productId = ?";
			stmt.update(Query1, productBean.getName() , productBean.getPrice() , productBean.getCategory() , productBean.getQty() , productId);
		} catch (Exception e) {              
			// TODO: handle exception
		}	
	}
}