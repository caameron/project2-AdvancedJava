package edu.pdx.cs410J.caameron;

import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.AnnotatedParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class AppointmentBookTest {
    private String  testOwner = "testOwner";

    @Ignore
    @Test
    public void getOwnerNameShouldReturnTheOwnerThatWasPassedIntoClass() {
        AppointmentBook appointmentBook = new AppointmentBook(testOwner);
        assertThat(appointmentBook.getOwnerName(), containsString("testOwner"));
    }

    @Ignore
    @Test
    public void createdApplicationBookShouldHaveEmptyAppointmentsCollection() {
        AppointmentBook appointmentBook = new AppointmentBook(testOwner);
        assertThat(appointmentBook.getAppointments(), equalTo(null));
    }

    @Ignore
    @Test
    public void ifThereIsAtleastOneAppointmentReturnAppointmentsCollection() throws Exception {
        AppointmentBook appointmentBook = new AppointmentBook(testOwner);
        Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription","am", "pm");
        appointmentBook.addAppointment(appointment);
        //Test Data that it should match up with.
        Collection correctResult = new ArrayList<Appointment>();
        correctResult.add(appointment);
        assertThat(appointmentBook.getAppointments(), equalTo(correctResult));
    }

    @Ignore
    @Test
    public void addingAnAppointmentDoesNotThrowAnError() throws Exception {
        AppointmentBook appointmentBook = new AppointmentBook(testOwner);
        Appointment appointment = new Appointment("12/12/1212", "12:12", "12/12/1212", "12:12", "TestDescription","am", "pm");
        appointmentBook.addAppointment(appointment);
    }

    @Ignore
    @Test
    public void ifOwnerIsNullThrowError() {

    }

    @Ignore
    @Test
    public void ifNoAppointmentsandgetAppointmentsIsCalledReturnErrorMsg() {

    }
}
