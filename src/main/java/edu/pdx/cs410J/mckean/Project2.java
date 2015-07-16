package edu.pdx.cs410J.mckean;


import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;


import java.util.*;
import java.io.IOException;

/**
 * The main class for the CS410J Phonebill project 2.
 * Created by TylerMcKean on 7/15/15.
 */
public class Project2 {

    /**
     * The main method of the program. Reads in arguments from the command line and performs functionality based
     * on what options it has been given. Can print the new call, read in from a phone bill file, write to a phone bill
     * file, or a combination of all three. In addition it can print the README statement depending on the arguments.
     *
     * @param args Represents the data read in from the command line in an array of strings.
     */
    public static void main(String[] args) {
        /**
         * For loop that checks the first few arguments from the command line for the README option
         * If the option is found, the README is printed.
         */
        for (int i = 0; i < 4; ++i) {
            if (args[i].equals("-README")) {
                readMe();
            }
        }
        /**
         * arguments is an array of strings that holds the data read in from the command line.
         */
        String [] arguments = new String[8];
        System.arraycopy(commandLineParse(args), 0, arguments, 0, 8);
        /**
         * The two lines below create instances of both the TextParser and the TextDumper.
         * Uses the file name read in from the command line for the constructors.
         */
        TextParser fileTextParser = new TextParser(arguments[1]);
        TextDumper fileTextDumper = new TextDumper(arguments[1]);
        /**
         * Creates an instance of a phone call to be added from the information given
         * in the command line.
         */
        PhoneCall newPhoneCall = new PhoneCall(arguments[4], arguments[5], arguments[6], arguments[7]);
        /**
         * If the -print option is found in the command line options, this prints the phone call to be added
         * back to the user.
         */
        if(arguments[2].equals("y")) {
            System.out.println("The phone call from the command line to be added: " + newPhoneCall.toString());
        }
        /**
         * If statement that runs when the user uses the -textFile fileName option in the command line.
         */
        if(arguments[0].equals("y")) {
            try {
                /**
                 * If statement that runs as long as the file named in the command line arguments already exists.
                 * Otherwise, the new phone bill is added to a new file.
                 */
                if (fileTextParser.parse() != null) {
                    AbstractPhoneBill inputPhoneBill = fileTextParser.parse();
                    /**
                     * If statement that runs if the customer name in the text file is the same as the one from the
                     * command line. Otherwise, it returns an error to the user that the names are not the same.
                     */
                    if (inputPhoneBill.getCustomer().equals(arguments[3])) {
                        inputPhoneBill.addPhoneCall(newPhoneCall);
                        fileTextDumper.dump(inputPhoneBill);
                    }
                    else {
                        System.out.println("The phone bill in the text file is not for the same customer as entered in the command line");
                        System.exit(1);
                    }
                }
                else {
                    PhoneBill inputPhoneBill = new PhoneBill(arguments[3]);
                    inputPhoneBill.addPhoneCall(newPhoneCall);
                    fileTextDumper.dump(inputPhoneBill);
                }
            }
            catch (ParserException e) {
                System.err.println("Parser Exception");
                System.exit(1);
            }
            catch (IOException e) {
                System.err.println("IO Exception");
                System.exit(1);
            }
        }
        else {
            PhoneBill newPhoneBill = new PhoneBill(arguments[3]);
            newPhoneBill.addPhoneCall(newPhoneCall);
        }
        System.exit(0);
    }

