package store.scriptbenio.ui.dropdown;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import store.scriptbenio.Benio;
import store.scriptbenio.module.Module;
import store.scriptbenio.util.SoundUtil;
import store.scriptbenio.util.render.RenderUtil;
import store.scriptbenio.util.render.hover.HoverUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleButtons {


    public Module mod;
    public Frame parent;
    public int offset;

    public ModuleButtons(Module mod, Frame parent, int offset){
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        FontRenderer fr = Benio.INSTANCE.getMc().fontRendererObj;

        RenderUtil.rect(parent.x, offset + 35, parent.width, parent.height, new Color(35, 35, 35,255));
        RenderUtil.rect(parent.x, offset + 34, parent.width, 1, new Color(60, 60, 60,255));

        fr.drawString(mod.getName(), parent.x + 7, offset + 38, mod.isToggled() ? Color.GREEN.getRGB() : -1);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(HoverUtil.rectHovered(parent.x, offset + 35, parent.width, offset, mouseX, mouseY) && mouseButton == 0){
            mod.toggle();
            SoundUtil.playSound("random.click");
        }
    }

}
