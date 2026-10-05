package com.example.kitapapi.service;

import com.example.kitapapi.entity.Book;
import com.example.kitapapi.exception.BookNotFoundException;
import com.example.kitapapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.kitapapi.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


    public Book updateBook(Long id, Book book){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        existingBook.setName(book.getName());
        existingBook.setPrice(book.getPrice());

        return bookRepository.save(existingBook);
    }
    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }

        bookRepository.deleteById(id);
    }
    public List<Book> getBooksByStock(int stock) {
        return bookRepository.findByStock(stock);
    }
    public Page<Book> getBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }
}
