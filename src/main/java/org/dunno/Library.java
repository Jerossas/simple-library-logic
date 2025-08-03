package org.dunno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (book == null || bookExists(book.getId())) {
            return false;
        }
        books.add(book);
        return true;
    }

    public List<Book> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            return Collections.emptyList();
        }
        return books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public boolean borrowBook(String id) {
        return findBookById(id)
                .map(book -> {
                    if (book.isBorrowed()) return false;
                    book.markAsBorrowed();
                    return true;
                })
                .orElse(false);
    }

    public boolean returnBook(String id) {
        return findBookById(id)
                .map(book -> {
                    if (!book.isBorrowed()) return false;
                    book.markAsReturned();
                    return true;
                })
                .orElse(false);
    }

    private boolean bookExists(String id) {
        return books.stream().anyMatch(b -> b.getId().equals(id));
    }

    private Optional<Book> findBookById(String id) {
        return books.stream()
                .filter(b -> b.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }
}