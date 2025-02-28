package store.scriptbenio.module.impl.visual;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import store.scriptbenio.Benio;
import store.scriptbenio.event.impl.render.Event2D;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;
import store.scriptbenio.util.color.ColorUtil;

import java.awt.*;
import java.util.Comparator;

@ModuleInfo(
        name = "HUD",
        description = "Mostra informacoes na tela",
        category = Category.Visual
)
public class HUD extends Module {

    private FontRenderer fr = null;

    public HUD() {
        fr = mc.fontRendererObj;
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
    private final Listener<Event2D> on2D = new Listener<>(e -> {
        GL11.glPushMatrix();
        GL11.glScaled(2, 2, 2);
        fr.drawString(Benio.INSTANCE.getName(), 132, 5, Color.BLUE.getRGB());
        GL11.glPopMatrix();

        char[] FPSBPSChars = (Minecraft.getDebugFPS() + " FPS").toCharArray();

        float charOffset = 296;
        int charColorOffset = 0;
        for(char c : FPSBPSChars){
            fr.drawString(String.valueOf(c), charOffset, 30, ColorUtil.chroma(charColorOffset, 1, 1).getRGB(), false);
            charOffset += 6f;
            charColorOffset -= 300;
        }

        float offset = 3;
        int colorOffset = 0;
        for (Object module : Benio.INSTANCE.getMm().getModules().values().stream().sorted(Comparator.comparing(m -> fr.getStringWidth(m.toString())).reversed()).toArray()) {
            Module mod = (Module) module;
            if (!mod.isToggled()) continue;

            fr.drawString(mod.getName(), 3, offset, ColorUtil.chroma(colorOffset, 1, 1).getRGB(), false);
            offset += 10;
            colorOffset -= 300;
        }


    });

    private String getBPS(){
        final float ticks = mc.timer.ticksPerSecond * mc.timer.timerSpeed;
        final double bps = mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.lastTickPosY, mc.thePlayer.lastTickPosZ);
        return String.format("%.2f", bps);
    }
}
