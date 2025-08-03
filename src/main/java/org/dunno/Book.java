package org.dunno;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean borrowed;

    public Book(String id, String title, String author) {
        if (id == null || id.isBlank() || title == null || title.isBlank()) {
            throw new IllegalArgumentException("ID y título son requeridos");
        }
        this.id = id;
        this.title = title;
        this.author = author != null ? author : "Anónimo";
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void markAsBorrowed() {
        if (this.borrowed) {
            throw new IllegalStateException("El libro ya está prestado");
        }
        this.borrowed = true;
    }

    public void markAsReturned() {
        if (!this.borrowed) {
            throw new IllegalStateException("El libro no estaba prestado");
        }
        this.borrowed = false;
    }
}