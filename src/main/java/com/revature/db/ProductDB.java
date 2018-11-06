package com.revature.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.models.Product;

public class ProductDB extends HashMap<Integer, Product> {

	private static final long serialVersionUID = 1L;

	public static ProductDB productDB = new ProductDB();
	public static int key = 1;

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		for (Map.Entry<Integer, Product> entry : productDB.entrySet()) {
			products.add(entry.getValue());
		}

		return products;
	}
	
	public Product getProductByKey(int productKey) {

		for (Map.Entry<Integer, Product> entry : productDB.entrySet()) {
			if (entry.getValue().getKey() == productKey) {
				return entry.getValue();
			}
		}

		return null;
	}
	
	public Product addProduct(Product newProduct) {
		return productDB.put(key, newProduct);
	}
	
	public Product updateProduct(Product updatedProduct) {
		return productDB.put(key, updatedProduct);
	}
	
	public boolean deleteProduct(int productKey) {
		Product removedProduct = productDB.remove(productKey);
		return (removedProduct != null);
		
	}

}
