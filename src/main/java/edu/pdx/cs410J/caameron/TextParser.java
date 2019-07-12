package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicBoolean;
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

            //Flags for malformatted text file
            boolean malformatted = false;
            Stream<String> lines = in.lines();
            String [] remainingLines = lines.toArray(size -> new String[size]);
//            lines.forEach(x-> {
            for(String line: remainingLines)
            {
                //Parse each line to get the time and description arguments
                String description;
                String beginTime;
                String beginDate;
                String endTime;
                String endDate;

                String [] split = line.split("@");
                if(split.length != 3)
                {
                    malformatted = true;
                    break;
                }

                description = split[0];

                String [] begSplit = split[1].split(" ");
                String [] endSplit = split[2].split(" ");
                if(begSplit.length!= 2 && endSplit.length != 2)
                {
                    malformatted = true;
                    break;
                }

                beginDate = begSplit[0];
                beginTime = begSplit[1];
                endDate = endSplit[0];
                endTime = endSplit[1];
                Appointment appt = new Appointment(beginDate, beginTime, endDate, endTime, description);
                returnBook.addAppointment(appt);
            }

            if(malformatted == true)
            {
                throw new ParserException("Malformatted text file. Program now exiting.");
            }

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

