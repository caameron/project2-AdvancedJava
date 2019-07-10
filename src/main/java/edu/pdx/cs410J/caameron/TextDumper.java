package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextDumper<T extends AbstractAppointmentBook> implements AppointmentBookDumper {

    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        String testString = "test";
        System.out.println("DDDD");
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));
        writer.write(testString);
        writer.close();
    }
}
