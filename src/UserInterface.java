/***************************
Author: Austin Bevacqua
Date: 17/05/2020
 Last Modified: 31/10/2020
Purpose: A static class that provides user input and validation, as well as methods for displaying information to the screen
***************************/
//CITATION: Class used by Austin Bevacqua (20162896) in PDI Assessment submission, and DSA Worksheets submissions 2-8.
import java.util.*;

public class UserInterface
{
    /*************************
    Author: Austin Bevacqua
    Date: 17/05/2020
    Last Modified: 31/10/2020
    Purpose: To provide a method with functionality for user input with the INTEGER data type with exception handling
    *********************/
    public static int userInput(int min, int max, String prompt)
    {
        //CITATION: Method used by Austin Bevacqua (20162896) in PDI Assessment submission, and DSA Worksheets submissions 2-8.
        Scanner sc = new Scanner(System.in);
        String out;
        int value;
        out = prompt;
        do
        {
            try 
            {
                println(out);
                value = sc.nextInt();
            } 
            catch (InputMismatchException e)  
            { //if the user inputs a value that is not an int, the exception will be caught
                sc.next(); //Input that caused the error is removed
                value = (min - 1); //Sets 'value' to an invalid value range so do while loop continues
                displayError("Input in incorrect format. Please input an integer"); //Error is displayed through submodule
            }
            out = ("Input not in range of " + min + "-" + max + "\n" + prompt);
             //The "output" is changed to include an error message, if the loop continues, the user will be able to view the error message.
        } while (value < min || value > max);
        //ASSERTION: value is between MIN and MAX
        return value;
    }

    /*************************
    Author: Austin Bevacqua
    Date: 17/05/2020
     Last Modified: 31/10/2020
    Import: min (Int), max (Int), prompt (String)
    Export: value (int)
    Purpose: To provide a method with functionality for user input with the STRING data type.
    *********************/
    public static String userInput(String prompt)
    {
        //CITATION: Method used by Austin Bevacqua (20162896) in PDI Assessment submission, and DSA Worksheets submissions 2-8.
        Scanner sc = new Scanner(System.in);
        String value = null;
        String out;
        
        out = prompt;
        do
        {
            println(out);
            value = sc.nextLine();
            out = ("Enter a valid string\n" + prompt); 
    
        }while((value == null) || (value.equals("")));

        return value;
    }

    /*************************
    Author: Austin Bevacqua
    Date: 17/05/2020
     Last Modified: 31/10/2020
    Import: error (String)
    Export: none
    Purpose: Displays an error to the screen
    *********************/
    public static void displayError(String error)
    {
        //CITATION: Method used by Austin Bevacqua (20162896) in PDI Assessment submission, and DSA Worksheets submissions 2-8.
        System.out.println("ERROR: " + error);
    }

    /*************************
    Author: Austin Bevacqua
    Date: 25/05/2020
     Last Modified: 31/10/2020
    Import: text (String)
    Export: none
    Purpose: Prints text out to the screen, creates a new line
    *********************/
    public static void println(String text)
    {
        System.out.println(text);
    }
}
