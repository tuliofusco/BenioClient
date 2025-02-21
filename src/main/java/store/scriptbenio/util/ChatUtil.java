package store.scriptbenio.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import store.scriptbenio.Benio;

public final class ChatUtil {

    public static String fix (String string) {
        return string.replace("&", "§")
                .replace(">>", "»")
                .replace("<<", "«")
                .replace("->", "→")
                .replace("<-","←");
    }

    public static void addChatMessage (final String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(fix(msg)));
    }

    public static void addChatMessage (final String msg, final boolean prefix) {
        if (prefix) {
            Minecraft.getMinecraft().thePlayer
                    .addChatMessage(new ChatComponentText(fix(Benio.INSTANCE.getClientPrefix()) + fix(msg)));
        } else {
            addChatMessage(msg);
        }
    }
}
