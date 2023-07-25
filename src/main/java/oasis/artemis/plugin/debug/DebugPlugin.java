package oasis.artemis.plugin.debug;

import oasis.artemis.Artemis;
import oasis.artemis.level.Level;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.object.SimpleObject;
import oasis.artemis.plugin.SimplePlugin;
import oasis.artemis.session.player.Player;
import oasis.artemis.task.TaskAdapter;
import oasis.artemis.ui.component.viewport.Viewport;
import oasis.artemis.ui.component.viewport.ViewportRenderContext;
import oasis.artemis.util.geometry.profile.SphereProfile;
import oasis.artemis.util.math.RotationBuilder;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>DebugPlugin</h2>
 * <p>Plugin used for debugging.</p>
 */
public final class DebugPlugin extends SimplePlugin {
    public DebugPlugin() {
        super("ArtemisEngineDebugPlugin", "1.0");
    }

    @Override
    public void onEngineStarted() {
        System.out.println("Engine has started!");

        final Player localPlayer = Artemis.getSessionManager().getLocalPlayer();
        if (localPlayer == null) return;

        final ArtemisObject player = SimpleObject.builder()
                .location(new Vector(0, 0, -100))
                .geometry(new SphereProfile(10))
                .mass(100)
                .build();

        localPlayer.setPawn(player);

        final Viewport viewport = new Viewport();

        Artemis.getWindow().add(viewport);
        viewport.setVisible(true);
        viewport.setSize(Artemis.getWindow().getSize());


        final Level level = Artemis.getLevelManager().getLevel("ArtemisWorld");
        if (level == null) return;

        level.addObject(player);

        final ArtemisObject o2 = SimpleObject.builder()
                .location(new Vector(0, 0, 100))
                .geometry(new SphereProfile(100))
                .mass(10)
                .rotationRate(RotationBuilder.fromYawDegrees(45).build())
                .build();

        level.addObject(o2);

        Artemis.getSyncScheduler().registerTask(new TaskAdapter() {
            @Override
            public void execute(@Nonnull Duration delta) {
                viewport.render(new ViewportRenderContext(
                        level,
                        player.getLocation(),
                        player.getRotation(),
                        List.of(player)
                ));
            }
        });

        Artemis.getAsyncScheduler().registerTask(new TaskAdapter() {
            @Override
            public void execute(@Nonnull Duration delta) {
                System.out.println(player.getLocation());
            }

            @Nonnull
            @Override
            public Duration getInterval() {
                return Duration.standardSeconds(1);
            }
        });
    }
}