    /**
     * Function that parses the information from the command line and checks it for errors.
     * @param args Takes in the command line information as an array of strings to be checked.
     * @return Returns an array of strings that is formatted for ease of use in the rest of the program.
     *
     */
    public static String[] commandLineParse (String[]args){
        String toFile = new String();
        String fileName = new String();
        String print = new String();

        if(args.length < 7) {
            System.err.println("Missing command line arguments.");
            System.exit(1);
        }
        if(args.length > 10) {
            System.err.println("Extra command line arguments.");
            System.exit(1);
        }
        /**
         * For loop that checks for the presence of the textFile option.
         */
        for(int i = 0; i < 3; ++i) {
            if(args[i].equals("-textFile") && !args[i+1].isEmpty()) {
                toFile = "y";
                fileName = args[i+1];
                break;
            }
            else {
                toFile = "n";
                fileName = "n";
            }
        }
        /**
         * For loop that checks for the presence of the print option.
         */
        for(int i = 0; i <3; ++i) {
            if(args[i].equals("-print")) {
                print = "y";
                break;
            }
            else {
                print = "n";
            }
        }
        /**
         * If statement that runs if both the textFile and print options exist
         */
        if(toFile.equals("y") && !fileName.isEmpty() && print.equals("y")) {
           if(args.length < 10) {
               System.err.println("Missing command line arguments.");
               System.exit(1);
           }
           else {
               String [] arguments = new String[8];
               arguments[0] = toFile;
               arguments[1] = fileName;
               arguments[2] = print;
               arguments[3] = args[3];
               arguments[4] = args[4];
               arguments[5] = args[5];
               arguments[6] = args[6] + " " + args[7];
               arguments[7] = args[8] + " " + args[9];

               if(arguments[3].matches("^[\\s]+") || arguments[0].isEmpty()) {
                   System.err.println("Customer name was entered incorrectly");
                   System.exit(1);
               }
               if(!arguments[4].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                   System.err.println("Caller phone number in the incorrect form");
                   System.exit(1);
               }
               if(!arguments[5].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                   System.err.println("Callee phone number is in the incorrect form");
                   System.exit(1);
               }
               if(!arguments[6].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                   System.err.println("Start date and time is in the incorrect form");
                   System.exit(1);
               }
               if(!arguments[7].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                   System.err.println("End date or time is in the incorrect form");
                   System.exit(1);
               }
               if(arguments[6].compareTo(arguments[7]) > 0) {
                   System.err.println("The start date entered is after the end date");
                   System.exit(1);
               }
               return arguments;
           }
        }
        /**
         * If statement that runs if the print option is present but not the fileName option
         */
        else if(toFile.equals("n") && fileName.equals("n") && print.equals("y")) {
            if (args.length < 8) {
                System.err.println("Missing command line arguments.");
                System.exit(1);
            }
            if (args.length > 8) {
                System.err.println("Extra command line arguments.");
                System.exit(1);
            }
            else {
                String[] arguments = new String[8];
                arguments[0] = toFile;
                arguments[1] = fileName;
                arguments[2] = print;
                arguments[3] = args[1];
                arguments[4] = args[2];
                arguments[5] = args[3];
                arguments[6] = args[4] + " " + args[5];
                arguments[7] = args[6] + " " + args[7];

                if (arguments[3].matches("^[\\s]+") || arguments[0].isEmpty()) {
                    System.err.println("Customer name was entered incorrectly");
                    System.exit(1);
                }
                if (!arguments[4].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Caller phone number in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[5].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Callee phone number is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[6].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("Start date and time is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[7].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("End date or time is in the incorrect form");
                    System.exit(1);
                }
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
                    System.exit(1);
                }
                return arguments;
            }
        }
        /**
         * If statement that runs if the fileName option is present but not the print option.
         */
        else if(toFile.equals("y") && !fileName.isEmpty() && print.equals("n")) {
            if (args.length < 9) {
                System.err.println("Missing command line arguments.");
                System.exit(1);
            }
            if (args.length > 9) {
                System.err.println("Extra command line arguments.");
                System.exit(1);
            }
            else {
                String[] arguments = new String[8];
                arguments[0] = toFile;
                arguments[1] = fileName;
                arguments[2] = print;
                arguments[3] = args[2];
                arguments[4] = args[3];
                arguments[5] = args[4];
                arguments[6] = args[5] + " " + args[6];
                arguments[7] = args[7] + " " + args[8];

                if (arguments[3].matches("^[\\s]+") || arguments[0].isEmpty()) {
                    System.err.println("Customer name was entered incorrectly");
                    System.exit(1);
                }
                if (!arguments[4].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Caller phone number in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[5].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Callee phone number is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[6].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("Start date and time is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[7].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("End date or time is in the incorrect form");
                    System.exit(1);
                }
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
                    System.exit(1);
                }
                return arguments;
            }
        }
        /**
         * This block of code runs if no options are present in the command line.
         */
        else {
            System.out.println("no print no file");
            if (args.length < 7) {
                System.err.println("Missing command line arguments.");
                System.exit(1);
            }
            if (args.length > 7) {
                System.err.println("Extra command line arguments.");
                System.exit(1);
            }
            else {
                String[] arguments = new String[8];
                arguments[0] = toFile;
                arguments[1] = fileName;
                arguments[2] = print;
                arguments[3] = args[0];
                arguments[4] = args[1];
                arguments[5] = args[2];
                arguments[6] = args[3] + " " + args[4];
                arguments[7] = args[5] + " " + args[6];

                if (arguments[3].matches("^[\\s]+") || arguments[0].isEmpty()) {
                    System.err.println("Customer name was entered incorrectly");
                    System.exit(1);
                }
                if (!arguments[4].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Caller phone number in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[5].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
                    System.err.println("Callee phone number is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[6].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("Start date and time is in the incorrect form");
                    System.exit(1);
                }
                if (!arguments[7].matches("\\d{1,2}/\\d{1,2}/\\d{2,4} \\d{1,2}:\\d{2}")) {
                    System.err.println("End date or time is in the incorrect form");
                    System.exit(1);
                }
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
                    System.exit(1);
                }
                return arguments;
            }
        }
        return null;
    }

    /**
     * Function that is called if the README option is present in the command line.
     */
    public static void readMe() {
        System.out.println("Project 2 README by Tyler McKean for CS410J Summer 2015.");
        System.out.println("This program implements two interfaces called TextDumper and TextParser.");
        System.out.println("It will read in a file name and destination from the command line.");
        System.out.println("From the information entered on the command line it will then either read or write");
        System.out.println("a new call to a Phone Bill and either write it to a new text file or read in from one.");
        System.out.println("It can also add new calls to Phone Bills for customers of the same name");
        System.exit(0);
    }
}