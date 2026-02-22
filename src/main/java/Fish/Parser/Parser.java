package Fish.Parser;

public class Parser {
    public static String filterCommand(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words[0];
    }

    public static String filterArg(String sentence) {
        String[] splitSentence = sentence.split(" ");
        if (splitSentence.length < 2) {
            return "";
        }
        String[] words = sentence.split(" ", 2);
        return words[1];
    }
}