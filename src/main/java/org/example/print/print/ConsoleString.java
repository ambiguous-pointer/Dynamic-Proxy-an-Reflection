package org.example.print.print;

public class ConsoleString {
    public static String str(FontColor fontColor, String str) {
        return new StringBuilder()
                .append("\u001B[")
                .append(fontColor.code)
                .append("m")
                .append(str)
                .append("\u001B[0m")
                .toString();
    }

    public static String str(FontColor fontColor, BackgroundColor backgroundColor, String str) {
        return new StringBuilder()
                .append("\u001B[")
                .append(fontColor.code)
                .append("m")
                .append("\u001B[")
                .append(backgroundColor.code)
                .append("m")
                .append(str)
                .append("\u001B[0m")
                .toString();
    }

    public static String str(FontColor fontColor, BackgroundColor backgroundColor, FontStyle fontStyle, String str) {
        return new StringBuilder()
                .append("\u001B[")
                .append(fontColor.code)
                .append("m")
                .append("\u001B[")
                .append(backgroundColor.code)
                .append("m")
                .append("\u001B[")
                .append(fontStyle.code)
                .append("m")
                .append(str)
                .append("\u001B[0m")
                .toString();
    }
}
