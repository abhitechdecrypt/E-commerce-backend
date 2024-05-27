package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponseDTO;
import com.ecommerce.dto.ErrorResponseDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductEcommServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/ecommerce/product")
public class ProductEcommController {

	@Autowired
	ProductEcommServiceImpl productEcommerceService;

	
	@Operation(summary = "Fetch Product Data API", 
			description = "REST API to fetch data inside E-commerce website ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", 
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("fetchProduct")
	public ResponseEntity<?> getProductList() {
		List<Product> productList = productEcommerceService.getProductList();
		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}

	
	@Operation(summary = "Create Product Data API", 
			description = "REST API to create data inside E-commerce website ")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "HTTP Status CREATED", 
					content = @Content(schema = @Schema(implementation = ApiResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", 
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping("createProduct")
	public ResponseEntity<ApiResponseDTO<Product>> getNewProduct(@RequestBody Product product) {
		Product newProduct = productEcommerceService.createNewproduct(product);

		ApiResponseDTO<Product> response = new ApiResponseDTO<>();
		response.setCode("201");
		response.setMessage("Product Created Successfully");
		response.setData(newProduct);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	@Operation(summary = "Find product By ID API", 
			description = "REST API to Find product By ID data inside E-commerce website ")
	@ApiResponses({
			@ApiResponse(responseCode = "302", description = "HTTP Status CREATED", 
					content = @Content(schema = @Schema(implementation = ApiResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", 
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("findProduct/{productId}")
	public ResponseEntity<ApiResponseDTO<Product>> getProductById(@PathVariable(name = "productId") Long productId) {
		Product newProduct = productEcommerceService.getProductById(productId);

		ApiResponseDTO<Product> response = new ApiResponseDTO<>();
		response.setCode("302");
		response.setMessage("Product Found");
		response.setData(newProduct);

		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	
	@Operation(summary = "Delete product By ID API", 
			description = "REST API to Delete product By ID data inside E-commerce website ")
	@ApiResponses({
			@ApiResponse(responseCode = "202", description = "HTTP Status CREATED", 
					content = @Content(schema = @Schema(implementation = ApiResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", 
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("deleteProduct/{productId}")
	public ResponseEntity<ApiResponseDTO<Product>> deleteProduct(@PathVariable(name = "productId") Long productId) {
		productEcommerceService.deleteProduct(productId);

		ApiResponseDTO<Product> response = new ApiResponseDTO<>();
		response.setCode("302");
		response.setMessage("Product Deleted Successfully");

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

	
	
	
	@Operation(summary = "Update Product Data API", 
			description = "REST API to update data inside E-commerce website ")
	@ApiResponses({
			@ApiResponse(responseCode = "202", description = "HTTP Status CREATED",
					content = @Content(schema = @Schema(implementation = ApiResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PatchMapping("updateProduct/{productId}")
	public ResponseEntity<ApiResponseDTO<Product>> updateProduct(@RequestBody Product product,
			@PathVariable(name = "productId") Long productId) {
		Product updateProduct = productEcommerceService.updateProduct(product, productId);

		ApiResponseDTO<Product> response = new ApiResponseDTO<>();
		if (updateProduct == null) {
			response.setCode("500");
			response.setMessage("Failed to update the product");
			response.setData(updateProduct);
		} else {
			response.setCode("202");
			response.setMessage("Product updated Successfully");
			response.setData(updateProduct);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	@Operation(summary = "Order Product Data API", 
			description = "REST API to Order Product inside E-commerce website ")
	@ApiResponses({
		@ApiResponse(responseCode = "202", description = "HTTP Status CREATED",
				content = @Content(schema = @Schema(implementation = ApiResponse.class))),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
		content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PatchMapping("orderProduct/{productId}/{userId}")
	public ResponseEntity<ApiResponseDTO<Order>> OrderProduct(@PathVariable(name = "userId") String userId,
			@PathVariable(name = "productId") Long productId) {
		Order orderedproduct = productEcommerceService.orderProduct(productId, Long.parseLong(userId));
		
		ApiResponseDTO<Order> response = new ApiResponseDTO<>();
		if (orderedproduct == null) {
			response.setCode("500");
			response.setMessage("Failed to update the product");
			response.setData(orderedproduct);
		} else {
			response.setCode("202");
			response.setMessage("Product updated Successfully");
			response.setData(orderedproduct);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

}
