package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project1.class, args );
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));
  }

  @Test
  public void programShouldRunWithOnly4Arguments() {
    /*
    Testing running the program with 1, 3, 4, and 5 arguments
     */
    MainMethodResult result = invokeMain("Owner", "Description", "12/12/1212", "12:12", "12/12/1212", "12:12");
    assertThat(result.getExitCode(), equalTo(0));

    MainMethodResult result2 = invokeMain("Owner", "Description", "end");
    assertThat(result2.getExitCode(), equalTo(1));
    assertThat(result2.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));

    MainMethodResult result3 = invokeMain("Owner");
    assertThat(result3.getExitCode(), equalTo(1));
    assertThat(result3.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));

    MainMethodResult result4 = invokeMain("Owner", "Description", "D", "begin", "D", "end", "extra");
    assertThat(result4.getExitCode(), equalTo(1));
    assertThat(result4.getTextWrittenToStandardError(), containsString("Did not give the correct arguments to command line. There must be a 6 arguments - owner, description, beginDate, beginTime, endDate, endTime"));
  }


  @Test
  public void readmeIsPrinted() {
    MainMethodResult result = invokeMain("-README", "Owner", "Description", "12/12/1212 12:12", "12/12/1212 12:12");
    assertThat(result.getTextWrittenToStandardOut(), containsString("Caameron"));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Ignore
  @Test
  public void programShouldRunWithFlags() {

  }

  @Ignore
  @Test
  public void programShouldntRunIfThereAreFourArgumentsButOneIsAFlag() {

  }
}