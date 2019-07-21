package edu.pdx.cs410J.caameron;

import edu.pdx.cs410J.AbstractAppointment;

import java.sql.CallableStatement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Comparable;

/**
 * Appointment class which will represent a single appointment and some of the details that belong with it
 * This will include a start date and time, a end date and time, and a brief description about the appointment
 * This class extends AbstractAppointment. All data members of the class are represented using Strings. For now
 * the class only sets all the data in the constructor and uses methods to get the values when needed.
 */
public class Appointment extends AbstractAppointment implements Comparable<Appointment>{

  //Data members for Appointment class, will have variables for
  //start, end and description
  private String startDate;
  private String endDate;
  private String endTime;
  private String startTime;
  private String description;
  private String startTimeOfDay;
  private String endTimeOfDay;
  private Date startTimeDate;
  private Date endTimeDate;

  //Constructor for Appointment Class. The class should take in three arguments
  //A beginning time, ending time, and a description.

  /**
   * Creates an instance of the Appointment class with a description, start time and end time. Constructor
   * also checks for errors in the start and end time by making sure they are in the correct format. A
   * regular expression is used to check for incorrect formatting. The description is also checked to make
   * sure that a null or empty String has been passed in.
   * Also takes in the dates and times and converts them to Date objects
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
   * @param startTimeOfDay
   *        am or pm of start time
   * @param endTimeOfDay
   *        am or pm of end time
   *
   */
  public Appointment(String startDate, String startTime, String endDate, String endTime, String description, String startTimeOfDay, String endTimeOfDay) throws Exception {
    //Check that the times and dates are in the correct format mm/dd/yyyy
    this.startDate = startDate;
    this.startTime = startTime;
    this.endDate = endDate;
    this.endTime = endTime;
    this.startTimeOfDay = startTimeOfDay;
    this.endTimeOfDay = endTimeOfDay;


    String startAppt = startDate + " " + startTime;
    String endAppt = endDate + " " + endTime;

    //Check that description is not empty or null using regex
    String regEx = "^[0-9]?[0-9]/[0-9]?[0-9]/([0-9]{4}) [0-1]?[0-9]:[0-6][0-9]$";
    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(startAppt);
    if( matcher.matches() == false)
    {
      throw new Exception("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm");
    }

    matcher = pattern.matcher(endAppt);
    if( matcher.matches() == false)
    {
      throw new Exception("Date and Time format incorrect. Must be of format dd/mm/yyyy hh:mm");
    }

    //Check that it is am or pm
    if(!(startTimeOfDay.toLowerCase().equals("am")) && !(startTimeOfDay.toLowerCase().equals("pm")))
    {
      throw new Exception("Start time of day is not am or pm. Please Specify 'am' or 'pm'");
    }

    if(!(endTimeOfDay.toLowerCase().equals("am")) && !(endTimeOfDay.toLowerCase().equals("pm")))
    {
      throw new Exception("End time of day is not am or pm. Please Specify 'am' or 'pm'");
    }

    //Set date and time to Date objects
    //Convert to ints
    String[] splitDate = startDate.split("/");
    String[] splitTime = startTime.split(":");
    int year, month, date, hrs, min;
    year = Integer.parseInt(splitDate[2]);
    month = Integer.parseInt(splitDate[0]);
    date = Integer.parseInt(splitDate[1]);
    hrs = Integer.parseInt(splitTime[0]);
    min = Integer.parseInt(splitTime[1]);
    Calendar calStart = Calendar.getInstance();
    calStart.set(Calendar.YEAR, year);
    calStart.set(Calendar.MONTH, month-1);
    calStart.set(Calendar.DAY_OF_MONTH, date);
    calStart.set(Calendar.HOUR, hrs);
    calStart.set(Calendar.MINUTE, min);
    if(startTimeOfDay.toLowerCase().equals("am") == true)
    {
      calStart.set(Calendar.AM_PM, Calendar.AM);
    }
    else
    {
      calStart.set(Calendar.AM_PM, Calendar.PM);
    }

    String[] splitDateEnd = endDate.split("/");
    String[] splitTimeEnd = endTime.split(":");
    year = Integer.parseInt(splitDateEnd[2]);
    month = Integer.parseInt(splitDateEnd[0]);
    date = Integer.parseInt(splitDateEnd[1]);
    hrs = Integer.parseInt(splitTimeEnd[0]);
    min = Integer.parseInt(splitTimeEnd[1]);
    Calendar calEnd = Calendar.getInstance();
    calEnd.set(Calendar.YEAR, year);
    calEnd.set(Calendar.MONTH, month-1);
    calEnd.set(Calendar.DAY_OF_MONTH, date);
    calEnd.set(Calendar.HOUR, hrs);
    calEnd.set(Calendar.MINUTE, min);
    if(endTimeOfDay.toLowerCase().equals("am") == true)
    {
      calEnd.set(Calendar.AM_PM, Calendar.AM);
    }
    else
    {
      calEnd.set(Calendar.AM_PM, Calendar.PM);
    }

    startTimeDate = calStart.getTime();
    endTimeDate = calEnd.getTime();
    if(startTimeDate.compareTo(endTimeDate) > 0)
    {
      throw new Exception("Start time of appointment cannot be after end time");
    }
//    System.out.println(startTimeDate);
//    System.out.println(endTimeDate);
//    System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(startTimeDate));
//    System.out.println(DateFormat.getTimeInstance(DateFormat.SHORT).format(startTimeDate));
//    System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(endTimeDate));
//    System.out.println(DateFormat.getTimeInstance(DateFormat.SHORT).format(endTimeDate));

    if(description == null || description.isEmpty() == true || startDate.isEmpty() == true || startTime.isEmpty() == true || endDate.isEmpty() == true || endTime.isEmpty() == true)
    {
      throw new Exception("Description cannot be empty or null");
    }
    this.description = description;


  }

