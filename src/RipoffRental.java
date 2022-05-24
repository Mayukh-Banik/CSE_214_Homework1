// Mayukh Banik 114489797 Recitation 03
// mayukh.banik@stonybrook.edu Homework 1 CSE 214 TA:Jake Papaspiridakos, Scott Zheng
import java.util.*;
public class RipoffRental
{
    private static final Bookshelf[] bookshelves = new Bookshelf[3];
    /**
     * initializes the bookshelf array and determines what method to call depending on the user input
     * @param args default
     * @throws FullShelfException a shelf is full
     * @throws EmptyShelfException a shelf is empty
     */
    public static void main(String[] args) throws FullShelfException, EmptyShelfException
    {
        for (int counter = 0; counter < 3; counter++ )
        {
            bookshelves[counter] = new Bookshelf();
        }
        int index = 0;
        System.out.println("Welcome to rip off rentals! Plug in your choice below: ");
        String input = "";
        while ( !input.equals("Q") )
        {
            initialOutput();
            input = input().nextLine().toUpperCase(Locale.ENGLISH);
            switch (input)
            {
                case "A":
                    add(bookshelves[index]);
                    break;
                case "S":
                    swap(bookshelves[index]);
                    break;
                case "L":
                    loan(bookshelves[index]);
                    break;
                case "R":
                    renameBook(bookshelves[index]);
                    break;
                case "D":
                    duplicateBook(bookshelves[index]);
                    break;
                case "C":
                    index = changeShelf();
                    break;
                case "O":
                    overWriteShelf(bookshelves, bookshelves[index], index);
                    break;
                case "E":
                    equalBookshelves(bookshelves);
                    break;
                case "P":
                    printBookshelf(bookshelves[index]);
                    break;
            }
        }
    }
    /**
     * the output for the menu and functions the user has access to
     */
    public static void initialOutput()
    {
        String[] table = {"A) Add Book", "S) Swap Book", "L) Loan Book", "R) Remove Book", "D) Duplicate Book",
                "C) Change Shelf", "O) Overwrite shelf with clone of current shelf",
                "E) Check if two shelves are equal", "P) Print current bookshelf", "Q) Quit"};
        for (int count = 0; count < 10; count++ )
        {
            System.out.println(table[count]);
        }
    }
    /**
     * UI interface to enter a book at a specific index range
     * @param bookshelves the bookshelf that is being added to
     * @throws FullShelfException in case that bookshelf is filled with books already
     */
    public static void add(Bookshelf bookshelves) throws FullShelfException
    {
        Book book = new Book();
        System.out.println("Please enter a title: ");
        book.setTitle(input().nextLine());
        System.out.println("Please enter an author: ");
        book.setAuthor(input().nextLine());
        System.out.println("Please enter the condition (1-5): ");
        book.setCondition(input().nextInt());
        System.out.println("Please enter the position on the shelf (1-20): ");
        bookshelves.addBook(input().nextInt() - 1, book);
        bookshelves.moveAllToTheLeft();
    }
    /**
     * UI interface to swap books at two different indexes
     * @param bookshelf bookshelf where the swap is occuring
     */
    public static void swap(Bookshelf bookshelf)
    {
        System.out.println("Enter index 1: ");
        int x = input().nextInt();
        System.out.println("Enter index 2: ");
        int y = input().nextInt();
        bookshelf.swapBook(x - 1, y - 1);
        bookshelf.moveAllToTheLeft();
    }
    /**
     * UI interface to loan out a book
     * @param bookshelf bookshelf where the book being loaned out is located
     */
    public static void loan(Bookshelf bookshelf)
    {
        System.out.println("Enter the index: ");
        int n = input().nextInt();
        System.out.println("Enter the name of the recipient: ");
        bookshelf.getBook(n - 1).setBorrower(input().nextLine());
        System.out.println("Enter the condition (1-5): ");
        bookshelf.getBook(n - 1).setCondition(input().nextInt());
        System.out.println("Borrowed!");
        System.out.println(bookshelf.getBook(n - 1).toString());
    }
    /**
     * removes a book at the specified index
     * @param bookshelf bookshelf where the removed book is located
     * @throws EmptyShelfException the shelf is empty so nothing can be removed
     */
    public static void renameBook(Bookshelf bookshelf) throws EmptyShelfException
    {
        System.out.println("Enter the index of the book you would like to remove: ");
        System.out.println(bookshelf.removeBook(input().nextInt() - 1).toString() + " is removed.");
        bookshelf.moveAllToTheLeft();
    }
    /**
     * creates a copy of the book at the specified index and makes a clone of it at the new index
     * @param bookshelf bookshelf where it is being duplicated
     * @throws FullShelfException if shelf is full can't duplicate a book
     */
    public static void duplicateBook(Bookshelf bookshelf) throws FullShelfException
    {
        System.out.println("Enter the index of the book you wish to make a copy of: ");
        int x = input().nextInt();
        System.out.println("Enter the index of the book that will be a cloned copy of the previous one: ");
        int y = input().nextInt();
        bookshelf.addBook(y - 1, bookshelf.getBook(x - 1));
        bookshelf.moveAllToTheLeft();
    }
    /**
     * reads the input for the shelf and changes the index of the Bookshelf[] being currently accessed
     * @return int value corresponding to index of Bookshelf array
     */
    public static int changeShelf()
    {
        System.out.println("What shelf do you want to look at? (A, B, or C?): ");
        return switchCase(input().nextLine());
    }
    /**
     * clones the current bookshelf and uses it to override another bookshelf
     * @param bookshelves array of Bookshelves to be accessed
     * @param bookshelf bookshelf that will be cloned
     * @param index currently accessed bookshelf
     * @throws FullShelfException a property of addBook that holds no importance here
     */
    public static void overWriteShelf(Bookshelf[] bookshelves, Bookshelf bookshelf, int index) throws FullShelfException
    {
        System.out.println("What bookshelf do you wish to make a clone of the one you are currently looking at?");
        String input = input().nextLine();
        int x = switchCase(input);
        if (index == x)
        {
            throw new IllegalArgumentException("You cannot clone to the bookshelf you are currently looking at. ");
        }
        for (int counter = 0; counter < bookshelf.numBooks(); counter++ )
        {
            bookshelves[x].addBook(counter, bookshelf.getBook(counter));
        }
    }
    /**
     * checks if two bookshelves are in the same order with same properties, borrower not checked
     * @param bookshelves array of Bookshelves to access all bookshelves
     */
    public static void equalBookshelves(Bookshelf[] bookshelves)
    {
        System.out.println("What is the first Bookshelf you would like to compare? (A, B, C): ");
        int x = switchCase(input().nextLine());
        System.out.println("What is the second Bookshelf you would like to compare? (A, B, C): ");
        int y = switchCase(input().nextLine());
        System.out.println("The bookshelves are equal: " + bookshelves[x].equals(bookshelves[y]));
    }
    /**
     * Prints out the current bookshelf
     * @param bookshelf accesses the bookshelf that will be printed
     */
    public static void printBookshelf(Bookshelf bookshelf)
    {
        System.out.printf("%-6s%-20s%-20s%-20s%-20s\n", "Index", "Title", "Author", "Condition", "Borrower");
        System.out.println(bookshelf.toString());
    }
    /**
     * creates a scanner class for inputs in multiple methods
     * @return a scanner to read console inputs
     */
    public static Scanner input()
    {
        return new Scanner(System.in);
    }
    /**
     * a method to check which bookshelf was inputted and to give a useable readout to the rest of the program
     * @param string for the input that the user put in
     * @return the index for the Bookshelf being accessed
     */
    public static int switchCase(String string)
    {
        string = string.toUpperCase(Locale.ENGLISH);
        int n;
        switch (string)
        {
            case "A":
                n =  0;
                break;
            case "B":
                n = 1;
                break;
            case "C":
                n = 2;
                break;
            default:
                n = -1;
                break;
        }
        if (n == -1)
        {
            throw new IllegalArgumentException("You did not input (A, B, or C) or its lowercase formats.");
        }
        return n;
    }
}