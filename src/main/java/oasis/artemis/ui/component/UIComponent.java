package oasis.artemis.ui.component;

import oasis.artemis.ui.window.UIWindow;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * <h2>UIComponent</h2>
 * <p>
 * A UI component is placed in a frame, and can display information to the user.
 * </p>
 */
public class UIComponent extends JPanel {
    //
    // Constructors
    //

    /**
     * Creates a new UI component.
     *
     * @param layout           The layout manager object
     * @param isDoubleBuffered {@code true} to make this component double-buffered
     */
    public UIComponent(@Nonnull LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Creates a new UI component.
     *
     * @param layout The layout manager object
     */
    public UIComponent(@Nonnull LayoutManager layout) {
        super(layout);
    }

    /**
     * Creates a new UI component.
     *
     * @param isDoubleBuffered {@code true} to make this component double-buffered
     */
    public UIComponent(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    /**
     * Creates a new blank component.
     */
    public UIComponent() {
    }

    //
    // Events
    //

    /**
     * Called when this component is made visible.
     *
     * @param frame Frame this component is in
     */
    public void onComponentShown(@Nonnull UIWindow frame) {}

    /**
     * Called when this component is made invisible.
     *
     * @param frame Frame this component is in
     */
    public void onComponentHidden(@Nonnull UIWindow frame) {}
}
