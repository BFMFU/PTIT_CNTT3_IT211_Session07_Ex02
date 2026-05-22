package app.product.ex02.controller;

import app.product.ex02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@PostMapping("/add")
	public ResponseEntity<String> addProduct() {
		productService.addProduct();
		return ResponseEntity.ok("Product added successfully");
	}
}
