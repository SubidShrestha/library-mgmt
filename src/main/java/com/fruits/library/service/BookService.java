package com.fruits.library.service;

import com.fruits.library.entity.Book;
import com.fruits.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<Book> getAllBooks(){
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public Book getBook(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public List<Book> getBooksByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    public Book addBooks(Book book){
        return bookRepository.save(book);
    }
    public List<Book> addManyBooks(List<Book> bookList){
        return bookRepository.saveAll(bookList);
    }
    public Book updateBook(Book newBook, Long bookId){
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null){
            book.setTitle(newBook.getTitle());
            book.setIsbn(newBook.getIsbn());
            book.setAuthor(newBook.getAuthor());
            book.setPublished_date(newBook.getPublished_date());
            return bookRepository.save(book);
        }
        return null;
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
