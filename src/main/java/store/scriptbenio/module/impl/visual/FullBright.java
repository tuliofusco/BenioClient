package store.scriptbenio.module.impl.visual;


import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;

@ModuleInfo(
        name = "FullBright",
        description = "You are the Sun.",
        category = Category.Visual
)
public final class FullBright extends Module {

    private float oldGamma;

    public FullBright () {
        setKey(Keyboard.KEY_M);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        oldGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        mc.gameSettings.gammaSetting = oldGamma;
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(e -> {
        mc.gameSettings.gammaSetting = 3;
    });
}
