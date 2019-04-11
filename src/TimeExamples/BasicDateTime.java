package TimeExamples;

import java.time.*;

public class BasicDateTime {
    public static void main(String[] args){
        System.out.println("Instant.now(): " + Instant.now());
        System.out.println("LocalDate.now(): " + LocalDate.now());
        System.out.println("LocalTine.now(): " + LocalTime.now());
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());

        System.out.println("First landing on the Moon:");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 30);
        System.out.println("Date: " + moonLandingDate);
        System.out.println("Time: " + moonLandingTime);

        System.out.println("Neil Armstrong steps onto the surface: ");
        LocalTime walkTime = LocalTime.of(20, 30, 56, 150_000_000);
        LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
        System.out.println(walk);


        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
        ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(nyc);
        ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Africa/Accra"));
        System.out.println(london);
    }
}
