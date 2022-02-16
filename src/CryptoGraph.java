/********************
 Class Crypto Grapah
 Purpose: The main entry point of the cryptoGraph program.
    Validates the arguments ran with the program, and notifies the user if they are incorrect
 Last Modified: 31/10/20
 Author: Austin Bevacqua
 ***************/
public class CryptoGraph {
    public static void main(String[] args) {
        //If there is 1 parameter, the program must be running in interactive mode.
        if (args.length == 1) {
            //Therefore, we check if the parameter "-i" is correct, and if so, we run the main interactive window
            if (args[0].equalsIgnoreCase("-i")) {
                Menu.interactiveMode();
            }
            else{
                Menu.printUsageError();
            }
        }
        //The only other option for running the program is in report mode, which requires 2-4 parameters
        else if ((args.length <= 4) && (args.length > 0)) {
            //We verify that the parameter "-r" is correct, and if so, we run the report mode
            if (args[0].equalsIgnoreCase("-r")) {
                Menu.reportMode(args);
            } else {
                Menu.printUsageError();
            }
        }
        //If the number of parameters is incorrect, the user is printed the correct usage information to the screen
        else {
            Menu.printUsageError();
        }
    }
}
