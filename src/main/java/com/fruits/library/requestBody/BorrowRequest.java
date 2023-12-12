package com.fruits.library.requestBody;

import com.fruits.library.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowRequest {
    private List<Long> bookId;
    private Long memberId;
    private LocalDate issued_at;
    private LocalDate return_due;
}
