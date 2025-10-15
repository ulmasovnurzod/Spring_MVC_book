package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dao.BookDAO;
import uz.pdp.model.Book;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDAO bookDAO;

    public List<Book> getAllBooks() {
        List<Book> allBooks = bookDAO.getAllBooks();
        return allBooks;
    }

    public Book getById(Integer id) {
        return bookDAO.getById(id);
    }

    public void save(Book book) {
        bookDAO.save(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(Integer id) {
        bookDAO.deleteBook(id);
    }

}
