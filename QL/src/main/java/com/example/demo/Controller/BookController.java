package com.example.demo.Controller;

import com.example.demo.DAO.BookService;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String keyword){
        return bookService.getByName(keyword);
    }

    @DeleteMapping("/{code}")
    public void deleteBook(@PathVariable Integer code){
        bookService.deleteBook(code);
    }

    @PutMapping("/{code}/quantity")
    public void updateQuantity(@PathVariable Integer code,@RequestParam Integer change){
        bookService.updateQuantity(code, change);
    }
}
