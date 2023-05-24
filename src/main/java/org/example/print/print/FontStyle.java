package org.example.print.print;

public enum FontStyle {
    /**
     * 加粗
     */
    BOLD(1),
    /**
     * 淡色
     */
    PALE_COLOR(2),
    /**
     * 斜体
     */
    ITALICS(3),
    /**
     * 下划线
     */
    UNDERSCORES(4),
    /**
     * 文字闪烁
     */
    TEXT_FLASHING(5),
    /**
     * 反色
     */
    REVERSE_COLOR(7),
    /**
     * 隐藏
     */
    HIDE(8),
    /**
     * 删除线
     */
    STRIKETHROUGH(9);

    public int code;

    FontStyle(int code) {
        this.code = code;
    }
}
