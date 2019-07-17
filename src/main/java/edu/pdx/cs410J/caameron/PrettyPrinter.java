package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrettyPrinter implements AppointmentBookDumper {
    private AppointmentBook<Appointment> apptBook;
    private String filename;

    public PrettyPrinter(AppointmentBook apptBook, String filename) {
        this.apptBook = apptBook;
        this.filename = filename;
    }

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
