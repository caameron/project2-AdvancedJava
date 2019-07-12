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
        assertThat(result.getTextWrittenToStandardOut(), containsString("Trip to the dentist from 02/28/2019 12:00 until 02/29/2000 15:00"));
    }
}
