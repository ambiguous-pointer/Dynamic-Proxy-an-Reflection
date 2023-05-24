package org.example.print.print;

public enum BackgroundColor {
    /**
     * 黑色
     */
    BLACK(40),
    /**
     * 红色
     */
    RED(41),
    /**
     * 绿色
     */
    GREEN(42),
    /**
     * 黄色
     */
    YELLOW(43),
    /**
     * 蓝色
     */
    BLUE(44),
    /**
     * 洋红色
     */
    PINKISH_RED(45),
    /**
     * 青色
     */
    CYAN(46),
    /**
     * 白色
     */
    WHITE(47);

    public int code;

    BackgroundColor(int code) {
        this.code = code;
    }
}
