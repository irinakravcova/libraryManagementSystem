package librarian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LibraryInDb implements LibraryAPI {
    @Override
    public Book findBook(String title) {
//        Connection c = DriverManager.getConnection("URL");
//        PreparedStatement ps = c.prepareStatement("SELECT title, isbn, copies, copies_available FROM books WHERE title = :?");
//        try {
//            ps.setString(1, title);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public String addBook(String title, int quantity) {
        return null;
    }

    @Override
    public String removeBook(String title, int quantity) {
        return null;
    }

    @Override
    public String deleteBook(String title) {
        return null;
    }

    @Override
    public String borrowBook(BookReader reader, String title) {
        return null;
    }

    @Override
    public String returnBook(BookReader reader, String title) {
        return null;
    }

    @Override
    public List<Book> getBooksBorrowed(BookReader reader) {
        return null;
    }

    @Override
    public List<BookReader> getLongReaders(int numDays) {
        return null;
    }

    @Override
    public String addReader(String name) {
        return null;
    }

    @Override
    public BookReader findReader(String name) {
        return null;
    }
}
