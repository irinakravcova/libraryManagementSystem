package librarian;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LibraryInMemory implements LibraryAPI {

    String filepath = "library.txt";

    List<Book> books = new ArrayList<>();
    List<BookReader> readers = new ArrayList<>();

    /**
     * Try to find book with specified title in library. If not found - return null.
     *
     * @param title book to be searched
     * @return found book or null if not found
     */
    public Book findBook(String title) {
        Book toFind = new Book(title, 0);
        for (Book book : books) {
            if (book.equals(toFind)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public BookReader findReader(String name) {
        BookReader toFind = new BookReader(name);
        for (BookReader reader : readers) {
            if (reader.equals(toFind)) {
                return reader;
            }
        }
        return null;
    }

    @Override
    public String addBook(String title, int quantity) {
        Book book = findBook(title);
        if (book == null) {
            Book newBook = new Book(title, quantity);
            books.add(newBook);
            log("Book quantity '" + title + "' added by " + quantity);
            return "OK";
        }
        if (book.addCopies(quantity)) {
            log("Book quantity '" + title + "' added by " + quantity);
            return "OK";
        }
        return log("Failed to add books");
    }

    @Override
    public String removeBook(String title, int quantity) {
        Book book = findBook(title);
        if (book == null) {
            return log("Remove: book with title '" + title + "'not found");
        }
        if (book.removeCopies(quantity)) {
            log("Book with title '" + title + "': number of copies decreased to " + book.getCopies());
            return "OK";
        }
        return log("Failed to remove book with title '" + title + "'");
    }

    @Override
    public String deleteBook(String title) {
       // return log("DELETE BOOK NOT IMPLEMENTED YET");
        Book book = findBook(title);
        if (book == null)
        {
            return log("Delete: book with title '" + title + "' not found. ");
        }
        if(book.getCopies() == book.getCopiesAvailable()){
            books.remove(findBook(title));
            return log("OK");
        }
        return log("Failed to delete book with title '" + "'");
    }

    @Override
    public String borrowBook(BookReader reader, String title) {
        if (reader.getBorrowedBooks().size() >= 5) {
            return log("Unable to borrow more than 5 books");
        }
        Book book = findBook(title);
        if (book == null) {
            return log("Book '" + title + "' not found");
        }
        if (book.borrow()) {
            if (reader.borrowBook(book)) {
                log("Book '" + title + "' has been borrowed by '" + reader.getName() + "'");
                return "OK";
            }
            // we have a problem - cannot add to list of borrowed books
            if (!book.returnToLibrary()) {
                return log("Failed to return book '" + title + "'. Quantity issues?.."); // should never happen
            }
            return log("Failed to borrow book '" + title + "' by '" + reader.getName() + "' - already borrowed");
        }
        return log("Failed to borrow '" + title + "' - no available books");
    }

    @Override
    public String returnBook(BookReader reader, String title) {
        Book book = findBook(title);
        if (book == null) {
            return log("Book '" + title + "' not found");
        }
        if (!reader.returnBook(book)) {
            return log("Reader '" + reader.getName() + "' has no this book borrowed");
        }
        if (!book.returnToLibrary()) {
            return log("Failed to return book '" + title + "' to reader '" + reader.getName() + "'. Quantity issues?.."); // should never happen
        }
        return "OK";
    }

    @Override
    public List<Book> getBooksBorrowed(BookReader reader) {
        return reader.getBorrowedBooks();
    }

    @Override
    public List<BookReader> getLongReaders(int numDays) {
        List<BookReader> longReaders = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        for (BookReader reader : readers) {
            if (reader.getBorrowedBooks().isEmpty()) {
                continue;
            }//CALENDAR NEEDS TO BE UPDATED
            Calendar lastBorrowing = reader.getLastBorrowing();
            Calendar lastDateToRead = (Calendar) lastBorrowing.clone();
            lastDateToRead.add(Calendar.DATE, numDays);
            if (now.after(lastDateToRead)) {
                longReaders.add(reader);
            }
        }
        return longReaders;
    }

    @Override
    public String addReader(String name) {
        BookReader reader = findReader(name);
        if (reader == null) {
            BookReader newReader = new BookReader(name);
            readers.add(newReader);
            log("Reader added: " + name);
            return "OK";
        }
        log("Failed to add reader '" + name + "': reader already exists");
        return "Failed to add reader: reader already exists";
    }

    private String log(String message) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(message);
            pw.flush();
            pw.close();
            return message;

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Failed to save log entry");
            return "Failed to add log message: " + message;
        }
    }

}
