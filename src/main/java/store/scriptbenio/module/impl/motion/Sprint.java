package store.scriptbenio.module.impl.motion;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;

@ModuleInfo(
        name = "Sprint",
        description = "Non legit Sprint",
        category = Category.Motion
)
public class Sprint extends Module {

    public Sprint () {
        // SPRINT KEY BIND
        setKey(Keyboard.KEY_B);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> onUpdate = new Listener<>(e -> {
        if (mc.thePlayer.isCollidedHorizontally) return;
        if (mc.thePlayer.moveForward <= 0) return;
        if (mc.thePlayer.isUsingItem()) return;
        if (mc.thePlayer.isSneaking()) return;

        mc.thePlayer.setSprinting(true);
    });
}
