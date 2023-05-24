package org.example.print.print;

public enum FontColor {
    /**
     * 黑色
     */
    BLACK(30),
    /**
     * 红色
     */
    RED(31),
    /**
     * 绿色
     */
    GREEN(32),
    /**
     * 黄色
     */
    YELLOW(33),
    /**
     * 蓝色
     */
    BLUE(34),
    /**
     * 洋红色
     */
    PINKISH_RED(35),
    /**
     * 青色
     */
    CYAN(36),
    /**
     * 白色
     */
    WHITE(37);

    public int code;

    FontColor(int code) {
        this.code = code;
    }
}
