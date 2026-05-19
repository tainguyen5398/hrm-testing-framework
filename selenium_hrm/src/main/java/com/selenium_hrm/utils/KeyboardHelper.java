package com.selenium_hrm.utils;

import org.openqa.selenium.By;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyboardHelper {

    private static Robot robot;

    public static Robot getRobot() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException("Failed to initialize Robot", e);
            }
        }
        return robot;
    }

    private KeyboardHelper() {}

    public static void pressEnter() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void pressEscape() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_ESCAPE);
        r.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public static void pressF11() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_F11);
        r.keyRelease(KeyEvent.VK_F11);
    }

    public static void pressTab() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
    }

    public static void pressBackspace() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_BACK_SPACE);
        r.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    public static void pressDelete() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_DELETE);
        r.keyRelease(KeyEvent.VK_DELETE);
    }

    public static void pressCtrlA() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_A);
    }

    public static void pressCtrlC() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_C);
    }

    public static void pressCtrlV() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
    }

    public static void pressCtrlX() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_X);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_X);
    }

    public static void pressCtrlZ() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_Z);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_Z);
    }

    public static void pressCtrlS() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_S);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_S);
    }

    public static void pressArrowUp() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_UP);
        r.keyRelease(KeyEvent.VK_UP);
    }

    public static void pressArrowDown() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
    }

    public static void pressArrowLeft() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_LEFT);
        r.keyRelease(KeyEvent.VK_LEFT);
    }

    public static void pressArrowRight() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_RIGHT);
        r.keyRelease(KeyEvent.VK_RIGHT);
    }

    public static void pressSpace() {
        Robot r = getRobot();
        r.keyPress(KeyEvent.VK_SPACE);
        r.keyRelease(KeyEvent.VK_SPACE);
    }

    public static void typeText(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        pressCtrlV();
    }
}
