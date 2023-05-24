package org.example.print;

enum FontColor {
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    PINKISH_RED(35),
    CYAN(36),
    WHITE(37);

    public int code;

    FontColor(int code) {
        this.code = code;
    }
}
enum FontStyle {
    /**
     *  加粗
     */
    BOLD(1),
    /**
     *  斜体
     */
    ITALICS(3),
    /**
     *  下划线
     */
    UNDERSCORES(4),
    /**
     *  文字闪烁
     */
    TEXT_FLASHING(5),
    /**
     *  反色
     */
    REVERSE_COLOR(7),
    /**
     *  删除线
     */
    STRIKETHROUGH(9);

    public int code;

    FontStyle(int code) {
        this.code = code;
    }
}
enum BackgroundColor {
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    PINKISH_RED(35),
    CYAN(36),
    WHITE(37);

    public int code;

    BackgroundColor(int code) {
        this.code = code;
    }
}



public class ConsoleColorfulUtils {

    public static String output(FontColor color, String str) {
        return "\033[" + color.code + ";2m" + str + "\033[0m";
    }

    public static String output(FontColor fontColor, BackgroundColor backgroundColor, String str) {
        return "\033[" + fontColor.code + ";2m" + str + "\033[0m";
    }
    public static String output(FontColor fontColor, BackgroundColor backgroundColor, FontStyle fontStyle, String str) {
        return "\033[" + fontColor.code + ";2m" + str + "\033[0m";
    }

    public static void clear(){
        System.out.println("\u001b[2m");
    }
}