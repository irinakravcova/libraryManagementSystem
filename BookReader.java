package librarian;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class BookReader {
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();
    private Calendar lastBorrowing = null;

    public BookReader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.contains(book)) {
            return false;
        }
        borrowedBooks.add(book);
        lastBorrowing = Calendar.getInstance();
        return true;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            return true;
        }
        return false;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Calendar getLastBorrowing() {
        return lastBorrowing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReader reader = (BookReader) o;
        return name.equals(reader.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "BookReader{" +
                "name='" + name + '\'' +
                ", borrowedBooks number =" + borrowedBooks.size() + " borrowed Books title =  "+ getBorrowedBooks()+
                ", lastBorrowing=" + lastBorrowing +
                '}';
    }
}
