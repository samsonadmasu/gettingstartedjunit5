package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeAll
   static void test(){
      System.out.println("Before All ...");
   }

   @BeforeEach
   void init(){
      System.out.println("Before Each ...");
      calendar = new ClinicCalendar(LocalDate.of(2022,6,5));
   }

   @AfterEach
  void tearDownMethodAfterEach(){
      System.out.println("After Each ...");
   }

   @AfterAll
  static void tearDownMethodAfterAll(){
      System.out.println("After All ...");
   }

   @Test
   void allowEntryOfAnAppointment() {
      System.out.println("Entry of Appointment ...");
//      ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);
      assertEquals("Jim", enteredAppt.getPatientFirstName());
      assertEquals("Weaver", enteredAppt.getPatientLastName());
      assertEquals(Doctor.avery, enteredAppt.getDoctor());
      assertEquals("9/1/2018 02:00 PM",
         enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));
   }

   @Test
   void getTodaysAppointmenr(){
//      ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 4:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery", "9/01/2022 2:00 pm");

      assertEquals(2,calendar.getTodayAppointments().size());
   }

   @Test
   void getTotalAppointmenr(){
//      ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 4:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery", "9/01/2022 2:00 pm");

      assertEquals(3,calendar.getAppointments().size());
   }

   @Test
   void getEqualsFromCollection(){ //to test itterable collections

      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery", "6/5/2022 4:00 pm");
//      calendar.addAppointment("Jim", "Weaver", "avery", "9/01/2022 2:00 pm");

//      assertEquals(calendar.getAppointments().size(),calendar.getTodayAppointments().size());
      assertIterableEquals(calendar.getAppointments(),calendar.getTodayAppointments());
   }
}