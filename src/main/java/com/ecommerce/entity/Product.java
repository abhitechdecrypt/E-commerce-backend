package com.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {

	@Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Integer inventory;

	private String category;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	private String manufacturer;

	@Column(nullable = false)
	private Double rating;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
