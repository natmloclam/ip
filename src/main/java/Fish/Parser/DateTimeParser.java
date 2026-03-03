package Fish.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;

/**
 * Class that handles the parsing and verification of date/time formats from String to LocalDateTime/LocalDate
 * and vice versa.
 */
public class DateTimeParser {

    /**
     * Returns the date portion of the user input
     *
     * @param input user input in the form of "yyyy-mm-dd HH:mm"
     */
    public static String filterDate(String input) {
        String[] words = input.split(" ", 2);
        return words[0].strip();
    }

    /**
     * Returns the time portion of the user input
     *
     * @param input user input in the form of "yyyy-mm-dd HH:mm"
     * @throws FishException if invalid date-time format was input
     */
    public static String filterTime(String input) throws FishException {
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new FishException(FishMessages.INVALID_DATE_TIME_FORMAT);
        }
        return words[1].strip();
    }

    /**
     * Formats the date to add "0" padding to the month and date so users need not pad them manually
     *
     * @param input date portion of the user input
     * @return date portion padded with 0's where necessary, as a String
     * @throws FishException if invalid date format was input
     */
    public static String dateFormatter(String input) throws FishException {
        String[] words = input.split("-");
        if (words.length != 3) {
            throw new FishException(FishMessages.INVALID_DATE_TIME_FORMAT);
        }
        return words[0] + "-" + String.format("%02d", Integer.parseInt(words[1])) + "-"
                + String.format("%02d", Integer.parseInt(words[2]));
    }

    /**
     * Outputs date-time as a String in the form "MMM d yyyy, h:mma".
     * E.g: "2026-02-22T23:30" becomes "Feb 22 2026, 11:30pm".
     *
     * @param dateTime LocalDateTime object
     */
    public static String formatDateTimeOutput(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return dateTime.format(formatter);
    }

    /**
     * Outputs date as a String in the form "MMM d yyyy".
     * E.g: "2026-02-22" becomes "Feb 22 2026".
     *
     * @param date LocalDate object
     */
    public static String formatDateOutput(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return date.format(formatter);
    }

    /**
     * Returns user input as a LocalDateTime object. Input allows
     * for date and month to be single digits.
     *
     * @param input date-time in the form "yyyy-mm-dd HH:mm"
     * @return date-time object in the form "yyyy-mm-ddTHH:mm"
     * @throws FishException if input is invalid
     */
    public static LocalDateTime parse(String input) throws FishException {
        String date = filterDate(input);
        String time = filterTime(input);

        String formattedDate = dateFormatter(date);

        return LocalDateTime.parse(formattedDate + "T" + time);
    }

    /**
     * Returns user input as a LocalDate object. Input allows
     * for date and month to be single digits.
     *
     * @param input date-time in the form "yyyy-mm-dd"
     * @return date-time object in the form "yyyy-mm-dd"
     * @throws FishException if input is invalid
     */
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