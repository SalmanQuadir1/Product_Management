package models;

import java.util.List;

public class ProductDto {
	   private String productName;
       private String description;
       private String status;
       private List<ProductVariantDto> variants;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ProductVariantDto> getVariants() {
		return variants;
	}
	public void setVariants(List<ProductVariantDto> variants) {
		this.variants = variants;
	}
       
       
       
       
	
       

}
