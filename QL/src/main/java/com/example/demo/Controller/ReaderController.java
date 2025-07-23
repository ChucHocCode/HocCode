package com.example.demo.Controller;

import com.example.demo.DAO.ReaderService;
import com.example.demo.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    //them nguoi doc moi
    @PostMapping
    public Reader addReader(@RequestBody Reader reader){
        return readerService.addReader(reader);
    }

    //xem danh sach
    @GetMapping
    public List<Reader> getAllReader(){
        return readerService.getAllReader();
    }

//    @GetMapping("{/id}")
//    public Reader getReaderById(@PathVariable Integer id){
//        return readerService.
//    }

    //xoa
    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Integer id){
        readerService.deleteReader(id);
    }
}
