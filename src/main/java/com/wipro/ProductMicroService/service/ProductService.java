package com.wipro.ProductMicroService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.ProductMicroService.bean.Product;


@Service
public interface ProductService {
	
	public List<Product> getAllProductDetails();
	public Product getProductById(int productID);
	public String deleteProductByID(String productID);
	public Product updateInsertProductDetails(Product product);
	public Product saveNewProduct(Product product);

}
