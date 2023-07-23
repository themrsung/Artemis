package oasis.artemis.ui.component.start;

import oasis.artemis.Artemis;
import oasis.artemis.ui.component.UIComponent;

import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>StartScreen</h2>
 * <p>The initial screen the game starts with.</p>
 */
public final class StartScreen extends UIComponent {
    /**
     * Default constructor.
     */
    public StartScreen() {
    }

    @Override
    protected void paintComponent(@Nonnull Graphics g) {
        // Background
        paintBackground(g);

        // Title
        paintTitle(g);
    }

    private void paintBackground(@Nonnull Graphics g) {
        g.translate(0, 0);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintTitle(@Nonnull Graphics g) {
        g.translate(getWidth() / 2, getHeight() / 2);
        g.setColor(Color.WHITE);
        g.drawString(Artemis.GAME_TITLE, 0, -250);
    }
}
