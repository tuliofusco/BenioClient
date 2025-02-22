package store.scriptbenio.module.impl.combat;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;

@ModuleInfo(
        name = "FastBow",
        description = "Became a bow master.",
        category = Category.Combat
)
public class FastBow extends Module {

    public FastBow() {
        // FASTBOW KEY BIND
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
        if (mc.thePlayer.getItemInUse() != null && mc.thePlayer.getItemInUse().getItem() instanceof ItemBow && mc.thePlayer.getItemInUseDuration() == 16) {
            int i = 0;
            while (i < 5) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                i++;
            }

            mc.thePlayer.sendQueue
                    .addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            mc.thePlayer.stopUsingItem();
        }
    });

}
