package com.example.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.models.Book;
import com.example.book.models.LoginUser;
import com.example.book.models.User;
import com.example.book.services.BookService;
import com.example.book.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private UserService users;
    
    @Autowired
    private BookService books;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User newUser, BindingResult result) {
        User user = users.register(newUser, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
        User user = users.login(newLogin, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok(books.allBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        books.createBook(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        Book book = books.findBook(id);
        if (book == null) {
            return ResponseEntity.status(404).body("Book not found");
        }

        return ResponseEntity.ok(book);
    }

    @PutMapping("/books/{id}")
public ResponseEntity<?> updateBook(@PathVariable("id") Long id, @Valid @RequestBody Book updatedBook, BindingResult result) {
    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    Book existingBook = books.findBook(id);
    if (existingBook == null) {
        return ResponseEntity.status(404).body("Book not found");
    }

    // Update all fields
    existingBook.setTitle(updatedBook.getTitle());
    existingBook.setAuthor(updatedBook.getAuthor());
    existingBook.setCode(updatedBook.getCode());
    existingBook.setType(updatedBook.getType());
    existingBook.setPublisher(updatedBook.getPublisher());
    existingBook.setEdition(updatedBook.getEdition());
    existingBook.setYear(updatedBook.getYear());
    existingBook.setCirculation(updatedBook.getCirculation());
    existingBook.setSchool(updatedBook.getSchool());
    existingBook.setIsb(updatedBook.getIsb());
    existingBook.setSignOffStatus(updatedBook.getSignOffStatus());
    existingBook.setTopic(updatedBook.getTopic());
    existingBook.setKeywords(updatedBook.getKeywords());

    books.updateBook(existingBook);

    return ResponseEntity.ok(existingBook);
}

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        Book book = books.findBook(id);
        if (book == null) {
            return ResponseEntity.status(404).body("Book not found");
        }

        books.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}
