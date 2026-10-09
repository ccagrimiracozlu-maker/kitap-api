package com.example.kitapapi.service;

import com.example.kitapapi.entity.Product;
import com.example.kitapapi.exception.ProductNotFoundException;
import com.example.kitapapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.kitapapi.dto.ProductRequestDTO;
import com.example.kitapapi.dto.ProductResponseDTO;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository ProductRepository;

    public ProductService(ProductRepository ProductRepository){
        this.ProductRepository = ProductRepository;
    }

    public List<Product> getAllProducts(){
        return ProductRepository.findAll();
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = toEntity(requestDTO);
        Product savedProduct = ProductRepository.save(product);

        return toResponseDTO(savedProduct);
    }

    public ProductResponseDTO getProductById(Long id){
        Product product = ProductRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return toResponseDTO(product);
    }


    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product existingProduct = ProductRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(requestDTO.getName());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setStock(requestDTO.getStock());

        Product updatedProduct = ProductRepository.save(existingProduct);

        return toResponseDTO(updatedProduct);
    }
    public void deleteProduct(Long id){
        if(!ProductRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }

        ProductRepository.deleteById(id);
    }
    public List<ProductResponseDTO> getProductsByStock(int stock) {
        return ProductRepository.findByStock(stock)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    public Page<ProductResponseDTO> getProducts(Pageable pageable){
        Page<Product> products = ProductRepository.findAll(pageable);
        return products.map(this::toResponseDTO);
    }

    private ProductResponseDTO toResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setStock(product.getStock());

        return responseDTO;
    }
    private Product toEntity(ProductRequestDTO requestDTO){
        Product product =new Product();

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        return product;
    }
}
