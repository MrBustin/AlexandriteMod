package net.justin.alexandritemod.network;

import net.justin.alexandritemod.screen.custom.TransmogTableMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class TransmogSelectPacket {
    private final int selectedIndex;

    public TransmogSelectPacket(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public static void encode(TransmogSelectPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.selectedIndex);
    }

    public TransmogSelectPacket (FriendlyByteBuf buf) {
        this.selectedIndex = buf.readInt();
    }

    public static void handle(TransmogSelectPacket msg, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.containerMenu instanceof TransmogTableMenu menu) {
                menu.setPreviewSelection(msg.selectedIndex); // Sync selection server-side ✅
            }
        });
        context.setPacketHandled(true);
    }
}
