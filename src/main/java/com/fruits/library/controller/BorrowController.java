package com.fruits.library.controller;

import com.fruits.library.entity.Book;
import com.fruits.library.entity.Borrow;
import com.fruits.library.entity.Member;
import com.fruits.library.repository.BookRepository;
import com.fruits.library.repository.MemberRepository;
import com.fruits.library.requestBody.BorrowRequest;
import com.fruits.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    @GetMapping
    public List<Borrow> getAllBorrowedBookRecords(@RequestParam(required = false) Long memberId){
        if (memberId != null){
            return borrowService.getAllBooksBorrowedByMember(memberId);
        }
        return borrowService.getAllBorrowedBooks();
    }
    @GetMapping("/{id}")
    public Borrow getBorrowRecord(@PathVariable Long id){
        return borrowService.getBorrowRecord(id);
    }
    @GetMapping("/available")
    public List<Book> getAllAvailableBooks(){
        return borrowService.getAllAvailableBooks();
    }
    @GetMapping("/{memberId}/return")
    public List<Borrow> getReturnedBooks(@PathVariable Long memberId){
        return borrowService.getBooksReturnedByMember(memberId);
    }
    @PostMapping
    public List<Borrow> addBorrowRecords(@RequestBody BorrowRequest borrowRequest){
        List<Book> bookList = bookRepository.findAllById(borrowRequest.getBookId());
        Member member = memberRepository.findById(borrowRequest.getMemberId()).orElse(null);
        if (member != null){
            return borrowService.addBorrowRecord(borrowRequest.getBookId(),member,borrowRequest.getIssued_at(),borrowRequest.getReturn_due());
        }
        return null;
    }
    @PutMapping("/{id}")
    public Borrow updateBorrowRecord(@RequestBody Borrow borrow, @PathVariable Long id){
        return borrowService.updateBorrowRecord(borrow,id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id){
        borrowService.deleteBorrowRecord(id);
        return ResponseEntity.ok("Borrow record has been successfully deleted");
    }
}
