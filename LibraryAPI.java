package librarian;

import java.util.List;

public interface LibraryAPI {
    Book findBook(String title);
    String addBook(String title, int quantity);

    /**
     * Remove some available instances of book from library.
     * @param title    book title
     * @param quantity quantity to remove
     * @return "OK" on success
     */
    String removeBook(String title, int quantity);

    /**
     * Completely remove book from library. Can be done only if
     * no reader borrowed a single instance of book.
     * @param title book title
     * @return "OK" on success
     */
    String deleteBook(String title);

    String borrowBook(BookReader reader, String title);

    String returnBook(BookReader reader, String title);

    List<Book> getBooksBorrowed(BookReader reader);

    /**
     * Return list of BookReaders who exceeded number of days reading since last
     * book has been borrowed.
     *
     * @param numDays number of days to be considered as "long reading"
     * @return list of readers
     */
    List<BookReader> getLongReaders(int numDays);

    String addReader(String name);
    BookReader findReader(String name);
}
