package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;

public class TextParser <T extends AbstractAppointmentBook> implements AppointmentBookParser {
    private String fileName;

    public TextParser(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        FileReader fileIn;
        AppointmentBook<Appointment> returnBook;
        try {
            fileIn = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fileIn);

            //Start reading line by line and creating the appointment book
            System.out.println(in.readLine() + in.readLine());
            in.close();
        }
        catch (Exception err)
        {
            throw new ParserException("Could not find file to read in");
        }

        return null;
    }
}
