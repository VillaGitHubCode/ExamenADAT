package com.sergio.apiproducto.repository;

import com.sergio.apiproducto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNameContainingIgnoreCase(String name);

    List<Producto> findByStock(int stock);




}
