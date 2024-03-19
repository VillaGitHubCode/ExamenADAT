package com.sergio.apiproducto.service;

import com.sergio.apiproducto.model.Producto;
import com.sergio.apiproducto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Producto saveOrUpdate (Producto producto) {
        return productRepository.save(producto);
    }

    public void deleteProducto ( Long id ){
        productRepository.deleteById(id);
    }

    public List<Producto> getProductos(){
        return productRepository.findAll();
    }

    public Optional<Producto> getProducto (Long id){
        return productRepository.findById(id);
    }

    public List<Producto> getProductoByName (String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Producto> getProductosByStock (int stock){
        return productRepository.findByStock(stock);
    }






}
