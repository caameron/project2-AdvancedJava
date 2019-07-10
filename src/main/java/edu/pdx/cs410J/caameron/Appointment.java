package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Appointment class which will represent a single appointment and some of the details that belong with it
 * This will include a start date and time, a end date and time, and a brief description about the appointment
 * This class extends AbstractAppointment. All data members of the class are represented using Strings. For now
 * the class only sets all the data in the constructor and uses methods to get the values when needed.
 */
public class Appointment extends AbstractAppointment {

  //Data members for Appointment class, will have variables for
  //start, end and description
  private String startDate;
  private String endDate;
  private String endTime;
  private String startTime;
  private String description;

  //Constructor for Appointment Class. The class should take in three arguments
  //A beginning time, ending time, and a description.

  /**
   * Creates an instance of the Appointment class with a description, start time and end time. Constructor
   * also checks for errors in the start and end time by making sure they are in the correct format. A
   * regular expression is used to check for incorrect formatting. The description is also checked to make
   * sure that a null or empty String has been passed in.
   * @param startDate
   *        The date at which the appointment is scheduled to begin. Format: mm/dd/yyyy
   * @param startTime
   *        The time of day at which the appointment is scheduled to begin. Format: hh:mm
   * @param endDate
   *        The date at which the appointment is scheduled to end. Format: mm/dd/yyyy
   * @param endTime
   *        The time of day at which the appointment is scheduled to end. Format: hh:mm
   * @param description
   *        Description about the appointment being made.
   */
  public Appointment(String startDate, String startTime, String endDate, String endTime, String description) {
    //Check that the times and dates are in the correct format mm/dd/yyyy
    this.startDate = startDate;
    this.startTime = startTime;
    this.endDate = endDate;
    this.endTime = endTime;

    String startAppt = startDate + " " + startTime;
    String endAppt = endDate + " " + endTime;

    //Check that description is not empty or null using regex
    String regEx = "^[0-9]?[0-9]/[0-9]?[0-9]/([0-9]{4}) [0-9]?[0-9]:[0-9][0-9]$";
    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(startAppt);
    if( matcher.matches() == false)
    {
      System.err.println("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm");
      System.exit(1);
    }

    matcher = pattern.matcher(endAppt);
    if( matcher.matches() == false)
    {
      System.err.println("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm");
      System.exit(1);
    }



    if(description == null || description.isEmpty() == true)
    {
      System.err.println("Description cannot be empty or null");
      System.exit(1);
    }
    this.description = description;
  }

  /**
   * Method to return the  beginning time and date of the appointment.
   * @return Returns the startDate and startTime concatenated
   */
  @Override
  public String getBeginTimeString() {
    return startDate + " " + startTime;
  }

  /**
   * Method to return the ending time and date of the appointment.
   * @return Returns the endDate and endTime concatenated
   */
  @Override
  public String getEndTimeString() {
    return endDate + " " + endTime;
  }

  /**
   * Method to return the description of the appointment
   * @return Returns the description member of the class
   */
  @Override
  public String getDescription() {
    return description;
  }
}
