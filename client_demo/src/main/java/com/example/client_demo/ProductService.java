package com.example.client_demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService{

    public List getProducts(){
        return Arrays.asList("Mazda","Toyota","Audi");
    }
}