  /**
   * Method to return the  beginning time and date of the appointment using DateFormat.SHORT
   * @return Returns the startDate and startTime concatenated
   */
  @Override
  public String getBeginTimeString() {
    String returnString = DateFormat.getDateInstance(DateFormat.SHORT).format(startTimeDate) + " " + DateFormat.getTimeInstance(DateFormat.SHORT).format(startTimeDate);
    return returnString;
  }

  /**
   * Method to return the ending time and date of the appointment using DateFormat.SHORT
   * @return Returns the endDate and endTime concatenated
   */
  @Override
  public String getEndTimeString() {
    String returnString = DateFormat.getDateInstance(DateFormat.SHORT).format(endTimeDate)+ " " + DateFormat.getTimeInstance(DateFormat.SHORT).format(endTimeDate);
    return returnString;
  }

  /**
   * Method to return the description of the appointment
   * @return Returns the description member of the class
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Method to return the beginning time in a Date object
   * @return Returns Data object of beginning time
   */
  @Override
  public Date getBeginTime() {
    return startTimeDate;
  }

  /**
   * Method to return the ending time in a Date object
   * @return Returns Date object of ending time
   */
  @Override
  public Date getEndTime() {
    return endTimeDate;
  }

  /**
   * Method to return beginning time in a format for the text file
   * @return Returns String of beginning time
   */
  public String getBeginTimeFile() {
    return startDate + " " + startTime + " " + startTimeOfDay;
  }

  /**
   * Method to return ending time in a format for the text file
   * @return Returns String of ending time
   */
  public String getEndTimeFile() {
    return endDate + " " + endTime + " " + endTimeOfDay;
  }

  /**
   * Method to compare one appointment to another in order to sort by chronologically/lexigraphically.
   * @param o Appointment to be compared
   * @return int that will represent if appointment is before, after, or equal to another appointment
   *         -1 : before appointment
   *         1  : after appointment
   *         0  : same time and description
   */
  @Override
  public int compareTo(Appointment o) {
    int comparison = startTimeDate.compareTo(o.getBeginTime());
    if(comparison == 0)
    {
      comparison = endTimeDate.compareTo(o.getEndTime());
      //check for ending date comparison
      if (comparison == 0) {
        //Check for description
        comparison = description.compareTo(o.getDescription());
      }
    }

    return comparison;
  }
}
