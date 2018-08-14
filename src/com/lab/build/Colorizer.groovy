package com.lab.build

/**
 * Ansi Colorization for strings
 *
 * @version $Id: $
 */
class Colorizer {
    private AnsiAttribute attr = AnsiAttribute.RESET;
    private AnsiColor fg = null;
    private AnsiColor bg = null;
    private boolean isBackground = false;

    def bright() {
        this.setAttribute(AnsiAttribute.BRIGHT);
    }

    def underline() {
        this.setAttribute(AnsiAttribute.UNDERLINE);
    }

    def blink() {
        this.setAttribute(AnsiAttribute.BLINK);
    }

    private def setAttribute(AnsiAttribute attr)
    {
        this.attr = attr
        return this
    }

    def black() {
        this.setColor(AnsiColor.BLACK)
    }

    def red() {
        this.setColor(AnsiColor.RED)
    }

    def green() {
        this.setColor(AnsiColor.GREEN)
    }

    def yellow() {
        this.setColor(AnsiColor.YELLOW)
    }

    def blue() {
        this.setColor(AnsiColor.BLUE)
    }

    def magenta() {
        this.setColor(AnsiColor.MAGENTA)
    }

    def cyan() {
        this.setColor(AnsiColor.CYAN)
    }

    def white() {
        this.setColor(AnsiColor.WHITE)
    }

    private def setColor(AnsiColor color) {
        if (this.isBackground) {
            this.bg = color
        } else {
            this.fg = color
        }
        return this
    }

    def background() {
        this.isBackground = true
        return this;
    }

    def foreground() {
        this.isBackground = false
        return this;
    }

    String colorize(String text) {
        def fg = '';
        if (this.fg != null) {
            fg = "\u001B" + '[' + this.attr.value + ';' + (30 + this.fg.value) + 'm'
        }
        def bg = '';
        if (this.bg != null) {
            bg = "\u001B" + '[' + (40 + this.bg.value) + 'm'
        }
        def end = "\u001B" + '[' + AnsiAttribute.RESET.value + 'm'
        return fg + bg + text + end
    }

    static colorizer() {
        return new Colorizer()
    }

    /**
     * Helper for common formatting
     *
     * @param text
     */
    static String info(String text) {
        colorizer().bright().blue().colorize(text)
    }

    /**
     * Helper for common formatting
     *
     * @param text
     */
    static String success(String text) {
        colorizer().bright().green().colorize(text)
    }

    /**
     * Helper for common formatting
     *
     * @param text
     */
    static String error(String text) {
        colorizer().bright().red().colorize(text)
    }
}
