package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;

public class TextParser <T extends AbstractAppointmentBook> implements AppointmentBookParser {
    private String fileName;
    private String ownerNameGiven;

    public TextParser(String fileName, String ownerName)
    {
        this.fileName = fileName;
        this.ownerNameGiven = ownerName;
    }

    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        FileReader fileIn;
        AppointmentBook<Appointment> returnBook;
        try {
            fileIn = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fileIn);

            //Read in all lines of file and them loop through them
            String owner = in.readLine();

            //Check that the owner name in file is the same as the o ne passed in
            if(owner.equals(ownerNameGiven) == false)
            {
                throw new ParserException("Owner name passed in through command line does not match owner name from text file");
            }

            returnBook = new AppointmentBook<>(owner);

            Stream<String> lines = in.lines();
            lines.forEach(x-> {
                //Parse each line to get the time and description arguments
                String description;
                String beginTime;
                String beginDate;
                String endTime;
                String endDate;

                String [] split = x.split("@");
                description = split[0];
                beginDate = split[1].split(" ")[0];
                beginTime = split[1].split(" ")[1];
                endDate = split[2].split(" ")[0];
                endTime = split[2].split(" ")[1];
                Appointment appt = new Appointment(beginDate, beginTime, endDate, endTime, description);
                returnBook.addAppointment(appt);
            });

            in.close();
        }
        catch (ParserException err)
        {
            throw err;
        }
        catch (Exception err)
        {
            throw new ParserException("No text file with that name exists, creating new file " + fileName);
        }

        return returnBook;
    }
}
