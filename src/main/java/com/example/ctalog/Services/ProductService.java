package com.example.ctalog.Services;


import com.example.ctalog.Domain.Product;

import com.example.ctalog.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {




    @Autowired
    ProductRepository productRepository;




    public Map<String,Object> addProduct(Product product)
    {


        Product exits=productRepository.findByName(product.getName());

        Map<String,Object> result=new HashMap<>();
        Optional<Product> optional =Optional.ofNullable( exits);
        if (!optional.isPresent())
        {

            Product save=new Product();
            save.setCode(product.getCode());
            save.setName(product.getName());
            save.setDescription(product.getDescription());
            save.setPrice(product.getPrice());
            productRepository.save(save);
            result.put("product",save);
            result.put("isSuccess",true);
            return result;

        }else
            return result;
    }




    public List<Product> products() throws RuntimeException
    {
        List<Product> productlist=productRepository.findAll();
        if (productlist!=null)
        {

            return productlist;
        }
        else
            return (List<Product>) new RuntimeException(" No Product is Present");
    }

}
