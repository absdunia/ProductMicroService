package com.wipro.ProductMicroService.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.wipro.ProductMicroService.Dao.ProductDao;
import com.wipro.ProductMicroService.bean.Product;

@RestController
@RequestMapping("/eCommerce/product")
public class ProductController {
	
    @Autowired
    private ProductDao productDao;
    
    @Value("${server.port}")
	String serverPort;
    
    @Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
    
    @RequestMapping("/message")
	public String serverSays() {
		List<String> messages =  Arrays.asList("Abhishek","Aditi","Govind","Sajith","Sibi");
		Random random =  new Random();
		int randomNum = random.nextInt(messages.size());
		return messages.get(randomNum) + " - @ "+ serverPort;
	}

    @GetMapping(value = "/getAllProducts")
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
		return "hello microservice!!!!" + " - @ "+ serverPort;
	}
	@GetMapping("/")
	public String getWelcomePage() {
		return "Welcome to Default page/response" + " - @ "+ serverPort;
	}
	

}
