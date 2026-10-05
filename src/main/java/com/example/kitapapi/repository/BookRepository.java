package com.example.kitapapi.repository;

import com.example.kitapapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStock(int stock);
}