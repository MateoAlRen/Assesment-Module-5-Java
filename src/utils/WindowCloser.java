package utils;

import java.awt.Window;
import javax.swing.JFrame;

public class WindowCloser {
    public static void closeAllFrames() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                window.dispose(); // Close the frame
            }
        }
    }
}
