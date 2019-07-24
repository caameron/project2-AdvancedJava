package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;

/**
 * Text Parser Class that is used to parse a text file that contains information on the details of a appointment book.
 * Uses BufferedReader and FileReader classes to open a file and obtain all the lines.
 *
 * @param <T> AppointmentBook Class
 */
public class TextParser <T extends AbstractAppointmentBook> implements AppointmentBookParser {
    private String fileName;
    private String ownerNameGiven;

    /**
     * Creates a new TextParse instance. Sets the owner name and file name data members of the class.
     * The fileName will be used to open the file and ownerName is used to check that the owner matches
     * the file.
     * @param fileName
     * @param ownerName
     */
    public TextParser(String fileName, String ownerName)
    {
        this.fileName = fileName;
        this.ownerNameGiven = ownerName;
    }

    /**
     * Method to parse the content of the text file.
     * The format of the file should be as follows :
     *           First line: Name of the owner
     *           Each line after: an Appointments arguments seperated by '@'
     * @return Returns an instance of an appointmentBook class that contains the owner and appointments from
     * the text file.
     *
     * @throws ParserException Will throw one of three exceptions:
     *                         Owner name in file doesn't match name given from command line
     *                         Text file that has been opened is malformatted
     *                         No text file has been found with name provided and a new one will be created
     */
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
                String beginTimeOfDay;
                String endTime;
                String endDate;
                String endTimeOfDay;

                String [] split = line.split("@");
                if(split.length != 3)
                {
                    malformatted = true;
                    break;
                }

                description = split[0];

                String [] begSplit = split[1].split(" ");
                String [] endSplit = split[2].split(" ");
                if(begSplit.length!= 3 || endSplit.length != 3)
                {
                    malformatted = true;
                    break;
                }

                beginDate = begSplit[0];
                beginTime = begSplit[1];
                beginTimeOfDay = begSplit[2];
                endDate = endSplit[0];
                endTime = endSplit[1];
                endTimeOfDay = endSplit[2];
                Appointment appt = new Appointment(beginDate, beginTime, endDate, endTime, description, beginTimeOfDay, endTimeOfDay);
                returnBook.addAppointment(appt);
                String[] splitTime = beginTime.split(":");
                String[] splitTimeEnd = endTime.split(":");
                int bHrs = Integer.parseInt(splitTime[0]);
                int eHrs = Integer.parseInt(splitTimeEnd[0]);
                if(bHrs > 12 || eHrs > 12 )
                {
                    throw new Exception("Hours cannot be more than 12");
                }
            }

            if(malformatted == true)
            {
                throw new ParserException("Malformatted text file, please check that file is in correct format.");
            }

            in.close();
        }
        catch (ParserException err)
        {
            throw err;
        }
        catch (Exception err)
        {
            if(err.getMessage().equals("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm") || err.getMessage().equals("Description cannot be empty or null"))
            {
                throw new ParserException("Malformatted text file: " + err.getMessage());
            }
            else if(err.getMessage().equals("Start time of appointment cannot be after end time") || err.getMessage().equals("Start time of day is not am or pm. Please Specify 'am' or 'pm'") || err.getMessage().equals("End time of day is not am or pm. Please Specify 'am' or 'pm'") || err.getMessage().equals("Hours cannot be more than 12"))
            {
                throw new ParserException("Malformatted text file: " + err.getMessage());
            }
            throw new ParserException("No text file with that name exists, creating new file " + fileName);
        }

        return returnBook;
    }
}

