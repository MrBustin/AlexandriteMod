package net.justin.alexandritemod;

import com.mojang.logging.LogUtils;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.block.entity.ModBlockEntities;
import net.justin.alexandritemod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.justin.alexandritemod.enchantment.ModEnchantmentEffects;
import net.justin.alexandritemod.entity.ModEntities;
import net.justin.alexandritemod.entity.client.BeaverRenderer;
import net.justin.alexandritemod.entity.client.ChairRenderer;
import net.justin.alexandritemod.entity.client.TomahawkProjectileRenderer;
import net.justin.alexandritemod.entity.client.TriceratopsRenderer;
import net.justin.alexandritemod.item.ModCreativeModeTabs;
import net.justin.alexandritemod.item.ModItems;
import net.justin.alexandritemod.sound.ModSounds;
import net.justin.alexandritemod.util.ModItemProperties;
import net.justin.alexandritemod.util.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AlexandriteMod.MOD_ID)
public class AlexandriteMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "alexandritemod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public AlexandriteMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModSounds.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);



        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()-> {
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI.get(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.BLUEBERRY.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI_SEEDS.get(), 0.3f);
        });

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ALEXANDRITE);
            event.accept(ModItems.RAW_ALEXANDRITE);
        }


        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.ALEXANDRITE_BLOCK);
            event.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BAR_BRAWL_MUSIC_DISK);
            event.accept(ModItems.UP_MUSIC_DISK);
        }

        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.TRICERATOPS_SPAWN_EGG);
            event.accept(ModItems.BEAVER_SPAWN_EGG);
        }

        //if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){

        //}


    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.WALNUT);

            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.TRICERATOPS.get(), TriceratopsRenderer::new);
            EntityRenderers.register(ModEntities.BEAVER.get(), BeaverRenderer::new);
            EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);
            EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);

        }
    }
}
