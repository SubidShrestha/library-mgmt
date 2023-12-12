package com.fruits.library.repository;

import com.fruits.library.entity.Book;
import com.fruits.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    @Query(value = """
                   SELECT b FROM Borrow b INNER JOIN Member m\s
                   ON b.member.id = m.id\s
                   WHERE m.id = :member_id\s
                    """)
    List<Borrow> findByMemberId(@Param("member_id")Long member_id);

    @Query(value = """
            SELECT b FROM Book b OUTER JOIN Borrow bb\s
            ON b.id = bb.book.id\s
            WHERE bb.issued_at IS NULL\s
            OR bb.returned_at IS NOT NULL\s
            """)
    List<Book> findAvailableBooks();
}
