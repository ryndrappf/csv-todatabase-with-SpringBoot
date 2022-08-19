package com.ryndrappf.uploadcsv.service;

import com.ryndrappf.uploadcsv.entity.Book;
import com.ryndrappf.uploadcsv.repo.BookRepo;
import com.ryndrappf.uploadcsv.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> save(MultipartFile file){
        try {
            List<Book> books = CSVHelper.csvToBooks(file.getInputStream());
            return bookRepo.saveAll(books);

        }catch (IOException ex){
            throw new RuntimeException("Fail to store CSV data : " + ex.getMessage());
        }
    }

    public List<Book> findAll(){
        return bookRepo.findAll();
    }
}
