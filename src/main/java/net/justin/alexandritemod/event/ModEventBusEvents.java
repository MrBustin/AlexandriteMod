package net.justin.alexandritemod.event;


import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.entity.client.TriceratopsModel;
import net.justin.alexandritemod.entity.custom.TriceratopsEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlexandriteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(TriceratopsModel.LAYER_LOCATION, TriceratopsModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
    }
}
