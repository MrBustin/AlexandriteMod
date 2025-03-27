package net.justin.alexandritemod.network;

import net.justin.alexandritemod.block.entity.custom.TransmogTableBlockEntity;
import net.justin.alexandritemod.screen.custom.TransmogTableMenu;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static com.ibm.icu.impl.CurrencyData.provider;

public class TransmogSelectPacket {
    private ResourceLocation transmogId;


    public TransmogSelectPacket(ResourceLocation transmogId) {
        this.transmogId = transmogId;
    }

    public TransmogSelectPacket(FriendlyByteBuf buffer) {
        this.transmogId = buffer.readResourceLocation();

    }


    // Encode the packet data into the PacketBuffer
    public void encode(FriendlyByteBuf buffer) {
      buffer.writeResourceLocation(transmogId);


    }


    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            if (player != null) {
                player.sendSystemMessage(Component.literal("Received Transmog for: " + transmogId));
            }

        });
        }
}

