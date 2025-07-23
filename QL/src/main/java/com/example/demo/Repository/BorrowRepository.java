package com.example.demo.Repository;
import com.example.demo.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowRepository  extends JpaRepository<Borrow,Integer> {
//    List<Borrow> findByBorrowNameContaining(String keyword);
}
