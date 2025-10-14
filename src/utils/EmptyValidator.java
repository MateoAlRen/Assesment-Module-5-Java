package utils;

public class EmptyValidator {
    public static boolean isEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }
}
