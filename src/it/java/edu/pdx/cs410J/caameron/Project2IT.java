package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */

    @Test
    public void happyPath() {
        MainMethodResult result = invokeMain("Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getExitCode(), equalTo(0));

    }

    @Test
    public void happyPathPrint() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Trip to the dentist from 02/28/2019 12:00 until 02/29/2000 15:00"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void READEMEinDESCRIPTION() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "-README", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("-README from 02/28/2019 12:00 until 02/29/2000 15:00"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void readMe() {
        MainMethodResult result = invokeMain("-print", "-README", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Caameron Nakasone\nAdvanced Programming in Java\n"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void readMe2() {
        MainMethodResult result = invokeMain("-print", "-textFile", "test","-README", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Caameron Nakasone\nAdvanced Programming in Java\n"));
        assertThat(result.getExitCode(), equalTo(0));
    }

   @Test
    public void readMestillPrintsEvenifAftertextFileOption() {
        MainMethodResult result = invokeMain("-print", "-textFile", "-README", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Trip to the dentist"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void readMeNoErrors() {
        MainMethodResult result = invokeMain("-pri2222nt", "-README", "Brian Griffin", "DSADSA", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Caameron Nakasone\nAdvanced Programming in Java\n"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void extraArgumentError() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00", "20");
        assertThat(result.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void extraArgumentErrorOptionThatIsREADME() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin","-README", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void notEnoughArgument() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void optionNotRecognized() {
        MainMethodResult result = invokeMain("-printd", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00", "20");
        assertThat(result.getTextWrittenToStandardError(), containsString("Option not recognized. Available options are : -README  -print textFile <file>"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void optionNotRecognized2() {
        MainMethodResult result = invokeMain("-print", "-READme", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00", "20");
        assertThat(result.getTextWrittenToStandardError(), containsString("Option not recognized. Available options are : -README  -print textFile <file>"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void optionNotRecognized3() {
        MainMethodResult result = invokeMain("-print", "-DD", "Brian Griffin", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00", "20");
        assertThat(result.getTextWrittenToStandardError(), containsString("Option not recognized. Available options are : -README  -print textFile <file>"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void emptyArgument() {
        MainMethodResult result = invokeMain("-print", "", "Trip to the dentist", "02/28/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Found empty argument, arguments cannot be empty or null."));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateGreaterMonth() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "22/10/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date entered. Month cannot be greater than 12."));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateGreaterDay() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/32/2019", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Day entered. Day cannot be greater than 31."));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateFormat() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "July 14th", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Date and Time has to be in integers not strings. Ex) 12/12/1212\n"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateFormat2() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/31/2019", "12:00", "0229/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Date entered. Month cannot be greater than 12."));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateFormat3() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/31/2019", "12:00", "02/29/2000", "1500");
        assertThat(result.getTextWrittenToStandardError(), containsString("Time incorrect format. Format is hh:mm"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void invalidDateFormat4() {
        MainMethodResult result = invokeMain("-print", "Brian Griffin", "Trip to the dentist", "02/21/20199", "12:00", "02/29/2000", "15:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm"));
        assertThat(result.getExitCode(), equalTo(1));
    }

}
