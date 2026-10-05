package com.example.kitapapi.service;

import com.example.kitapapi.entity.Book;
import com.example.kitapapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.kitapapi.exception.BookNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;
    @Test
    void getBookById_gecerliId_kitabiGetirir(){
        //hazırlık
        Book book=new Book();
        book.setName("Suç ve Ceza");
        book.setPrice(150);
        book.setStock(5);

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        //işlem
        Book result =bookService.getBookById(1L);

        //Kontrol
        assertEquals("Suç ve Ceza" , result.getName());
    }

    @Test
    void getBookById_gecersizId_hataFirlatiyor(){
        //Hazırlık
        when(bookRepository.findById(999L))
                .thenReturn(Optional.empty());
        //işlem ve Kontrol
        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookById(999L);
        });
        }
    }

