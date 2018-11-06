package com.revature.dao;

import java.util.List;

import com.revature.models.Product;

public interface ProductDAO {

	List<Product> getAllProducts();
	Product getProductByKey(int productKey);
	Product addProduct(Product newProduct);
	Product updateProduct(Product updatedProduct);
	boolean deleteProduct(int productKey);
	
}
