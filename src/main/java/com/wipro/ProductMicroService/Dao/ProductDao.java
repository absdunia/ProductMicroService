package com.wipro.ProductMicroService.Dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.ProductMicroService.bean.Product;

@Repository
@Transactional
public interface ProductDao extends JpaRepository<Product, Integer>{

}