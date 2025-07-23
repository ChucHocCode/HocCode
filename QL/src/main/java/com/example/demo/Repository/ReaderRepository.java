package com.example.demo.Repository;

import com.example.demo.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Integer> {
//    List<Reader> findByReaderNameContaining(String keyword);
}
