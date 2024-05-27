package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Customer;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderStatus;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductEcommServiceImpl implements ProductEcommerceService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserEcommServiceImpl ecommServiceImpl;

	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		List<Product> list = productRepository.findAll();

		return list;
	}

	@Override
	public Product getProductById(Long productId) {
		Product productFound = productRepository.findById(productId).orElseThrow(
				() -> new ProductNotFoundException("Product Not found with given ID :: " + productId, "E404"));

		return productFound;
	}

	@Override
	public Product createNewproduct(Product product) {
		product.setId(new Date().getTime());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		Product save = productRepository.save(product);
		return save;
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		Product updatedproduct = null;
		Product productFound = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not found with given ID :: " + id, "E404"));
		if (productFound != null) {
			try {
				productFound.setImageUrl(product.getImageUrl());
				productFound.setManufacturer(product.getManufacturer());
				productFound.setInventory(product.getInventory());
				productFound.setPrice(product.getPrice());
				productFound.setRating(product.getRating());
				updatedproduct = productRepository.save(productFound);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return updatedproduct;
	}

	@Override
	public String deleteProduct(Long productId) {
		Product productFound = productRepository.findById(productId).orElseThrow(
				() -> new ProductNotFoundException("Product Not found with given ID :: " + productId, "E404"));
		if (productFound != null) {
			productRepository.deleteById(productId);
		}
		return "Product Deleted";
	}

	@Override
	public Order orderProduct(Long productId, Long userId) {
		Customer userById = ecommServiceImpl.commonFindUserById(userId);
		Order order = new Order();
		order.setId(new Date().getTime());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setProduct_id(productId);
		if (userById != null) {
			order.setUserId(userId);
		} else {
			throw new ProductNotFoundException("User not found With the given Id :: " + userId, "E404");
		}
		order.setStatus(OrderStatus.PENDING);
		Product productFound = productRepository.findById(productId).orElseThrow(
				() -> new ProductNotFoundException("Product Not found with given ID :: " + productId, "E404"));
		order.setTotalAmount(productFound.getPrice());

		orderRepository.save(order);
		return order;
	}

}
