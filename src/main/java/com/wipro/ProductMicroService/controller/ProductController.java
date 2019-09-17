package com.wipro.ProductMicroService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ProductMicroService.Dao.ProductDao;
import com.wipro.ProductMicroService.bean.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
    private ProductDao productDao;

    @GetMapping(value = "/all")
    public List<Product> getAll() {
        return productDao.findAll();
    }

   /* @PostMapping(value = "/load")
    public List<Product> persist(@RequestBody final Product product) {
    	productDao.save(product);
        return productDao.findAll();
    }*/
    
	@GetMapping("/welcome")
	public String welcome() {
		//System.out.println("******************************************");
		return "hello microservice!!!!";
	}
	@GetMapping("/")
	public String getWelcomePage() {
		return "Welcome to Default page/response";
	}

}
