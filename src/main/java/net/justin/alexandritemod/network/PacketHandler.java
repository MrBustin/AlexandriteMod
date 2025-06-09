package net.justin.alexandritemod.network;

import net.justin.alexandritemod.AlexandriteMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketHandler {
    public static final SimpleChannel INSTANCE = ChannelBuilder.named(
            ResourceLocation.fromNamespaceAndPath(AlexandriteMod.MOD_ID, "main"))
            .serverAcceptedVersions((status, version) -> true)
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(ApplyTransmogPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ApplyTransmogPacket::encode)
                .decoder(ApplyTransmogPacket::new)
                .consumerMainThread(ApplyTransmogPacket::handle)
                .add();

        INSTANCE.messageBuilder(TransmogSelectPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(TransmogSelectPacket::encode)
                .decoder(TransmogSelectPacket::new)
                .consumerMainThread(TransmogSelectPacket::handle)
                .add();


    }


    public static void sendToServer(Object msg) {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToAllClients(Object msg){
        INSTANCE.send(msg, PacketDistributor.ALL.noArg());
    }

}
