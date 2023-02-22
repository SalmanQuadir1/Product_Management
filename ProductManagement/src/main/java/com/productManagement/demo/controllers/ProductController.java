package com.productManagement.demo.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductVariant;
import com.productManagement.demo.repository.ProductVariantRepository;
import com.productManagement.demo.service.ImageService;
import com.productManagement.demo.service.ProductService;

import utils.CommonMethods;
import utils.Constants;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService prodService;

    //private Object productImage;
    @Autowired
    private ImageService imageService;


    @Autowired
    private ProductVariantRepository pvr;


    @PostMapping(value = "/updateProduct", consumes = {"multipart/form-data",
            MediaType.MULTIPART_FORM_DATA_VALUE}) /*
     * headers = "content-type=multipart/*", consumes = {
     * MediaType.APPLICATION_JSON_VALUE,
     * MediaType.MULTIPART_FORM_DATA_VALUE, "application/*" })
     */
    public ResponseEntity<?> updateProduct(HttpServletRequest request, HttpServletResponse response,
                                           @RequestPart Product product, @RequestPart(value = "files", required = false) MultipartFile[] files) {
        System.err.println(product.getProduct() + "bbbbbbbbbbbbbb");
        for (MultipartFile multipartFile : files) {
            System.err.println("hehehehheh" + multipartFile.getContentType());
            System.err.println("nameeee" + multipartFile.getOriginalFilename());
        }

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
            System.err.println(product.getDescription() + "aaaaaaaaaaaaaaaaaaaaa");
            for (MultipartFile file : files) {

                Images img = new Images();
                img.setName(file.getOriginalFilename());
                System.out.println(file.getOriginalFilename() + "jjjjjjjjjjjjjjjj");
                img.setType(file.getContentType());
                try {
                    img.setImage(file.getBytes());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                img.setProduct(newProduct);

                /*
                 * try { byte[] bytes = file.getBytes(); Path path = Paths.get(Constants.PATH +
                 * file.getOriginalFilename()); Files.write(path, bytes); } catch (IOException
                 * e) { e.printStackTrace(); }
                 */

                imageService.save(img);
            }

            List<ProductVariant> variants = product.getVariants();
            if (variants != null) {
                List<ProductVariant> productVariants = variants.stream().map(variant -> {
                    ProductVariant productVariant = new ProductVariant();
                    productVariant.setSize(variant.getSize());
                    productVariant.setQuantity(variant.getQuantity());
                    productVariant.setWeight(variant.getWeight());
                    productVariant.setProduct(newProduct);
                    return productVariant;
                }).collect(Collectors.toList());
                pvr.saveAll(productVariants);
            }

            return ResponseEntity.status(HttpStatus.OK).body(newProduct);

        } else {
            return ResponseEntity.badRequest().body("Product Details Are missing");
        }
    }

    @GetMapping("/getImage/{imageName:.+}")
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

//	@RequestMapping(value = "/getimage/{imageName:.+}", method = RequestMethod.GET)
//   // @ApiOperation(value = "Find Image ")
//   // @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
//    public static void getImage(@PathVariable("imageName") String imageName, Images model, HttpServletRequest req,
//        List<HttpServletResponse> rep) throws IOException, WriterException {
//
//      try {
//        InputStream is = new FileInputStream(Constants.PATH + imageName);
//
//        byte[] bytes = IOUtils.toByteArray(is);
//        for (HttpServletResponse ree : rep) {
//          ree.setContentType(CommonMethods.getContentType(imageName));
//          OutputStream os = ree.getOutputStream();
//          os.write(bytes);
//          os.close();
//          is.close();
//        }
//        //rep.setContentType(CommonsMethod.getContentType(imageName));
//        
//        
//        
//      } catch (Exception e) {
//      }
//
//    }

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
     * @GetMapping("/{id}/sizes") public ResponseEntity<Map<String, Integer>>
     * getSizesAndQuantitiesByProductId(@PathVariable Long id) { List<Object[]>
     * sizesAndQuantities = pvr.findSizesAndQuantitiesByProductId(id);
     *
     * Map<String, Integer> sizeQuantityMap = sizesAndQuantities.stream()
     * .collect(Collectors.toMap(sizeAndQuantity -> (String) sizeAndQuantity[0],
     * sizeAndQuantity -> ((Number) sizeAndQuantity[1]).intValue()));
     *
     * Map<String, Integer> filteredSizeQuantityMap = new HashMap<>();
     * filteredSizeQuantityMap.put("S", sizeQuantityMap.getOrDefault("S", 0));
     * filteredSizeQuantityMap.put("XXL", sizeQuantityMap.getOrDefault("XXL", 0));
     *
     * return ResponseEntity.ok(filteredSizeQuantityMap); }
     */


}
