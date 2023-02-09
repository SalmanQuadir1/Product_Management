package com.productManagement.demo.controllers;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.repository.ImagesRepository;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ImageService;
import com.productManagement.demo.service.ProductService;

import utils.Constants;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService prodService;
	@Autowired
	private ProductRepository productRepository;
	private Object productImage;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ProductRepository prodRepository;
	@Autowired
	private ImagesRepository imageRepsitory;

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

	@PostMapping(value = "/addupdateProduct", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestPart Product product, @RequestPart(value = "files", required = false) MultipartFile[] files) {
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

			// Check if there are any files (images) to update

			if (files != null && files.length > 0) {
				for (MultipartFile file : files) {
					Images img = new Images();
					img.setName(file.getOriginalFilename());
					img.setType(file.getContentType());
					img.setProduct(result);
					try {
						byte[] bytes = file.getBytes();
						Path path = Paths.get(Constants.PATH + file.getOriginalFilename());
						Files.write(path, bytes);
					} catch (IOException e) {
						e.printStackTrace();
					}
					imageService.save(img);
				}

			}
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

			for (MultipartFile file : files) {
				Images img = new Images();
				img.setName(file.getOriginalFilename());
				img.setType(file.getContentType());
				img.setProduct(newProduct);

				try {
					byte[] bytes = file.getBytes();
					Path path = Paths.get(Constants.PATH + file.getOriginalFilename());
					Files.write(path, bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageService.save(img);
			}

			return ResponseEntity.status(HttpStatus.OK).body(newProduct);

		} else {
			return ResponseEntity.badRequest().body("Product Details Are missing");
		}
	}

	@GetMapping("/getimage/{imageName:.+}")
	public void getImage(@PathVariable("imageName") String name, Images images, HttpServletRequest req,
			HttpServletResponse rep) throws IOException {

		try {
			InputStream is = new FileInputStream(Constants.PATH + name);
			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(name));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}

	}

	public static String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "imsage/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}

	@GetMapping("/allProducts")
	public ResponseEntity<?> getAll() {
		List<Product> product = prodService.findAll();
		return ResponseEntity.ok(product);
	}

	@GetMapping("/findProduct/{id}")
	public ResponseEntity<?> findProduct(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		Product prod = prodService.findByIdWithImages(id);
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<List<Product>> deleteProductItem(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		prodService.deleteProductById(id);
		return ResponseEntity.ok().build();

	}

	/*--------------------------------------------------------------------------*/


	/*
	 * @PutMapping("/images/{id}") public ResponseEntity<Images>
	 * updateProductImage(@PathVariable Long id,
	 * 
	 * @RequestParam("files") MultipartFile files) { Images productImage =
	 * imagesRepository.findById(id).get();
	 * 
	 * try { productImage.setData(files.getBytes());
	 * productImage.setFileName(files.getOriginalFilename());
	 * imagesRepository.save(productImage);
	 * 
	 * return new ResponseEntity<>(productImage, HttpStatus.OK); } catch
	 * (IOException e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
