package com.example.ctalog.Controller;

import com.example.ctalog.Domain.Product;


import com.example.ctalog.Repositories.ProductRepository;
import com.example.ctalog.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {


    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/api/product/add_product", method = RequestMethod.POST)
    public ResponseEntity<Object> addproduct(@RequestBody Product product) {


        Map<String, Object> result = productService.addProduct(product);
        if (result.get("isSuccess").equals(true)) {
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } else
            return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);


    }


    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public ResponseEntity<Object> product(HttpServletResponse response, HttpServletRequest servletRequest) {

        List<Product> result = productService.products();
        if (result != null) {
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        }else
            return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);

    }


    @RequestMapping(value = "/api/product/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> product(@PathVariable String name) {


        Product product = productRepository.findByName(name);
        if (product != null) {

            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(product, HttpStatus.BAD_REQUEST);


    }

    @GetMapping("/greeting")
    public String Greeting() {

        return "Hello from " + this.discoveryClient.getInstances("catalog-service");
    }


}
