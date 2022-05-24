// Mayukh Banik 114489797 Recitation 03
// mayukh.banik@stonybrook.edu Homework 1 CSE 214 TA:Jake Papaspiridakos, Scott Zheng
public class Book
{
    private String title = "", author = "", borrower = "N/A";
    private int condition = 0;
    /**
     * Default constructor
     */
    public Book() {}
    /**
     * Initializes input data for a book class
     * @param title title of the book
     * @param author author of the book
     * @param condition condition of the book
     * @param borrower borrower of the book
     */
    public Book (String title, String author, int condition, String borrower)
    {
        this.title = title;
        this.author = author;
        this.condition = condition;
        this.borrower = borrower;
    }
    /**
     * returns book title
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * returns book author
     * @return string author
     */
    public String getAuthor()
    {
        return author;
    }
    /**
     * returns book borrower
     * @return borrower
     */
    public String getBorrower()
    {
        return borrower;
    }
    /**
     * returns book condition
     * @return condition
     */
    public int getCondition()
    {
        return condition;
    }
    /**
     * input author for book to be set to
     * @param author set author
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    /**
     * input condition for book to be set to
     * @param condition set condition
     */
    public void setCondition(int condition)
    {
        this.condition = condition;
    }
    /**
     * input borrower for book to be set to
     * @param borrower set borrower
     */
    public void setBorrower(String borrower)
    {
        this.borrower = borrower;
    }
    /**
     * input title for book to be set to
     * @param title set title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * returns string form of the book
     * @return string output of Book properties
     */
    public String toString()
    {
        return "Book: " + getTitle() + ", Author: " + getAuthor() + ", Condition: " + getCondition() +
                ", Borrower: " + getBorrower();
    }
    /**
     * takes an argument of object, and compares it to a book to see if the values
     * not including borrower are the same
     * @param obj to be compared to this book
     * @return boolean type to be utilized in other methods
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof Book)
        {
            Book book = (Book) obj;
            if ( !this.title.equals(book.getTitle()) )
            {
                return false;
            }
            if ( !this.author.equals(book.getAuthor()))
            {
                return false;
            }
            if ( this.condition != book.getCondition())
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        return true;
    }
    /**
     * returns a copy without the borrower of this book
     * @return object of Book type
     */
    public Object clone()
    {
        return new Book(this.title, this.author, this.condition, "");
    }
}