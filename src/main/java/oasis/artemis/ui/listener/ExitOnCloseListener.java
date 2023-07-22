package oasis.artemis.ui.listener;

import oasis.artemis.Artemis;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <h2>ExitOnCloseListener</h2>
 * <p>
 * This will stop the engine when the window is closed.
 * Add this listener to the window in order to add this behavior.
 * </p>
 */
public final class ExitOnCloseListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
        Artemis.stop();
    }
}
