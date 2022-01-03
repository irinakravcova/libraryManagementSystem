package librarian;

import javax.swing.*;
import java.util.List;

public class Library {

    LibraryAPI lib = new LibraryInMemory();

    public static void main(String[] args) {
        Library library = new Library();
        library.mainMenu();
    }

    public void mainMenu() {
        int userChoice;

        do {
            String[] responses = {"Exit",
                    "Add book", "View book",
                    "Edit Book",
                    "Remove book",
                    "Borrow book",
                    "Return book",
                    "Add Reader",
                    "View Reader info",
                    "Long readers report"
            };

            userChoice = JOptionPane.showOptionDialog(null, "Welcome to Library. What you would like to do?",
                    "--", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, responses, 0);
            handleUserChoice(userChoice);
        } while (userChoice != 0);

    }

    private String askBookTitle() {
        return JOptionPane.showInputDialog(null, "Book title: ");
    }

    private String askBookIsbn() {
        return JOptionPane.showInputDialog(null, "Book new ISBN: ");
    }

    private Integer askBookCopies() {
        return Integer.valueOf(JOptionPane.showInputDialog(null, "Number of copies: "));
    }

    private Integer askBorrowingDays() {
        return Integer.valueOf(JOptionPane.showInputDialog(null, "Days for borrowing: "));
    }


    private String askReaderName() {
        return JOptionPane.showInputDialog(null, "Reader name: ");
    }

    private void handleUserChoice(int userChoice) {
        System.out.println("User choose: " + userChoice);
        switch (userChoice) {
            case -1:
            case 0: {
                System.out.println("Exiting...");
                System.exit(0);
                break;
            }
            case 1: {//Add a book
                String result = lib.addBook(askBookTitle(), askBookCopies());
                if (!"OK".equals(result)) {
                    System.err.println("Failed to add book: " + result);
                }
                break;
            }
            case 2: {//view book
                Book book = lib.findBook(askBookTitle());
                System.out.println("Book: " + book);
                break;
            }
            case 3: {//edit book
                // enter book title, find book
                Book book = lib.findBook(askBookTitle());

                if (book==null){
                    System.err.println("error");
                }
                // enter new ISBN
                book.setIsbn(askBookIsbn());
                break;
            }
            case 4: {
                // enter book title, find book
                lib.deleteBook(askBookTitle());
                break;
            }
            case 5: {//borrow book
                String name = askReaderName();
                BookReader reader = lib.findReader(name); // handle "not found"...

                if(reader == null){
                    System.out.println("reader with this name not found. ");
                    break;
                }
                lib.borrowBook(reader, askBookTitle());
            //    lib.borrowBook(askBookTitle()); case return book do the same
                //case string lib book
                break;
            }
            case 6: {//return book
                String name = askReaderName();
                BookReader reader = lib.findReader(name); // handle "not found"...
                lib.returnBook(reader, askBookTitle());
                break;
            }
            case 7: {
                String name = askReaderName();
                BookReader reader = lib.findReader(name);
                if (reader == null) {
                    lib.addReader(name);
                } else {
                    System.err.println("Reader with name '" + name + "' already exists.");
                }

                break;
            }
            case 8: {//view reader info
                String name = askReaderName();
                BookReader reader = lib.findReader(name); // handle "not found"...
                System.out.println("User info: " + reader);
                break;
            }
            case 9: {//report long readers
                Integer days = askBorrowingDays();
                List<BookReader> longReaders = lib.getLongReaders(days);
                for (BookReader longReader : longReaders) {
                    System.out.println(longReader);
                }
                if (longReaders.isEmpty()) {
                    System.out.println("No long readers found.");
                }
                break;
            }
            default: {
                System.err.println("Unknown choice: " + userChoice);
                break;
            }
        }
    }

}

