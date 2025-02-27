package store.scriptbenio.ui.dropdown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import store.scriptbenio.Benio;
import store.scriptbenio.module.Category;

import java.awt.*;
import java.io.IOException;

public class Frame {
    public Category cat;
    public int offset;

    public Frame(Category cat, int offset){
        this.cat = cat;
        this.offset = offset;

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = Benio.INSTANCE.getMc().fontRendererObj;
        Gui.drawRect(offset + 70, 25, offset - 10, 5, new Color(35, 35, 35,255).getRGB());
        fr.drawString(cat.name(), offset + 12, 10, -1);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException{

    }



}
