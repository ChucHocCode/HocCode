package com.example.demo.DAO;

import com.example.demo.Repository.ReaderRepository;
import com.example.demo.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader addReader(Reader r){
        return readerRepository.save(r);
    }

    public List<Reader> getAllReader(){
        return readerRepository.findAll();
    }

    //xoa
    public void deleteReader(Integer readerId){
        readerRepository.deleteById(readerId);
    }



}