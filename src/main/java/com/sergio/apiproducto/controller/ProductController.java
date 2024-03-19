package com.sergio.apiproducto.controller;

import com.sergio.apiproducto.model.Producto;
import com.sergio.apiproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/productos")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        Producto productNew = productService.saveOrUpdate(producto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productNew);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        List<Producto> productos = productService.getProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Producto>> searchProductName(@RequestParam String name){
        List<Producto> producto = productService.getProductoByName(name);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/searchByStockEmpty")
    public ResponseEntity<List<Producto>> searchProductStock(){
        List<Producto> productos = productService.getProductosByStock(0);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> updateProducto (@PathVariable Long idProducto, @RequestBody Producto producto) {

        Optional<Producto> productoOld = productService.getProducto(idProducto);

        if (productoOld.isPresent()){
            producto.setId(idProducto);
            productService.saveOrUpdate(producto);
            return ResponseEntity.ok(producto);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Optional<Producto>> deleteProducto (@PathVariable Long idProducto){

        Optional<Producto> producto = productService.getProducto(idProducto);

        if (producto.isPresent()){
            productService.deleteProducto(idProducto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
