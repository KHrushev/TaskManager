package ua.edu.sumdu.j2se.hrushev.tasks.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface DateGetter {
    default LocalDateTime getDate() {
        LocalDateTime date = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date = LocalDateTime.parse(reader.readLine(), formatter);
            return date;
        } catch (DateTimeParseException | NumberFormatException | IOException parseException) {
            System.out.println("You've entered date/time incorrectly, try again.");
            return getDate();
        }
    }
}
