package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

public class TextParser <T extends AbstractAppointmentBook> implements AppointmentBookParser {

    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        return null;
    }
}
