package com.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ProductBean;
import com.dao.ProductDao;

@RestController
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@PostMapping("/product")
	public ProductBean addProduct(@RequestBody ProductBean productBean) {
		System.out.println(productBean.getName());
		productDao.addProduct(productBean);
		return productBean	;
	}
	@GetMapping("/product")
	public List<ProductBean> getAllProductList(){   
		List<ProductBean> listOfProduct =  productDao.getAllProductList();
		return listOfProduct;    
	}
	
	@GetMapping("/product/{productId}")
	public ProductBean getProductById(@PathVariable("productId") Integer productId){
		ProductBean productById =   productDao.getProductById(productId);
		return productById;
	}
	 
	@DeleteMapping("/product/{productId}")
	public void deleteProductById(@PathVariable("productId") Integer productId){
		productDao.deleteProductById(productId);  
	}
	
	@PutMapping("/product/{productId}")
	public void updateProductById(@PathVariable("productId") Integer productId , @RequestBody ProductBean productBean){
		 productDao.updateProductById(productId,productBean);
//		return updateProductById;
	}
}
    