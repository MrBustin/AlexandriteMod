package net.justin.alexandritemod.datagen;
import com.jcraft.jorbis.Block;
import net.justin.alexandritemod.AlexandriteMod;
import net.justin.alexandritemod.block.ModBlocks;
import net.justin.alexandritemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AlexandriteMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_ALEXANDRITE.get());

        basicItem(ModItems.CHISEL.get());


    }
}