package edu.pdx.cs410J.mckean;


/**
 * Created by TylerMcKean on 7/15/15.
 */
public class Project2 {

    /**
     * The main method of the program. Reads in arguments from the command line and performs functionality based
     * on what options it has been given. Can print the new call, read in from a phone bill file, write to a phone bill
     * file, or a combination of all three. In addition it can print the README statement depending on the arguments.
     *
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 4; ++i) {
            if (args[i].equals("-README")) {
                readMe();
            }
        }

        String [] arguments = new String[8];
        System.arraycopy(commandLineParse(args), 0, arguments, 0, 8);

        System.out.println("The arguments are as follows:");
        for(String arg : arguments) {
            System.out.println(arg);
        }

        PhoneBill newPhoneBill = new PhoneBill(arguments[3]);
        PhoneCall newPhoneCall = new PhoneCall(arguments[4], arguments[5], arguments[6], arguments[7]);

        newPhoneBill.addPhoneCall(newPhoneCall);

        if(arguments[2].equals("y")){
            System.out.println(newPhoneBill.toString());
            System.out.println(newPhoneCall.toString());
            System.exit(0);
        }

        System.exit(0);
    }

    /**
     * @param args
     * @return
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
        for(int i = 0; i <3; ++i) {
            if(args[i].equals("-print")) {
                print = "y";
                break;
            }
            else {
                print = "n";
            }
        }
        if(toFile.equals("y") && !fileName.isEmpty() && print.equals("y")) {
            System.out.println("Printing and read/write to file.");
           if(args.length < 10) {
               System.err.println("Missing line arguments.");
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
               if(arguments[6].compareTo(arguments[7]) > 0) {
                   System.err.println("The start date entered is after the end date");
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
               return arguments;
           }
        }
        else if(toFile.equals("n") && fileName.equals("n") && print.equals("y")) {
            System.out.println("Printing and no read/write to file.");
            if (args.length < 8) {
                System.err.println("Missing line arguments.");
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
                arguments[6] = args[4] + args[5];
                arguments[7] = args[6] + args[7];

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
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
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
                return arguments;
            }
        }
        else if(toFile.equals("y") && fileName.equals("y") && print.equals("n")) {
            System.out.println("Not Printing and read/write to file.");
            if (args.length < 9) {
                System.err.println("Missing line arguments.");
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
                arguments[6] = args[5] + args[6];
                arguments[7] = args[7] + args[8];

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
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
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
                return arguments;
            }
        }
        else {
            System.out.println("Not Printing and No read/write to file.");
            if (args.length < 7) {
                System.err.println("Missing line arguments.");
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
                arguments[6] = args[3] + args[4];
                arguments[7] = args[5] + args[6];

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
                if (arguments[6].compareTo(arguments[7]) > 0) {
                    System.err.println("The start date entered is after the end date");
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
                return arguments;
            }
        }
        return null;
    }

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