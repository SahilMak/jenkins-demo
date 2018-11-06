package com.revature.dao;

import java.util.List;

import com.revature.db.ProductDB;
import com.revature.models.Product;

public class ProductDAOImpl implements ProductDAO {

	private ProductDB productDB = ProductDB.productDB;
	
	@Override
	public List<Product> getAllProducts() {
		return productDB.getAllProducts();
	}

	@Override
	public Product getProductByKey(int productKey) {
		return productDB.getProductByKey(productKey);
	}

	@Override
	public Product addProduct(Product newProduct) {
		return productDB.addProduct(newProduct);
	}

	@Override
	public Product updateProduct(Product updatedProduct) {
		return productDB.updateProduct(updatedProduct);
	}

	@Override
	public boolean deleteProduct(int productKey) {
		return productDB.deleteProduct(productKey);
	}

}
