package com.ryndrappf.uploadcsv.controller;

import com.ryndrappf.uploadcsv.dto.ResponseData;
import com.ryndrappf.uploadcsv.entity.Book;
import com.ryndrappf.uploadcsv.service.BookService;
import com.ryndrappf.uploadcsv.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAllBook(){
        ResponseData responseData = new ResponseData();

        try {
            List<Book> books = bookService.findAll();
            responseData.setStatus(true);
            responseData.getMessages().add("Get All Books");
            responseData.setPayLoad(books);
            return ResponseEntity.ok(responseData);

        }catch (Exception e){
            responseData.setStatus(false);
            responseData.getMessages().add("Could not Get Books" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        ResponseData responseData = new ResponseData();

        if (!CSVHelper.hasCSVFormat(file)){
            responseData.setStatus(false);
            responseData.getMessages().add("Please Upload a CSV file : " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            List<Book> books = bookService.save(file);
            responseData.setStatus(true);
            responseData.getMessages().add("Uploaded the File Success : " + file.getOriginalFilename());
            responseData.setPayLoad(books);
            return ResponseEntity.ok(responseData);

        }catch (Exception ex){
            responseData.setStatus(false);
            responseData.getMessages().add("Could not upload the file : " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
        }
    }
}
