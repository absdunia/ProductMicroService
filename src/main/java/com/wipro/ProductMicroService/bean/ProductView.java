package com.wipro.ProductMicroService.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductView {
	    private Integer id;
	    private String productName;
	    private String productCode;
	    private String productDesc;
	    private Date productAddedOn;
		private float price;
		private int promotion1;
		private int promotion2;
		private int promotion3;
		private Timestamp startDate;
		private Timestamp endDate;
		private int quantity;
		private String supplierDetails;
		
}