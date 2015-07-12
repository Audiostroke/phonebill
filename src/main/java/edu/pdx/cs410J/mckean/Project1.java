package edu.pdx.cs410J.mckean;

import edu.pdx.cs410J.AbstractPhoneBill;


/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {

    Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    /**
     * The next two statements ensure that the args passed in with the command line are complete.
     */
    if(args.length < 7) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    }

    if(args.length > 7) {
      System.err.println("Extra command line arguments");
      System.exit(1);
    }

    /**
     * arguments is an array of strings that is created to help parse the command line arguments.
     * The next several if statements are error checks to ensure that arguments have beeen
     * entered correctly.
     */

    String[] arguments = new String[5];

    arguments[0] = args[0];
    arguments[1] = args[1];
    arguments[2] = args[2];
    arguments[3] = args[3]+" "+args[4];
    arguments[4] = args[5]+" "+args[6];

    if(arguments[0].matches("^[\\s]+") || arguments[0].isEmpty()) {
      System.err.println("Customer name was entered incorrectly");
      System.exit(1);
    }

    if(!arguments[1].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
      System.err.println("Caller phone number in the incorrect form");
      System.exit(1);
    }

    if(!arguments[2].matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
      System.err.println("Callee phone number is in the incorrect form");
      System.exit(1);
    }

    if(arguments[3].compareTo(arguments[4]) > 0) {
      System.err.println("The start date entered is after the end date");
      System.exit(1);
    }

    System.out.println("The arguments entered are as follows:");
    for (String arg : arguments) {
      System.out.println(arg);
    }

    /**
     * The next two lines create a new instance of the PhoneBill and PhoneCall objects
     * using their constructors.
     */
    PhoneBill newPhoneBill = new PhoneBill(arguments[0]);
    PhoneCall newPhoneCall = new PhoneCall(arguments[1], arguments[2], arguments[3], arguments[4]);

    /**
     * Adds the PhoneCall that was created to the PhoneBill of the customer.
     */
    newPhoneBill.addPhoneCall(newPhoneCall);

    /**
     * Prints information regarding the customers phone bill as well as the phone call that was added.
     */
    System.out.println(newPhoneBill.toString());
    System.out.println(newPhoneCall.toString());

    System.exit(0);
  }
}