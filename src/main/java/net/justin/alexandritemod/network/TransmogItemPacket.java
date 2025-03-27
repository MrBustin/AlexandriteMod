package net.justin.alexandritemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class TransmogItemPacket {
    private final ItemStack stack;

    public TransmogItemPacket(ItemStack stack) {
        this.stack = stack;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, TransmogItemPacket> CODEC = new StreamCodec<>() {
        @Override
        public TransmogItemPacket decode(RegistryFriendlyByteBuf buffer) {
            ItemStack stack = buffer.readJsonWithCodec(ItemStack.CODEC);
            return new TransmogItemPacket(stack);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buffer, TransmogItemPacket packet) {

            buffer.writeJsonWithCodec(ItemStack.CODEC, packet.stack);
        }
    };

    public void handle(CustomPayloadEvent.Context context) {

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                player.sendSystemMessage(Component.literal("Received ItemStack: " + stack.getHoverName().getString()));
            }
        });
    }
}
