package com.productManagement.demo.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductImages;
import com.productManagement.demo.entity.ResponseMessage;
import com.productManagement.demo.repository.ImagesRepository;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService prodService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ImagesRepository imagesRepository;

	/*
	 * @PostMapping("/saveProduct") public ResponseEntity<?>
	 * saveProduct(@RequestBody Product product) {
	 * System.out.println("Product Saved !!!"); try { prodService.save(product);
	 * return ResponseEntity.status(HttpStatus.CREATED).body(product);
	 * 
	 * } catch (Exception e) {
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	 * 
	 * }
	 * 
	 * }
	 */

	@PostMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Product product) {
		if (product != null && product.getId() != null) {
			Product productEntity = prodService.getProductById(product.getId());
			product.setId(productEntity.getId());
			productEntity.setDescription(product.getDescription());
			productEntity.setComments(product.getComments());
			productEntity.setDetails(product.getDetails());
			productEntity.setPrice(product.getPrice());
			productEntity.setRating(product.getRating());
			productEntity.setReviews(product.getReviews());
			productEntity.setStatus(product.getStatus());
			productEntity.setProductName(product.getProductName());
			Product result = prodService.saveProduct(productEntity);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else if (product != null && product.getId() == null) {
			Product productEntity = new Product();
			productEntity.setDescription(product.getDescription());
			productEntity.setComments(product.getComments());
			productEntity.setDetails(product.getDetails());
			productEntity.setPrice(product.getPrice());
			productEntity.setRating(product.getRating());
			productEntity.setReviews(product.getReviews());
			productEntity.setStatus(product.getStatus());
			productEntity.setProductName(product.getProductName());

			Product newProduct = prodService.saveProduct(productEntity);

			return ResponseEntity.status(HttpStatus.OK).body(newProduct);

		} else {
			return ResponseEntity.badRequest().body("Product Details Are missing");
		}
	}



	@GetMapping("/allProducts")
	public ResponseEntity<?> getAll() {
		List<Product> product = prodService.findAll();
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<List<Product>> deleteProductItem(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		prodService.deleteProductById(id);
		return ResponseEntity.ok().build();
	}
	
	
	 
		/*--------------------------------------------------------------------------*/
	  
	  
	  @PostMapping("/{id}/images")
	  public ResponseEntity<Product> uploadProductImages(@PathVariable Long id, @RequestParam("files") MultipartFile[] files) throws IOException {
	    Product product = prodService.findById(id);
	    
	    for (MultipartFile file : files) {
	      Images productImage = new Images();
	      productImage.setFileName(file.getOriginalFilename());
	      productImage.setData(file.getBytes());
	      productImage.setProduct(product);
	      
	      product.getImages().add(productImage);
	      imagesRepository.save(productImage);
	    }
	    
	    prodService.save(product);
	    
	    return ResponseEntity.ok(product);
	  }
	  
	  
	  
	  @GetMapping("/images/{id}")
	  public ResponseEntity<byte[]> downloadProductImage(@PathVariable Long id) {
	    Images productImage = imagesRepository.findById(id).get();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(productImage.getData().length);
	    headers.setContentDispositionFormData("attachment", productImage.getFileName());
	    
	    return new ResponseEntity<>(productImage.getData(), headers, HttpStatus.OK);
	  }
	}





