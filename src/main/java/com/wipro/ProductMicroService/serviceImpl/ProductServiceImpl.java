package com.wipro.ProductMicroService.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wipro.ProductMicroService.Dao.ProductDao;
import com.wipro.ProductMicroService.bean.Product;
import com.wipro.ProductMicroService.service.ProductService;

@Service
@Qualifier("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Override
	public List<Product> getAllProductDetails() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public Product getProductById(int productID) {
		// TODO Auto-generated method stub
		return productDao.findById(productID).get();
	}

	@Override
	public String deleteProductByID(String productID) {
		// TODO Auto-generated method stub
		
		Optional<Product> product = productDao.findById(Integer.parseInt(productID));
		productDao.deleteById(product.get().getId());
		return "Object has been removed from the DB";
	}

	@Override
	public Product updateInsertProductDetails(Product product) {
		// TODO Auto-generated method stub
		
		return productDao.save(product);
	}

	@Override
	public Product saveNewProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}
	
	

}
