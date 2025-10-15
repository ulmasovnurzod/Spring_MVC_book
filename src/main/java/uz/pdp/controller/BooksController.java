package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.model.Book;
import uz.pdp.service.BookService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;



    @GetMapping
    public ModelAndView getAllBooks(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        List<Book> bookList = bookService.getAllBooks();
        modelAndView.addObject("books", bookList);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addBook");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updatePage(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("updateBook");

        Book book = bookService.getById(id);
        modelAndView.addObject("books", book);

        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deletePage(@PathVariable(name = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        bookService.deleteBook(id);
        List<Book> bookList = bookService.getAllBooks();
        modelAndView.addObject("books", bookList);

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView saveBook(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "author") String author,
                                 @RequestParam(name = "year")String year,
                                 @RequestParam(name = "description")String description,
                                 @RequestParam(name = "coverImageUrl")String coverImageUrl){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        bookService.save(new Book(title,author,year,description,coverImageUrl));
        List<Book> bookList = bookService.getAllBooks();
        modelAndView.addObject("books", bookList);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateBook(@RequestParam(name = "id") Integer id,
                                   @RequestParam(name = "title") String title,
                                   @RequestParam(name = "author") String author,
                                   @RequestParam(name = "year")String year,
                                   @RequestParam(name = "description")String description,
                                   @RequestParam(name = "coverImageUrl")String coverImageUrl){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        bookService.updateBook(new Book(id,title,author,year,description,coverImageUrl));
        List<Book> bookList = bookService.getAllBooks();
        modelAndView.addObject("books", bookList);
        return modelAndView;
    }
}
