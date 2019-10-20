package com.wipro.ProductMicroService.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.wipro.ProductMicroService.bean.Inventory;
import com.wipro.ProductMicroService.bean.Price;
import com.wipro.ProductMicroService.bean.Product;
import com.wipro.ProductMicroService.bean.ProductView;
import com.wipro.ProductMicroService.bean.Promotion;
import com.wipro.ProductMicroService.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/eCommerce/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
    
    @Value("${server.port}")
	String serverPort;
    
    @Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
    
	@Autowired
	private Price price;
	
	@Autowired
	private Product product;
	
	@Autowired
	private Inventory inventory;
	
	@Autowired
	private Promotion promotion;

  /*  @GetMapping(value = "/getAllProducts")
    public List<Product> getAll() {
        return productDao.findAll();
    }*/
    
	@GetMapping(value = "/getAllProducts", produces = "application/json")
	public List<Product> getAllInventoryDetails() {
		return productServiceImpl.getAllProductDetails();
	}

	@GetMapping(value = "/getProductById")
	public Product getProductById(@RequestParam String productID) {
		return productServiceImpl.getProductById(Integer.parseInt(productID));
	}

	@DeleteMapping(value = "/deleteByProductID",produces = "application/json")
	public String removeProduct(@RequestParam String productID) {
		return productServiceImpl.deleteProductByID(productID);
	}

	@PutMapping(value = "/updateProductByID", consumes = "application/json", produces = "application/json")
	public Product updateProduct(@RequestBody Product product) {
		return productServiceImpl.updateInsertProductDetails(product);
	}

	@PostMapping(value = "/createProduct", consumes = "application/json", produces = "application/json")
	public Product createProduct(@RequestBody Product product) {
		return productServiceImpl.saveNewProduct(product);
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "hello microservice!!!!" + " - @ "+ serverPort;
	}
	@GetMapping("/")
	public String getWelcomePage() {
		return "Welcome to Default page/response" + " - @ "+ serverPort;
	}
	
	@PostMapping(value = "/createAllProduct", consumes = "application/json", produces = "application/json")
	public ProductView createAllProduct(@RequestBody ProductView productView) {
		RestTemplate restTemplate =  restTemplateBuilder.build();
		
		product.setId(productView.getId());
		product.setProductName(productView.getProductName());
		product.setProductCode(productView.getProductCode());
		product.setProductDesc(productView.getProductDesc());
		product.setProductAddedOn(productView.getProductAddedOn());
		productServiceImpl.saveNewProduct(product);
		
		price.setProductID(productView.getId());
		price.setPrice(productView.getPrice());
		
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PRICE-MS", false);
		String baseUrl = instanceInfo.getHomePageUrl();
		baseUrl = baseUrl+ "/eCommerce/price/createPrice";
		Price priceResult = restTemplate.postForObject(baseUrl, price, Price.class);
		
		inventory.setProductID(productView.getId());
		inventory.setQuantity(productView.getQuantity());
		inventory.setSupplierDetails(productView.getSupplierDetails());
		
		InstanceInfo instanceInfo1 = eurekaClient.getNextServerFromEureka("INVENTORY-MS", false);
		String baseUrl1 = instanceInfo1.getHomePageUrl();
		baseUrl1 = baseUrl1+ "/eCommerce/inventory/createInventory";
		Inventory inventoryResult = restTemplate.postForObject(baseUrl1, inventory, Inventory.class);
		
		promotion.setProductID(productView.getId());
		promotion.setPromotion1(productView.getPromotion1());
		promotion.setPromotion2(productView.getPromotion2());
		promotion.setPromotion3(productView.getPromotion3());
		promotion.setStartDate(productView.getStartDate());
		promotion.setEndDate(productView.getEndDate());
		
		InstanceInfo instanceInfo2 = eurekaClient.getNextServerFromEureka("PROMOTION-MS", false);
		String baseUrl2 = instanceInfo2.getHomePageUrl();
		baseUrl2 = baseUrl2+ "/eCommerce/promotion/createPromotionForProduct";
		Promotion promotionResult = restTemplate.postForObject(baseUrl2, promotion, Promotion.class);
		
		return productView;
	}

	@DeleteMapping(value = "/deleteAllByProductID",produces = "application/json")
	public String removeAll(@RequestParam String productID) {
		RestTemplate restTemplate =  restTemplateBuilder.build();
		try {
			productServiceImpl.deleteProductByID(productID);
			
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PRICE-MS", false);
			String baseUrl = instanceInfo.getHomePageUrl();
			baseUrl = baseUrl+ "/eCommerce/price/deleteByProductID?productID="+productID;
			restTemplate.delete(baseUrl);
			
			InstanceInfo instanceInfo1 = eurekaClient.getNextServerFromEureka("INVENTORY-MS", false);
			String baseUrl1 = instanceInfo1.getHomePageUrl();
			baseUrl1 = baseUrl1+ "/eCommerce/inventory/deleteByProductID?productID="+productID;
			restTemplate.delete(baseUrl1);
			
			InstanceInfo instanceInfo2 = eurekaClient.getNextServerFromEureka("PROMOTION-MS", false);
			String baseUrl2 = instanceInfo2.getHomePageUrl();
			baseUrl2 = baseUrl2+ "/eCommerce/promotion/deleteByProductID?productID="+productID;
			restTemplate.delete(baseUrl2);
			
			return "Product detials deleted successfully from all tables";
		}
		catch(Exception e){
			return "ProductID not found";
		}
	}
		@PutMapping(value = "/updateAll", consumes = "application/json", produces = "application/json")
		public ProductView updateAllProduct(@RequestBody ProductView productView) {
			RestTemplate restTemplate =  restTemplateBuilder.build();
			
			
			product.setId(productView.getId());
			product.setProductName(productView.getProductName());
			product.setProductCode(productView.getProductCode());
			product.setProductDesc(productView.getProductDesc());
			product.setProductAddedOn(productView.getProductAddedOn());
			
			productServiceImpl.updateInsertProductDetails(product);
			
			price.setProductID(productView.getId());
			price.setPrice(productView.getPrice());
			
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PRICE-MS", false);
			String baseUrl = instanceInfo.getHomePageUrl();
			baseUrl = baseUrl+ "/eCommerce/price/updatePriceByID";
			restTemplate.put(baseUrl, price);
			
			inventory.setProductID(productView.getId());
			inventory.setQuantity(productView.getQuantity());
			inventory.setSupplierDetails(productView.getSupplierDetails());
			
			InstanceInfo instanceInfo1 = eurekaClient.getNextServerFromEureka("INVENTORY-MS", false);
			String baseUrl1 = instanceInfo1.getHomePageUrl();
			baseUrl1 = baseUrl1+ "/eCommerce/inventory/updateInventoryByID";
			restTemplate.put(baseUrl1, inventory);
			
			promotion.setProductID(productView.getId());
			promotion.setPromotion1(productView.getPromotion1());
			promotion.setPromotion2(productView.getPromotion2());
			promotion.setPromotion3(productView.getPromotion3());
			promotion.setStartDate(productView.getStartDate());
			promotion.setEndDate(productView.getEndDate());
			
			InstanceInfo instanceInfo2 = eurekaClient.getNextServerFromEureka("PROMOTION-MS", false);
			String baseUrl2 = instanceInfo2.getHomePageUrl();
			baseUrl2 = baseUrl2+ "/eCommerce/promotion/updatePromotionsByID";
			restTemplate.put(baseUrl2, promotion);
			
			
			return productView;
			
		}
	
}

