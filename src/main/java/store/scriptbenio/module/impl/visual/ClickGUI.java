package store.scriptbenio.module.impl.visual;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.Benio;
import store.scriptbenio.event.impl.render.Event2D;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;
import store.scriptbenio.ui.dropdown.DropdownGUI;

@ModuleInfo(
        name = "ClickGUI",
        description = "interface grafica",
        category = Category.Visual
)
public class ClickGUI extends Module {

    public ClickGUI(){
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {

        super.onEnable();
    }

    @Override
    public void onDisable(){
        mc.displayGuiScreen(null);
        super.onDisable();
    }

    @Subscribe
    private final Listener<Event2D> on2D = new Listener<>(e -> {
        mc.displayGuiScreen(Benio.INSTANCE.getGui());
    });

}
