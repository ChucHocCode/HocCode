package com.example.demo.Controller;

import com.example.demo.DAO.BorrowService;
import com.example.demo.model.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reader")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public Borrow addReader(@RequestParam Integer readerId,
                            @RequestParam Integer bookCode,
                            @RequestParam String readerName,
                            @RequestParam Integer quantity) throws Exception{
        return borrowService.addBorrow(readerId,bookCode,readerName,quantity);
    }

    @GetMapping
    public List<Borrow> getAllBorrow(){
        return borrowService.getAllBorrow();
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow (@PathVariable Integer id){
        borrowService.deleteBorrow(id);
    }

}
