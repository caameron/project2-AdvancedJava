package edu.pdx.cs410J.caameron;

import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  @Test
  public void getBeginTimeStringNeedsToBeImplemented() {
    Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription");
    appointment.getBeginTimeString();
  }

  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription");
    assertThat(appointment.getDescription(), containsString("TestDescription"));
  }

  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription");
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void getBeginTimeStringReturnsCorrectDate() {
    Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription");
    assertThat(appointment.getBeginTimeString(), containsString("12/12/1212 12:12"));
  }

  @Test
  public void getEndTimeStringReturnsCorrectDate() {
    Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription");
    assertThat(appointment.getEndTimeString(), containsString("12/12/1212 12:12"));
  }

  @Test
  public void checkDifferentInputsOfDatesAndTimes() {
    List correctTestDates = new ArrayList();
    correctTestDates.add("7/15/2019 14:39");
    correctTestDates.add("06/2/2019 1:03");
    correctTestDates.add("7/5/2019 14:39");
    correctTestDates.add("06/02/2019 1:03");

    List incorrectTestDates = new ArrayList();
    incorrectTestDates.add("7/15/19 14:39");
    incorrectTestDates.add("7/15/2019 14:3");
    incorrectTestDates.add("7/115/2019 4:39");
    incorrectTestDates.add("227/15/2019 14:39");

    String regEx = "^[0-9]?[0-9]/[0-9]?[0-9]/([0-9]{4}) [0-9]?[0-9]:[0-9][0-9]$";
    Pattern pattern = Pattern.compile(regEx);
    for(Object date : correctTestDates)
    {
      Matcher matcher = pattern.matcher(date.toString());
      assertThat(matcher.matches(), equalTo(true));
    }
    for(Object date : incorrectTestDates)
    {
      Matcher matcher = pattern.matcher(date.toString());
      assertThat(matcher.matches(), equalTo(false));
    }

  }

  @Ignore
  @Test
  public void creatingAppointmentWithInappropriateStartDateReturnsError() {

  }

  @Ignore
  @Test
  public void creatingAppointmentWithInappropriateEndDateReturnsError() {

  }

  @Ignore
  @Test
  public void ifDescriptionIsNullThrowError() {

  }
}
