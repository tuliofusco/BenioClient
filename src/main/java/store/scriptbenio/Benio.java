package store.scriptbenio;

import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import store.scriptbenio.event.impl.input.EventKey;
import store.scriptbenio.module.ModuleManager;

@Getter
public enum Benio implements Subscriber {
    INSTANCE;

    private final Minecraft mc = Minecraft.getMinecraft();

    private ModuleManager mm;

    public static final EventBus BUS = EventManager.builder()
            .setName("root/Benio")
            .setSuperListeners()
            .build();

    private String
            name = "BenioClient",
            version = "alpha001",
            commandPrefix = "#",
            clientPrefix = "[BENIO] ",
            authors = "Gabriel Scatena & Tulio Fusco";

    public final void init () {
        BUS.subscribe(this);
        Display.setTitle(name + " -> " + version);

        mm = new ModuleManager();
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
