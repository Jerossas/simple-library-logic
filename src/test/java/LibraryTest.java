import org.dunno.Book;
import org.dunno.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Book book;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("XD01", "100 años de seriedad", "García Márquez");
        library.addBook(book);
    }

    @Test
    void testAddBook_Success() {
        Book newBook = new Book("XD02", "Otro libro", "Autor");
        assertTrue(library.addBook(newBook));
        assertEquals(2, library.getBooks().size());
    }

    @Test
    void testBorrowBook_FirstTime() {
        assertTrue(library.borrowBook("XD01"));
        assertTrue(book.isBorrowed());
    }

    @Test
    void testBorrowBook_AlreadyBorrowed() {
        library.borrowBook("XD01");
        assertFalse(library.borrowBook("XD01"));
    }

    @Test
    void testReturnBook_Success() {
        library.borrowBook("XD01");
        assertTrue(library.returnBook("XD01"));
        assertFalse(book.isBorrowed());
    }

    @Test
    void testReturnBook_NotBorrowed() {
        assertFalse(library.returnBook("XD01"));
    }

    @Test
    void testFindByTitle_NoMatches() {
        assertTrue(library.findByTitle("Título inexistente").isEmpty());
    }
}