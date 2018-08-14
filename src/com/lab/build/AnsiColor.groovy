package com.lab.build

/**
 * Ansi text colors
 *
 * @version $Id: $
 */
enum AnsiColor {
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    MAGENTA(5),
    CYAN(6),
    WHITE(7)

    AnsiColor(int value) {
        this.value = value
    }
    private final int value

    int getValue() {
        value
    }
}