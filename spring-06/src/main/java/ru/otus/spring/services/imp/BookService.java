package ru.otus.spring.services.imp;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    Book saveBook(long author_id, long genre_id, String bookname);

    Book removeBook(long id);

    Book findByIdBook(long id);

    List<Book> findByGenreBook(long genreId);

    List<Book> findByAuthorBook(long authorId);

    List<Book> findAllBooks();
}
