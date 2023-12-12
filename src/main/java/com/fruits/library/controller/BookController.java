package com.fruits.library.controller;

import com.fruits.library.entity.Book;
import com.fruits.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String author){
        if (author != null){
            return bookService.getBooksByAuthor(author);
        }
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBook(id);
    }
    @PostMapping
    public Book addNewBook(@RequestBody Book book){
        return bookService.addBooks(book);
    }
    @PostMapping("/many")
    public List<Book> addAllBooks(@RequestBody List<Book> bookList){
        return bookService.addManyBooks(bookList);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(book,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book has been deleted from the records");
    }
}
