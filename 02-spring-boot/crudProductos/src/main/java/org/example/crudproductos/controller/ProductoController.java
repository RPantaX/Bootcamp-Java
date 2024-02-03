package org.example.crudproductos.controller;

import org.aspectj.apache.bcel.generic.RET;
import org.example.crudproductos.entity.Producto;
import org.example.crudproductos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    @GetMapping("/producto/{id}")
    public Optional<Producto> listarProductoById(@PathVariable("id") Long id){
        return productoRepository.findById(id);
    }
    @PostMapping("/producto")
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }
    @PutMapping("/producto/{id}")
    public Producto actualizarProducto(@RequestBody Producto producto, @PathVariable("id") Long id){
        Producto producto1= productoRepository.findById(id).orElseThrow();
        producto1.setCategoria(producto.getCategoria());
        producto1.setStock(producto.getStock());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setNombre(producto.getNombre());
        producto1.setPrecio(producto.getPrecio());
        return productoRepository.save(producto1);
    }
    @DeleteMapping("producto/{id}")
    public void eliminarProducto(@PathVariable("id") Long id){
        productoRepository.deleteById(id);
    }

}
