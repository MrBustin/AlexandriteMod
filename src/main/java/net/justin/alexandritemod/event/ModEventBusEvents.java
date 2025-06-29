package net.justin.alexandritemod.event;


import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.entity.client.BeaverModel;
import net.justin.alexandritemod.entity.client.LumberJackModel;
import net.justin.alexandritemod.entity.client.TomahawkProjectileModel;
import net.justin.alexandritemod.entity.client.TriceratopsModel;
import net.justin.alexandritemod.entity.custom.BeaverEntity;
import net.justin.alexandritemod.entity.custom.LumberJackEntity;
import net.justin.alexandritemod.entity.custom.TriceratopsEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlexandriteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(TriceratopsModel.LAYER_LOCATION, TriceratopsModel::createBodyLayer);
        event.registerLayerDefinition(BeaverModel.LAYER_LOCATION, BeaverModel::createBodyLayer);
        event.registerLayerDefinition(TomahawkProjectileModel.LAYER_LOCATION, TomahawkProjectileModel::createBodyLayer);
        event.registerLayerDefinition(LumberJackModel.LAYER_LOCATION, LumberJackModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
        event.put(ModEntities.BEAVER.get(), BeaverEntity.createAttributes().build());
        event.put(ModEntities.LUMBER_JACK.get(), LumberJackEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
        event.register(ModEntities.TRICERATOPS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
