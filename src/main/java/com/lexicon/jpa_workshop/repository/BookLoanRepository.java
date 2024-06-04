package com.lexicon.jpa_workshop.repository;

import com.lexicon.jpa_workshop.Entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository  extends JpaRepository<BookLoan, Long> {

    List<BookLoan> findByBorrower_Id(Long borrowerId);

    List<BookLoan> findByBook_Id(Long bookId);

    List<BookLoan> findByReturnedIsFalse();

    List<BookLoan> findByDueDateBeforeAndReturnedIsFalse(LocalDate date);

    List<BookLoan> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("UPDATE BookLoan b SET b.returned = true WHERE b.id = :id")
    void markBookLoanAsReturnedById(@Param("id") Long id);

}
