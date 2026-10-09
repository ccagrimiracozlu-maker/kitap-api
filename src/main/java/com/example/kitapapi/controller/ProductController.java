package com.example.kitapapi.controller;

import com.example.kitapapi.dto.ProductRequestDTO;
import com.example.kitapapi.dto.ProductResponseDTO;
import com.example.kitapapi.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService ProductService;

    public ProductController(ProductService ProductService){
        this.ProductService = ProductService;
    }

    @GetMapping
   public Page<ProductResponseDTO> getAllProducts(
           @RequestParam(required = false) Integer stock,
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return ProductService.getProducts(pageable);
    }

    @GetMapping("/filter")
    public List<ProductResponseDTO> getProductByStock(@RequestParam int stock){
        return ProductService.getProductsByStock(stock);
    }


    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return ProductService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO requestDTO){
        return ProductService.updateProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        ProductService.deleteProduct(id);
    }

    @PostMapping
    public ProductResponseDTO createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO) {
        return ProductService.createProduct(requestDTO);
    }
}
