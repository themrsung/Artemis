package oasis.artemis.ui.window;

import oasis.artemis.ui.component.UIComponent;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

/**
 * <h2>UIWindow</h2>
 * <p>A window is used as a frame for UI components.</p>
 */
public class UIWindow extends JFrame {
    //
    // Constructors
    //

    /**
     * Creates a new empty UI window.
     *
     * @throws HeadlessException When a required environment property cannot be found
     */
    public UIWindow() throws HeadlessException {
        initializeWindow();
    }

    /**
     * Creates a new window with given graphics configuration.
     *
     * @param gc Graphics configuration
     */
    public UIWindow(@Nonnull GraphicsConfiguration gc) {
        super(gc);

        initializeWindow();
    }

    /**
     * Creates a new window with given title.
     *
     * @param title Title of this window
     * @throws HeadlessException When a required environment property cannot be found
     */
    public UIWindow(@Nonnull String title) throws HeadlessException {
        super(title);

        initializeWindow();
    }

    /**
     * Creates a new window with given parameters.
     *
     * @param title Title of this window
     * @param gc    Graphics configuration
     */
    public UIWindow(@Nonnull String title, @Nonnull GraphicsConfiguration gc) {
        super(title, gc);

        initializeWindow();
    }

    //
    // Initializer
    //

    /**
     * Performs initialization of this window.
     */
    private void initializeWindow() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                Arrays.stream(getComponents()).forEach(c -> {
                    if (c instanceof UIComponent ui) notifyComponentShown(ui);
                });
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                Arrays.stream(getComponents()).forEach(c -> {
                    if (c instanceof UIComponent ui) notifyComponentHidden(ui);
                });
            }
        });
    }

    /**
     * Notifies a component that it has been shown.
     *
     * @param component Component to notify
     */
    private void notifyComponentShown(@Nonnull UIComponent component) {
        component.onComponentShown(this);
    }

    /**
     * Notifies a component that it has been hidden.
     *
     * @param component Component to notify
     */
    private void notifyComponentHidden(@Nonnull UIComponent component) {
        component.onComponentHidden(this);
    }
}
