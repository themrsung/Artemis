package oasis.artemis.ui.component.viewport;

import oasis.artemis.level.Level;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.ui.component.UIComponent;
import oasis.artemis.util.geometry.Vertex;
import oasis.artemis.util.geometry.solid.Solid;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Viewport</h2>
 * <p>A viewport renders a level to a 2D screen.</p>
 */
public class Viewport extends UIComponent {
    /**
     * Default constructor.
     */
    public Viewport() {
        super();

        this.vertices = new ArrayList<>();
        this.painting = false;
    }

    //
    // Render
    //

    /**
     * Renders given contextual data to this viewport.
     *
     * @param context Render context
     * @see ViewportRenderContext
     */
    public void render(@Nonnull ViewportRenderContext context) {
        if (painting) return;

        // Clear cache
        vertices.clear();

        final Level level = context.level();
        final Vector origin = context.origin();
        final Quaternion angle = context.angle();
        final List<ArtemisObject> blacklist = context.renderBlacklist();

        final List<ArtemisObject> render = level.getObjects().stream().filter(o -> !blacklist.contains(o)).toList();
        render.forEach(o -> {
            final Solid solid = o.getSolid();
            solid.getVertices().forEach(v -> vertices.add(v.transform(origin, angle).inflate(50)));
        });

        this.context = context;

        repaint();
    }

    /**
     * Paints vertices to screen.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(@Nonnull Graphics g) {
        painting = true;

        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Translate
        g.translate(getWidth() / 2, getHeight() / 2);

        // Copy list to prevent concurrent modification
        new ArrayList<>(vertices).forEach(v -> {
            final Polygon p = new Polygon();
            v.forEach(point -> {
                final Pair<Integer> coordinates = translateVector(point, 300);
                p.addPoint(coordinates.getFirst(), coordinates.getSecond());
            });

            g.setColor(v.getColor());
            g.fillPolygon(p);
        });

        g.setColor(Color.BLACK);
        g.drawString("Vertices: " + vertices.size(), -getWidth() / 2 + 40, -getHeight() / 2 + 40);

        if (context != null) {
            g.drawString("Location: " + context.origin(), -getWidth() / 2 + 40, -getHeight() / 2 + 80);
        }

        painting = false;
    }

    @Nonnull
    protected Pair<Integer> translateVector(@Nonnull Vector v, int FOV_L) {
        return new Pair<>(
                (int) (FOV_L / (FOV_L + v.getZ()) * -v.getX()),
                (int) (FOV_L / (FOV_L + v.getZ()) * -v.getY()) // Invert Y
        );
    }

    //
    // Variables
    //
    /**
     * An internal cache of vertices to render.
     */
    @Nonnull
    protected final List<Vertex> vertices;

    /**
     * Cached viewport context.
     */
    @Nullable
    protected ViewportRenderContext context;

    /**
     * A marker to prevent concurrent modification of cache
     * while {@link Viewport#paintComponent(Graphics)} is running.
     */
    protected boolean painting;

}
