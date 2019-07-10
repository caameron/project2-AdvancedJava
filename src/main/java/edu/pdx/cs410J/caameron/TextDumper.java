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
        String testString = "testddd";
        AbstractAppointmentBook<Appointment> apptBook = abstractAppointmentBook;
        Collection<Appointment> appointments = apptBook.getAppointments();

        //Attach owner name to string
        stringBuilder.append(abstractAppointmentBook.getOwnerName() + '@');

        //Attach appointments
        for (Appointment appointment : appointments)
        {
            System.out.println("D");
            StringBuilder appt = new StringBuilder();
            appt.append(appointment.getDescription() + '@' + appointment.getBeginTimeString() + '@' + appointment.getEndTimeString() + '\n');
            stringBuilder.append(appt);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
