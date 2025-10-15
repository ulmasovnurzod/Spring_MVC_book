package uz.pdp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Book;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Repository
public class BookDAO {




    private final JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getString("year"));
            book.setDescription(rs.getString("description"));
            book.setCoverImageUrl(rs.getString("cover_image_url"));
            return book;
        });
    }

    public Book getById(Integer id) {

        String sql = "select * from books where id = ?";

        List<Book> books = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setYear(rs.getString("year"));
            book.setDescription(rs.getString("description"));
            book.setCoverImageUrl(rs.getString("cover_image_url"));
            return book;

        });
        if (books.isEmpty()) {
            throw new RuntimeException("Kitob topilmadi, id: " + id);
        }

        return books.get(0);


    }

    public void save(Book book) {
        String sql = "insert into books(title, author, year, description, cover_image_url) values (?,?,?,?,?)";

         jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getDescription(),
                book.getCoverImageUrl());
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET  title = ?, author = ?, year = ?, description = ?,cover_image_url = ? WHERE id = ?";

         jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getDescription(),
                book.getCoverImageUrl(),
                book.getId());
    }

    public int deleteBook(Integer id) {
        String sql = "delete from books where id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
