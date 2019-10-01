/**
 * 
 */
package com.wipro.ProductMicroService.bean;

import org.springframework.stereotype.Component;

/**
 * @author Team 6
 *
 */
@Component
public class Inventory {
	
	
	private int productID;
	private int quantity;
	private String supplierDetails;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(String supplierDetails) {
		this.supplierDetails = supplierDetails;
	}
	
	@Override
	public String toString()
	{
		return "Inventory fields : 1.Product ID :"+productID+" 2.Quantity : "+quantity+"  3.Supplier Details : "+supplierDetails;
	}
}
