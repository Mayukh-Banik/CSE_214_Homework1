// Mayukh Banik 114489797 Recitation 03
// mayukh.banik@stonybrook.edu Homework 1 CSE 214 TA:Jake Papaspiridakos, Scott Zheng
public class Bookshelf
{
    public static int CAPACITY = 20;
    private final Book[] books = new Book[CAPACITY];
    private int count;
    /**
     * constructor setting each value of books
     */
    public Bookshelf()
    {
        for (count = 0; count < CAPACITY; count++)
        {
            books[count] = new Book();
        }
    }
    /**
     * figures out the number of books in the array
     * @return int value of number of books
     */
    public int numBooks()
    {
        moveAllToTheLeft();
        int i = 0;
        for (count = 0; count < CAPACITY; count++)
        {
            if (books[count].getCondition() >= 1 && books[count].getCondition() <= 5)
            {
                i++;
            }
        }
        return i;
    }
    /**
     * a method to find the book at a specific selection
     * @param index the index of Book[] to take the value from
     * @return book
     */
    public Book getBook(int index)
    {
        if (books[index].getCondition() < 1 || books[index].getCondition() > 5)
        {
            throw new IllegalArgumentException("There is no book at that index. ");
        }
        return books[index];
    }
    /**
     * deletes the value at an index and shifts everything 1 to the left
     * @param index the array index with the book value that should be deleted
     * @return the book that was deleted
     * @throws EmptyShelfException incase they remove a book when there is nothing at the shelf
     */
    public Book removeBook(int index) throws EmptyShelfException
    {
        moveAllToTheLeft();
        if (books[0].getCondition() < 1 || books[0].getCondition() > 5)
        {
            throw new EmptyShelfException();
        }
        getBook(index);
        Book book = books[index];
        books[index] = new Book();
        moveAllToTheLeft();
        return book;
    }
    /**
     * sets a value at books[index] equal to the book and shifts everything else 1 to the right
     * @param index placement for book
     * @param book book to be added
     * @throws FullShelfException in case the shelf is full and no more can be added
     */
    public void addBook(int index, Book book) throws FullShelfException
    {
        if (index >= 20 || index < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Book location is not between 1 and 20.");
        }
        if (book.getCondition() > 5 || book.getCondition() < 1)
        {
            throw new IllegalArgumentException("The condition is not between 1-5. ");
        }
        moveAllToTheLeft();
        if (books[19].getCondition() != 0)
        {
            throw new FullShelfException();
        }
        else
        {
            for (int counter = CAPACITY - 1; counter > index; counter--)
            {
                books[counter] = books[counter - 1];
            }
        }
        books[index] = book;
    }
    /**
     * swaps the values at two indexes in the array
     * @param index1 1st thing to be swapped
     * @param index2 2nd thing to be swapped
     * @throws IllegalArgumentException in case the indexes are out of bounds
     */
    public void swapBook(int index1, int index2) throws IllegalArgumentException
    {
        getBook(index1);
        getBook(index2);
        if (index1 < 0 || index1 > 19 || index2 < 0 || index2 > 19)
        {
            throw new IllegalArgumentException("Incorrect Index Input.");
        }
        Book temp = books[index1];
        books[index1] = books[index2];
        books[index2] = temp;
    }
    /**
     * makes a new Book array with the value of the current Book[] array
     * @return an array of objects of Book type
     */
    public Object clone()
    {
        Object[] objects = new Book[CAPACITY];
        for ( int count = 0; count < CAPACITY; count++ )
        {
            objects[count] = books[count].clone();
        }
        return objects;
    }
    /**
     * returns a string value of all elements
     * @return string representation in table format of all books in the array
     */
    public String toString()
    {
        moveAllToTheLeft();
        String string = "";
        for (count = 0; count < CAPACITY; count++)
        {
            if (books[count].getCondition() <= 5 && books[count].getCondition() >= 1)
            {
                string = string + String.format("%-6d%-20s%-20s%-20d%-20s\n", (count + 1), books[count].getTitle(),
                        books[count].getAuthor(), books[count].getCondition(), books[count].getBorrower());
            }
        }
        return string;
    }
    /**
     * checks if the bookshelf is equal not checking for borrower though
     * @param obj which is the bookshelf
     * @return boolean value whether it is true or not
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof Bookshelf)
        {
            Bookshelf bookshelf = (Bookshelf) obj;
            if (bookshelf.numBooks() != numBooks())
            {
                return false;
            }
            for (int count = 0; count < CAPACITY; count++)
            {
                if (!bookshelf.books[count].equals(books[count]))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * deletes all holes in the array and shifts everything to the left with no holes
     */
    public void moveAllToTheLeft()
    {
        Book[] book1 = new Book[20];
        for (count = 0; count < CAPACITY; count++)
        {
            book1[count] = new Book();
        }
        for (int count1 = 0, count2 = 0; count1 < CAPACITY; count1++)
        {
            if (books[count1].getCondition() <= 5 && books[count1].getCondition() >= 1)
            {
                book1[count2] = (Book) books[count1].clone();
                book1[count2].setBorrower(books[count1].getBorrower());
                count2++;
            }
        }
        for (count = 0; count < CAPACITY; count++)
        {
            books[count] = new Book();
        }
        for (int counter = 0; counter < CAPACITY; counter++)
        {
            books[counter] = (Book) book1[counter].clone();
            books[counter].setBorrower(book1[counter].getBorrower());
        }
    }
}