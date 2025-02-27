package store.scriptbenio.ui.dropdown;

import net.minecraft.client.gui.Gui;
import store.scriptbenio.module.Category;

import java.io.IOException;

public class Frame {
    public Category cat;

    public Frame(Category cat){
        this.cat = cat;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(100, 100, 10, 10, -1);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException{

    }

}
