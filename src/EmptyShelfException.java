// Mayukh Banik 114489797 Recitation 03
// mayukh.banik@stonybrook.edu Homework 1 CSE 214 TA:Jake Papaspiridakos, Scott Zheng
public class EmptyShelfException extends Exception
{
    /**
     * custom exception for an empty bookshelf
     */
    EmptyShelfException()
    {
        System.out.println("The shelf is empty.");
    }
}
