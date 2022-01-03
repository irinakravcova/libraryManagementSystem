package librarian;

import java.util.Objects;

public class Book {
    private String title;
    private String isbn;
    private int copies;
    private int copiesAvailable;

    public Book(String title, int copies) {
        this.title = title;
        this.copies = copies;
        copiesAvailable = copies;
    }

    public String getTitle() {
        return title;
    }

    public int getCopies() {
        return copies;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean borrow() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
            return true;
        }
        return false;
    }

    public boolean returnToLibrary() {
        if (copiesAvailable < copies) {
            copiesAvailable++;
            return true;
        }
        return false;
    }

    public boolean addCopies(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        this.copies = this.copies + quantity;
        copiesAvailable = copiesAvailable + quantity;
        return true;
    }

    public boolean removeCopies(int quantity) {
        if (quantity <= 0) {
            return false;
        }
        if (copiesAvailable < quantity) {
            return false;
        }
        copies -= quantity;
        copiesAvailable -= quantity;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies=" + copies +
                ", copiesAvailable=" + copiesAvailable +
                '}';
    }
}
