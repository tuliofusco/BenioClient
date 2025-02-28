package store.scriptbenio.ui.dropdown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.Benio;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.impl.visual.ClickGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DropdownGUI extends GuiScreen {

    private final List<Frame> frames;

    public DropdownGUI(){
        frames = new ArrayList<>();

        int offset = 0;
        for(Category cat : Category.values()){
            frames.add(new Frame(cat, 10 + offset, 35, 75, 15));
            offset += 85;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(Frame frames : frames){
                frames.drawScreen(mouseX, mouseY, partialTicks);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for(Frame frame : frames){
            frame.keyTyped(typedChar, keyCode);
        }

        if (keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_RSHIFT){
            Benio.INSTANCE.getMm().getModule(ClickGUI.class).toggle();
        }

        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(Frame frame : frames){
            frame.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;

    }
}
