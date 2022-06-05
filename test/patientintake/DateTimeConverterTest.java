package patientintake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Date time converter should")
class DateTimeConverterTest {

    @Nested
    @DisplayName("convert string with 'today' keyword ")
    class TodayTest{
        @Test
        @DisplayName("correctlly")
        void convertStringToDateTimeCorrectlly() {
            LocalDate today = LocalDate.of(2018, 8, 26);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
            assertEquals(result,LocalDateTime.of(2018, 8, 26,13,0),
                    ()->"Failed to convert failed string to expected date time : today passed was :" +today);
        }

        @Test
        @DisplayName("regardless of the CASE")
        void convertStringToDateTimeCorrectllyCaseInsensative() {
            LocalDate today = LocalDate.of(2018, 8, 26);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm", today);
            assertEquals(result,LocalDateTime.of(2018, 8, 26,13,0),
                    ()->"Failed to convert failed string to expected date time : today passed was :" +today);
        }
    }


    @Test
    @DisplayName("convert expected datetime into string correctlly")
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2018 1:00 pm",LocalDate.of(2018, 9, 1));
        assertEquals(result,LocalDateTime.of(2018, 9, 2,13,0));
    }

    @Test
    void ThroughExpectedExceptionBack(){
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2018 1:00 pm",LocalDate.of(2018, 9, 2));
        assertEquals(result,LocalDateTime.of(2018, 9, 2,13,0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrectlly")
    void throughExpectedExceptionBack(){ // with asssert Throws method
//        LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2018 100 pm",LocalDate.of(2018, 9, 1));
       Throwable error =  assertThrows(RuntimeException.class,()->{
            DateTimeConverter.convertStringToDateTime("9/2/2018 100 pm",
                    LocalDate.of(2018, 9, 1));
        });
       assertEquals("Unable to create date time from: [9/2/2018 100 pm], please enter with format [M/d/yyyy h:mm a], Text '9/2/2018 100 PM' could not be parsed at index 12",error.getMessage());
    }

}