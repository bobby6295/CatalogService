package com.example.ctalog.Repositories;

import com.example.ctalog.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Serializable> {

Product findByName(String  name);



}
