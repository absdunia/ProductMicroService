/**
 * 
 */
package com.wipro.ProductMicroService.bean;

import org.springframework.stereotype.Component;

@Component
public class Price {

	
	private int productID;

	private float price;
	public Price()
	{
		
	}
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
@Override
public String toString()
{
	return ""+price;
}
}
