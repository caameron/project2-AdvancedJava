package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Text Dumper Class that will be used to dump the content of an appointment book into a text file.
 * Uses BufferedWriter and FileWriter to create a file and write content too.
 *
 * @param <T> AppointmentBook Class
 */
public class TextDumper<T extends AbstractAppointmentBook> implements AppointmentBookDumper {
    String filename;

    /**
     * Creates a new TextDumper instance. Sets the file name data member of the class.
     * The fileName member will be used as the name of the new text file.
     * @param filename
     */
    public TextDumper(String filename)
    {
        this.filename = filename;
    }


    /**
     * Method to create and write out content to a text file.
     * Format of content written to file is as follows:
     *              First line: Name of owner
     *              Each line after: arguments to create appointment seperated by '@'
     * @param abstractAppointmentBook The appointment book's content that will be written out to a text file
     * @throws IOException Will throw an exception if unable to create or write out to a text file.
     */
    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        AbstractAppointmentBook<Appointment> apptBook = abstractAppointmentBook;
        Collection<Appointment> appointments = apptBook.getAppointments();

        //Attach owner name to string
        //seperating the owner and all appointments by a new line delimeter
        //using '@' char to seperate arguments
        try {
            stringBuilder.append(abstractAppointmentBook.getOwnerName()).append('\n');

            //Attach appointments
            for (Appointment appointment : appointments) {
                StringBuilder appt = new StringBuilder();
                appt.append(appointment.getDescription() + '@' + appointment.getBeginTimeString() + '@' + appointment.getEndTimeString() + '\n');
                stringBuilder.append(appt);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(stringBuilder.toString());
            writer.close();
        }
        catch (Exception err)
        {
            throw new IOException("Error in trying to dump Appointment Book to text File");
        }
    }
}
