package store.scriptbenio.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class SoundUtil {

    public static void playSound(String soundName) {

        ResourceLocation soundLocation = new ResourceLocation("minecraft", soundName);

        Minecraft mc = Minecraft.getMinecraft();

        mc.getSoundHandler().playSound(PositionedSoundRecord.create(soundLocation, 1.0F));
    }

}
