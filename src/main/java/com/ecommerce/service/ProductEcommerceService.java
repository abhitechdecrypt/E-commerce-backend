package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;

public interface ProductEcommerceService {
	
	List<Product> getProductList();
	
	Product getProductById(Long productId);
	
	Product createNewproduct(Product product);
	
	Product updateProduct(Product product, Long id);
	
	String deleteProduct(Long productId);
	
	Order orderProduct(Long productId, Long userId);
	
	Product findProductByCategory(String category);

}
