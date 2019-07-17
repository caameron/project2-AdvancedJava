package edu.pdx.cs410J.caameron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project3 {
    /**
     * The main method will handle the command line parsing for project 3 as well as some error handling.
     * It will store the command line arguments in two ArrayLists one for arguments and one for options.
     * It will check for the correct formatting of some of the arguments and if there are the correct
     * number of arguments. If there are options sent in it will invoke their corresponding actions.
     * It will also create an instance of the Appointment Book class and Appointment class
     * based on the command line arguments and then add that Appointment to the Appointment Book.
     * If the textFile flag is detected the the main method will also create instances of the textDumper
     * and textParser classes to read and write to a file.
     * @param args Command line arguments sent to main program.
     */

    public static void main(String[] args) {

    /*
      Check for the correct amount of arguments passed in from the command line,
      Check if there is something missing and if they want to print it out or not.
      Leave the error handing for formats of the input to the classes themselves

     */
        //Create variable to hold file name if given
        String fileName = "";

        //Parse the arguments sent in into arguments for the program and options (options start with '-')
        List arguments = new ArrayList();
        List options = new ArrayList();
        boolean stopOptionflag = true;
        boolean getNext = false;
        boolean printAndWrite = false;
        for (String arg : args) {
            if(arg.isEmpty() == true)
            {
                System.err.println("Found empty argument, arguments cannot be empty or null.");
                System.exit(1);
            }

            if(getNext == true)
            {
                getNext = false;
                fileName = arg;
                continue;
            }

            if(arg.charAt(0) == '-' && stopOptionflag == true)
            {
                options.add(arg);
                if(arg.equals("-textFile"))
                {
                    printAndWrite = true;
                    getNext = true;
                }
            }
            else if (getNext == true)
            {
                getNext = false;
                fileName = arg;
            }
            else {
                arguments.add(arg);
                stopOptionflag = false;
            }
        }

        //Check if the README flag is there if it is print README and end program
        for (Object option : options)
        {
            if(option.toString().equals("-README"))
            {
                System.out.println("Caameron Nakasone\nAdvanced Programming in Java\nProject 2: Storing An Appointment Book in a Text File\n" +
                        "Project 2 will take in arguments from the command line to create an Appointment Book and an Appointment\n" +
                        "that will be automatically added to the created Appointment Book. The arguments include the name of the owner for the\n" +
                        "appointment book and details about the appointment which will be added (description, start date/time and end date/time\n" +
                        "Optionally the program can also read in an appointment book from a text file and then add an appointment to that book\n" +
                        "with the arguments given through the command line, the resulting appointment book will then be written back to that text\n" +
                        "file. This can be invoked by using the -textFile <fileName> option on the command line\n\n" +
                        "There are also README and print options which you may choose to invoke. The README brings up this message and the print\n" +
                        "option will print the appointment that has just been added to the standard output. No fields can be empty from the command\n" +
                        "line and the dates and times must be formatted correctly for the program to run. If you have entered in something\n" +
                        "incorrectly an error message will be printed out and the program will exit.\n");
                System.exit(0);
            }
        }

        for (Object option : options) {
            if (!(option.toString().equals("-README")) && !(option.toString().equals("-print")) && !(option.toString().equals("-textFile"))) {
                System.err.println("Option not recognized. Available options are : -README  -print textFile <file>");
                System.exit(1);
            }
        }


        //Check that the correct amount of arguments have been passed in  keeping in mind that they can add the options tag if they
        //Choose to do so.
        if(arguments.size() != 6)
        {
            System.err.println("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime");
            System.exit(1);
        }

        String owner = (arguments.get(0)).toString();
        String description = (arguments.get(1)).toString();
        String beginDate = (arguments.get(2)).toString();
        String beginTime = (arguments.get(3)).toString();
        String endDate = (arguments.get(4)).toString();
        String endTime = (arguments.get(5)).toString();

        try {


            String[] splitB = beginDate.split("/");
            String[] splitE = endDate.split("/");
            int monthB = Integer.parseInt(splitB[0]);
            int monthE = Integer.parseInt(splitE[0]);
            if (monthB > 12 || monthE > 12) {
                System.err.println("Invalid Date entered. Month cannot be greater than 12.");
                System.exit(1);
            }

            try {
                int dayB = Integer.parseInt(splitB[1]);
                int dayE = Integer.parseInt(splitE[1]);
                if (dayB > 31 || dayE > 31) {
                    System.err.println("Invalid Day entered. Day cannot be greater than 31.");
                    System.exit(1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {System.err.println("Date incorrect format. Format is mm/dd/yyyy");}

            String[] splitTimeB = beginTime.split(":");
            String[] splitTimeE = endTime.split(":");
            try {
                int minutesB = Integer.parseInt(splitTimeB[1]);
                int minutesE = Integer.parseInt(splitTimeE[1]);
                if (minutesB > 60 || minutesE > 60) {
                    System.err.println("Invalid Time entered. Minutes cannot be more than 60");
                    System.exit(1);
                }
            } catch (ArrayIndexOutOfBoundsException er) {System.err.println("Time incorrect format. Format is hh:mm");}
            int hoursB = Integer.parseInt(splitTimeB[0]);
            int hoursE = Integer.parseInt(splitTimeE[0]);
            if (hoursB > 24 || hoursE > 24) {
                System.err.println("Invalid Time entered. Hours cannot be more than 24");
                System.exit(1);
            }
        } catch (NumberFormatException e)
        {
            System.err.println("Date and Time has to be in integers not strings. Ex) 12/12/1212\n" +
                    "July 14th 1994 is not a correct format");
            System.exit(1);
        }

        //There are the correct amount of arguments now create appointmentBook and appointment. Then add appointment to appointmentBook
        AppointmentBook apptBook;
        Appointment appt = null;
        try {
            appt = new Appointment(beginDate, beginTime, endDate, endTime, description);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        //Check if the option to read and write to a file are there
        //Write out appointmentbook to text file

        if(printAndWrite)
        {
            TextParser textParser = new TextParser(fileName, owner);
            try {
                apptBook = (AppointmentBook) textParser.parse();
            }
            catch (Exception err) {
//            System.out.println("No text file with that name exists, creating new file " + fileName);
                if(!err.getMessage().equals("No text file with that name exists, creating new file " + fileName))
                {
                    System.out.println(err.getMessage());
                    System.exit(1);
                }
                System.out.println(err.getMessage());
                apptBook = new AppointmentBook(owner);
            }
            apptBook.addAppointment(appt);

            TextDumper textDumper = new TextDumper(fileName);
            try {
                textDumper.dump(apptBook);
            } catch (IOException err) {
                System.out.println(err);
            }
        }
        else
        {
            //If the printAndWrite flag is not set just create the apptBook and add an appt.
            apptBook = new AppointmentBook(owner);
            apptBook.addAppointment(appt);
        }

        //Check for print flag and print out appointment if it is there
        for (Object option : options)
        {
            if(option.toString().equals("-print"))
            {
                ArrayList appointments = apptBook.getAppointments();
                int size = appointments.size();
                Appointment appointment = (Appointment) appointments.get(size - 1);
                System.out.println(appointment.toString());
                System.exit(0);
            }
        }

        System.exit(0);
    }
}
