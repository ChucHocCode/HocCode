package com.example.demo.DAO;

import com.example.demo.Repository.BookRepository;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book b){
        return bookRepository.save(b);//them vao
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //ham tim kiem sach bang ma
    public Book getBookByCode(Integer code){
        return bookRepository.findById(code).orElse(null);
    }

    //xoa sach
    public void deleteBook(Integer code){
        bookRepository.deleteById(code);
    }

    //tim sach bang ten
    public List<Book> getByName(String keyword){
        return bookRepository.findByBookNameContaining(keyword);
    }

    //update
    public void updateQuantity(Integer code, Integer change){
        Book b  =getBookByCode(code);
        if(b!= null){
            b.setQuantity(b.getQuantity()+change);
            bookRepository.save(b);
        }
    }

}