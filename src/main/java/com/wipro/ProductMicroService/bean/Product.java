package com.wipro.ProductMicroService.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
	
	@Id
    @Column(name = "ID")
    private Integer id;
    private String productName;
    private String productCode;
    @Column(name = "PRODUCT_DESC")
    private String productDesc;
    private Date productAddedOn;

}
