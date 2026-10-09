package com.example.kitapapi.service;

import com.example.kitapapi.entity.Product;
import com.example.kitapapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.kitapapi.exception.ProductNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.kitapapi.dto.ProductResponseDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository ProductRepository;

    @InjectMocks
    ProductService ProductService;
    @Test
    void getProductById_gecerliId_kitabiGetirir(){
        //hazırlık
        Product Product=new Product();
        Product.setName("Suç ve Ceza");
        Product.setPrice(150);
        Product.setStock(5);

        when(ProductRepository.findById(1L))
                .thenReturn(Optional.of(Product));

        //işlem
        ProductResponseDTO result =ProductService.getProductById(1L);

        //Kontrol
        assertEquals("Suç ve Ceza" , result.getName());
    }

    @Test
    void getProductById_gecersizId_hataFirlatiyor(){
        //Hazırlık
        when(ProductRepository.findById(999L))
                .thenReturn(Optional.empty());
        //işlem ve Kontrol
        assertThrows(ProductNotFoundException.class, () -> {
            ProductService.getProductById(999L);
        });
        }
    }

