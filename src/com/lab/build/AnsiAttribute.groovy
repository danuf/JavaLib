package com.lab.build

/**
 * Ansi test attributes
 *
 * @version $Id: $
 */
enum AnsiAttribute {
    RESET(0),
    BRIGHT(1),
    UNDERLINE(4),
    BLINK(5)

    AnsiAttribute(int value) {
        this.value = value
    }
    private final int value

    int getValue() {
        value
    }
}
