package Fish.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;

public class DateTimeParser {

    // date input in the form yyyy-mm-dd
    public static String filterDate(String input) {
        String[] words = input.split(" ", 2);
        return words[0].strip();
    }

    public static String filterTime(String input) throws FishException {
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new FishException(FishMessages.INVALID_DATE_TIME_FORMAT);
        }
        return words[1].strip();
    }

    public static String dateFormatter(String input) throws FishException {
        String[] words = input.split("-");
        if (words.length != 3) {
            throw new FishException(FishMessages.INVALID_DATE_TIME_FORMAT);
        }
        return words[0] + "-" + String.format("%02d", Integer.parseInt(words[1])) + "-"
                + String.format("%02d", Integer.parseInt(words[2]));
    }

    public static String formatDateTimeOutput(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return input.format(formatter);
    }

    public static String formatDateOutput(LocalDate input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return input.format(formatter);
    }

    public static LocalDateTime parse(String input) throws FishException {
        String date = filterDate(input);
        String time = filterTime(input);

        String formattedDate = dateFormatter(date);

        return LocalDateTime.parse(formattedDate + "T" + time);
    }

    public static LocalDate parseDate(String input) throws FishException {
        String data;
        try {
            data = dateFormatter(input);
        } catch (FishException e) {
            throw new FishException(FishMessages.INVALID_DATE_FORMAT);
        }
        return LocalDate.parse(data);
    }
}