package oasis.artemis;

import oasis.artemis.ui.component.viewport.Viewport;
import oasis.artemis.ui.window.UIWindow;
import oasis.artemis.util.geometry.Vertex;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>Test</h2>
 * <p>Testing ground.</p>
 */
public final class Test {
    /**
     * Runs the test.
     * @param args Arguments
     */
    public static void main(@Nonnull String[] args) {
        Vector origin = Vector.ZERO;
        final Vector direction = Vector.NEGATIVE_Y;

        final Vertex plane = new Vertex(
                new Vector(-100, -100, -100),
                new Vector(-100, -100, 100),
                new Vector(100, -100, 100),
                Color.YELLOW
        );

        for (int i = 0; i < 1000; i++) {
            origin = origin.add(direction);
            //////// FUCK
        }

        final UIWindow window = new UIWindow(Artemis.GAME_TITLE + " Test");
        final Viewport viewport = new Viewport();

        window.add(viewport);
        window.setSize(1920, 1080);

        viewport.setSize(window.getSize());
        window.setVisible(true);
    }
}
