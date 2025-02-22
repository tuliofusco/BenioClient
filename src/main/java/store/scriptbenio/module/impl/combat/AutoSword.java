package store.scriptbenio.module.impl.combat;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import store.scriptbenio.event.impl.update.EventUpdate;
import store.scriptbenio.module.Category;
import store.scriptbenio.module.Module;
import store.scriptbenio.module.ModuleInfo;

@ModuleInfo(
        name = "AutoSword",
        description = "Automatically equips a sword in combat",
        category = Category.Combat
)
public class AutoSword extends Module {
    public AutoSword () {
        // AUTOSWORD KEY BIND
        setKey(Keyboard.KEY_X);
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
        getBestWeapon(null);
    });

    public void getBestWeapon(Entity entity) {
        float damageModifier = 0;
        int newItem = -1;

        for (int slot = 0; slot < 9; slot++) {
            ItemStack stack =  mc.thePlayer.inventory.mainInventory[slot];
            if (stack == null) continue;

            if (stack.getItem() instanceof ItemSword) {
                ItemSword sword = (ItemSword) stack.getItem();
                float damage = sword.getMaxDamage() + (sword.hasEffect(stack) ? getEnchantDamageVsEntity(stack, entity) : 0);
                if (damage > damageModifier) {
                    newItem = slot;
                    damageModifier = damage;
                }
            }
        }

        if (newItem > -1) {
            mc.thePlayer.inventory.currentItem = newItem;
        }
    }

    public int getEnchantDamageVsEntity(ItemStack itemStack, Entity entity) {
        if (entity instanceof EntityZombie || entity instanceof EntityPigZombie || entity instanceof EntitySkeleton) {
            return EnchantmentHelper.getEnchantmentLevel
                    (Enchantment.sharpness.effectId, itemStack) + EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, itemStack);
        } else if (entity instanceof EntitySpider) {
            return EnchantmentHelper.getEnchantmentLevel
                    (Enchantment.sharpness.effectId, itemStack) + EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, itemStack);
        } else {
            return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, itemStack);
        }
    }
}
