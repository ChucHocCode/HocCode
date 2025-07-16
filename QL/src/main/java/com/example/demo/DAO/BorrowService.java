package com.example.demo.DAO;

import Repository.BookRepository;
import Repository.BorrowRepository;
import model.Borrow;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;


@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    //Them muon sach
    public Borrow addBorrow(Integer readerId,Integer bookCode , String readerName , Integer quantity) throws Exception{
        //ktra sacg co ton tai hay k
        Book book=bookRepository.findById(bookCode).orElse(null);
        if(book==null){
            throw new Exception("Book not found!");
        }

        //ktra so luong sach
        if(book.getQuantity()<quantity){
            throw new Exception("Not enough quantity");
        }

        //giam so luong sach
        book.setQuantity(book.getQuantity() -quantity);
        bookRepository.save(book);

        //luu phieu muon
        Date today= Date.valueOf(LocalDate.now());
        Borrow borrow = new Borrow(readerId,bookCode,today,null, quantity,readerName);
        return borrowRepository.save(borrow);

    }
    public List<Borrow> getAllBorrow(){
        return borrowRepository.findAll();
    }
    public void deleteBorrow(Integer borrowId){
        borrowRepository.deleteById(borrowId);
    }
}
