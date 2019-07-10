package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextParser <T extends AbstractAppointmentBook> implements AppointmentBookParser {

    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        FileReader fileIn;
        try {
            fileIn = new FileReader("test.txt");
        }
        catch (Exception err)
        {
            System.out.println("Could not find file to read in");
            return null;
        }

        BufferedReader in = new BufferedReader(fileIn);
        try {
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
