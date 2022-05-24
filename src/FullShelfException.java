// Mayukh Banik 114489797 Recitation 03
// mayukh.banik@stonybrook.edu Homework 1 CSE 214 TA:Jake Papaspiridakos, Scott Zheng
public class FullShelfException extends Exception
{
    /**
     * custom exception if the bookshelf is full
     */
    FullShelfException()
    {
        super("The shelf is full.");
    }
}
