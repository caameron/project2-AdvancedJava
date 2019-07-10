package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class TextDumper<T extends AbstractAppointmentBook> implements AppointmentBookDumper {

    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        AbstractAppointmentBook<Appointment> apptBook = abstractAppointmentBook;
        Collection<Appointment> appointments = apptBook.getAppointments();

        //Attach owner name to string
        //seperating the owner and all appointments by a new line delimeter
        //using '@' char to seperate arguments
        try {
            stringBuilder.append(abstractAppointmentBook.getOwnerName() + '\n');

            //Attach appointments
            for (Appointment appointment : appointments) {
                StringBuilder appt = new StringBuilder();
                appt.append(appointment.getDescription() + '@' + appointment.getBeginTimeString() + '@' + appointment.getEndTimeString() + '\n');
                stringBuilder.append(appt);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
            writer.write(stringBuilder.toString());
            writer.close();
        }
        catch (Exception err)
        {
            throw new IOException("Error in trying to dump Appointment Book to text File");
        }
    }
}
