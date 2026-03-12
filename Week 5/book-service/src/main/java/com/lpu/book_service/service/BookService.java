package com.lpu.book_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lpu.book_service.dto.BookDto;
import com.lpu.book_service.entity.Book;
import com.lpu.book_service.repo.BookRepository;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book createBook(Book book) {
        return repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Book updateBook(Long id, Book updatedBook) {

        Book book = repo.findById(id).orElseThrow();

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPrice(updatedBook.getPrice());
        book.setQuantity(updatedBook.getQuantity());
        book.setCategory(updatedBook.getCategory());

        return repo.save(book);
    }
//    public BookDto convertToDTO(Book book) {
//
//        BookDto dto = new BookDto();
//
//        dto.setId(book.getId());
//        dto.setTitle(book.getTitle());
//        dto.setAuthor(book.getAuthor());
//        dto.setPrice(book.getPrice());
//
//        return dto;
//    }

    public void deleteBook(Long id) {
        repo.deleteById(id);
    }
}