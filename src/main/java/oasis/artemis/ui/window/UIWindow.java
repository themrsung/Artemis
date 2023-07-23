package oasis.artemis.ui.window;

import oasis.artemis.ui.component.UIComponent;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
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
    }

    /**
     * Creates a new window with given graphics configuration.
     *
     * @param gc Graphics configuration
     */
    public UIWindow(@Nonnull GraphicsConfiguration gc) {
        super(gc);
    }

    /**
     * Creates a new window with given title.
     *
     * @param title Title of this window
     * @throws HeadlessException When a required environment property cannot be found
     */
    public UIWindow(@Nonnull String title) throws HeadlessException {
        super(title);
    }

    /**
     * Creates a new window with given parameters.
     *
     * @param title Title of this window
     * @param gc    Graphics configuration
     */
    public UIWindow(@Nonnull String title, @Nonnull GraphicsConfiguration gc) {
        super(title, gc);
    }

    //
    // Component notifiers
    //


    @Override
    public void add(@Nonnull Component comp, @Nonnull Object constraints, int index) {
        super.add(comp, constraints, index);
    }

    @Override
    public Component add(@Nonnull String name, @Nonnull Component comp) {
        return super.add(name, comp);
    }

    @Override
    public Component add(@Nonnull Component comp, @Nonnegative int index) {
        return super.add(comp, index);
    }

    @Override
    public Component add(@Nonnull Component comp) {
        return super.add(comp);
    }

    @Override
    public void add(@Nonnull PopupMenu popup) {
        super.add(popup);
    }

    @Override
    public void add(@Nonnull Component comp, @Nonnull Object constraints) {
        super.add(comp, constraints);
    }

    @Override
    protected void addImpl(@Nonnull Component comp, @Nonnull Object constraints, @Nonnegative int index) {
        if (comp instanceof UIComponent ui) ui.onComponentShown(this);
        super.addImpl(comp, constraints, index);
    }

    @Override
    public void remove(@Nonnull Component comp) {
        if (Arrays.stream(getComponents()).toList().contains(comp) && comp instanceof UIComponent ui) {
            ui.onComponentHidden(this);
        }
        super.remove(comp);
    }

    @Override
    public void remove(@Nonnegative int index) {
        try {
            if (getComponents()[index] instanceof UIComponent ui) {
                ui.onComponentHidden(this);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        super.remove(index);
    }

    @Override
    public void remove(@Nonnull MenuComponent m) {
        super.remove(m);
    }

    @Override
    public void removeAll() {
        Arrays.stream(getComponents())
                .filter(UIComponent.class::isInstance)
                .map(UIComponent.class::cast)
                .forEach(ui -> ui.onComponentHidden(this));

        super.removeAll();
    }
}
