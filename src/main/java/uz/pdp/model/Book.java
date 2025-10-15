package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private Integer id;
    private String title;
    private String author;
    private String year;
    private String description;
    private String coverImageUrl;

    public Book(String title, String author, String year, String description, String coverImageUrl) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.coverImageUrl = coverImageUrl;
    }
}
