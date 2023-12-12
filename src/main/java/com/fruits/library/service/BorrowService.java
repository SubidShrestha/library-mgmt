package com.fruits.library.service;

import com.fruits.library.entity.Book;
import com.fruits.library.entity.Borrow;
import com.fruits.library.entity.Member;
import com.fruits.library.repository.BookRepository;
import com.fruits.library.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    public List<Borrow> getAllBorrowedBooks(){
        return borrowRepository.findAll();
    }
    public List<Book> getAllAvailableBooks(){
        return borrowRepository.findAvailableBooks();
    }
    public List<Borrow> getAllBooksBorrowedByMember(Long memberId){
        return borrowRepository.findByMemberId(memberId);
    }
    public List<Borrow> getBooksReturnedByMember(Long memberId){
        List<Borrow> borrows = borrowRepository.findByMemberId(memberId);
        List<Borrow> returnedBooks = new ArrayList<>();
        for (Borrow borrow : borrows){
            if (borrow.getReturned_at() != null){
                returnedBooks.add(borrow);
            }
        }
        return returnedBooks;
    }
    public List<Borrow> addBorrowRecord(List<Long> bookIds, Member member, LocalDate issuedDate, LocalDate dueDate){
        List<Book> availableBooks = borrowRepository.findAvailableBooks();
        List<Book> requestBooks = bookRepository.findAllById(bookIds);
        List<Borrow> borrowList = new ArrayList<>();
        if (new HashSet<>(availableBooks).containsAll(requestBooks)){
                for (Book requestBook : requestBooks){
                    borrowList.add(Borrow.builder()
                                    .book(requestBook)
                                    .member(member)
                                    .issued_at(issuedDate)
                                    .return_due(dueDate)
                            .build());
                }
            return borrowRepository.saveAll(borrowList);
        }
        return null;
    }
    public Borrow updateReturnedDate(LocalDate date, Long id){
        Borrow book = borrowRepository.findById(id).orElse(null);
        if (book != null){
            book.setReturned_at(date);
            return borrowRepository.save(book);
        }
        return null;
    }
    public Borrow updateBorrowRecord(Borrow book, Long id){
        Borrow borrow = borrowRepository.findById(id).orElse(null);
        if (borrow != null){
            borrow.setMember(book.getMember());
            borrow.setBook(book.getBook());
            borrow.setIssued_at(book.getIssued_at());
            borrow.setReturn_due(book.getReturn_due());
            borrow.setReturned_at(book.getReturned_at());
            return borrowRepository.save(borrow);
        }
        return null;
    }
    public List<Borrow> getReturnDueBooks(){
        List<Borrow> borrowList = borrowRepository.findAll();
        List<Borrow> unreturnedBook = new ArrayList<>();
        for (Borrow borrow : borrowList){
            if (borrow.getReturned_at() == null && borrow.getIssued_at() != null){
                unreturnedBook.add(borrow);
            }
        }
        return unreturnedBook;
    }
    public void deleteBorrowRecord(Long id){
        borrowRepository.deleteById(id);
    }

    public Borrow getBorrowRecord(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }
}
