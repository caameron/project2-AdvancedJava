package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * PrettyPrinter class that implements AppointmentBookDumper. Its method will take the content of an appointment book
 * and create a nicely formatted textual representation of it.
 * @param <T> AppointmentBook Class
 */
public class PrettyPrinter<T extends AbstractAppointmentBook> implements AppointmentBookDumper {
    private AppointmentBook<Appointment> apptBook;
    private String filename;

    /**
     * Creates a new PrettyPrinter instance. Takes in the file name of the file which will contain the content
     * of the appointment book and the instance of the appointment book that will be converted to a nicely formatted
     * textual representation
     * @param apptBook AppointmentBook instance to be printed
     * @param filename Name of file that will contain printed appointment book
     */
    public PrettyPrinter(AppointmentBook apptBook, String filename) {
        this.apptBook = apptBook;
        this.filename = filename;
    }

    /**
     * Method to create a new file and dump the content of an appointment book to it in a nice textual representation
     * @param abstractAppointmentBook The appointment book's content that will be written out to a text file
     * @throws IOException Will throw an exception if unable to create or write out to a text file
     */
    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        try {

            StringBuilder stringBuilder = new StringBuilder();

            //Pretty Print all the appointments and the owner name
            for (Appointment appointment : apptBook.getAppointments()) {
                stringBuilder.append(appointment.toString());
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(stringBuilder.toString());
            writer.close();
        }
        catch (Exception err)
        {
            throw new IOException("Error in trying to pretty print the appointment book");
        }
    }
}
