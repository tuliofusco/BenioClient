package store.scriptbenio;

import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import store.scriptbenio.command.CommandManager;
import store.scriptbenio.event.impl.input.EventKey;
import store.scriptbenio.module.ModuleManager;

@Getter
public enum Benio implements Subscriber {
    INSTANCE;

    private final Minecraft mc = Minecraft.getMinecraft();

    public static final EventBus BUS = EventManager.builder()
            .setName("root/Benio")
            .setSuperListeners()
            .build();

    private String
            name = "BenioClient",
            version = "alpha v1.0.0",
            commandPrefix = "#",
            clientPrefix = "[BENIO] ",
            authors = "Gabriel Scatena & Tulio Fusco";

    private ModuleManager mm;
    private CommandManager cm;

    public final void init () {
        BUS.subscribe(this);
        Display.setTitle(name);

        mm = new ModuleManager();
        cm = new CommandManager();
    }

    public final void shutdown () {
        BUS.unsubscribe(this);
    }

    @Subscribe
    private final Listener<EventKey> listener = new Listener<>(e -> {
        if (this.mm != null) {
            mm.getModules().values().forEach(m -> {
                if (m.getKey() == e.getKey()) {
                    m.toggle();
                }
            });
        }
    });

}
