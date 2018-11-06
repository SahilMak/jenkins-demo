package com.revature.services;

import java.util.List;

import com.revature.dao.ProductDAO;
import com.revature.dao.ProductDAOImpl;
import com.revature.models.Product;

public class ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();
	
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	public Product getProductByKey(int productKey) {
		return productDAO.getProductByKey(productKey);
	}

	public Product addProduct(Product newProduct) {
		return productDAO.addProduct(newProduct);
	}

	public Product updateProduct(Product updatedProduct) {
		return productDAO.updateProduct(updatedProduct);
	}

	public boolean deleteProduct(int productKey) {
		return productDAO.deleteProduct(productKey);
	}

}
