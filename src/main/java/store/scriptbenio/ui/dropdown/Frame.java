package store.scriptbenio.ui.dropdown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import store.scriptbenio.Benio;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.util.render.RenderUtil;
import store.scriptbenio.util.render.hover.HoverUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<ModuleButtons> moduleButtons;

    public Category cat;
    public int offset, x, y, width, height;
    public boolean extended = false;

    public Frame(Category cat, int x, int y, int width, int height){
        this.cat = cat;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        moduleButtons = new ArrayList<>();

        int offset = height;
        for(Module mod : Benio.INSTANCE.getMm().getModules(cat)){
            moduleButtons.add(new ModuleButtons(mod, this, offset));
            offset += height;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = Benio.INSTANCE.getMc().fontRendererObj;

        RenderUtil.rect(x, y, width, height, new Color(35, 35, 35,255));
        fr.drawString(cat.name(), x + 7, y + 4, -1);

        if(extended){
            fr.drawString("-", x + 65, y + 4, -1);

            for(ModuleButtons mb : moduleButtons){
                mb.drawScreen(mouseX, mouseY, partialTicks);
            }
        }else{
            fr.drawString("+", x + 65, y + 4, -1);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException{

    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
        for(ModuleButtons mb : moduleButtons){
            mb.mouseClicked(mouseX, mouseY, mouseButton);
        }

        if(HoverUtil.rectHovered(x, y, width, height, mouseX, mouseY) && mouseButton == 1){
            extended = !extended;
        }
    }

}
