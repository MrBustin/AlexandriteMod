package net.justin.alexandritemod.common;

import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlexandriteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TransmogTooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if (!stack.isEmpty() && stack.getItem() == ModItems.ALEXANDRITE_SWORD.get()) {
            Float indexFloat = stack.get(ModDataComponents.CUSTOM_MODEL_INDEX.get());
            if (indexFloat != null) {
                int index = (int) indexFloat.floatValue();
                ResourceLocation modelId = TransmogRegistry.getTransmogByIndex(ModItems.ALEXANDRITE_SWORD.get(), index);
                if (modelId != null) {
                    String display = modelId.getPath().replace("_", " ");
                    event.getToolTip().add(Component.literal("Model: ")
                            .append(Component.literal(display).withStyle(ChatFormatting.DARK_PURPLE)));
                }
            }
        }
    }
}
