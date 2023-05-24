package org.example.print;


import org.example.print.print.*;
import org.example.print.print.FontColor;
import org.example.print.print.BackgroundColor;
import org.example.print.print.FontStyle;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleString.str(FontColor.BLACK, BackgroundColor.GREEN, "Hello Word"));
    }
}
