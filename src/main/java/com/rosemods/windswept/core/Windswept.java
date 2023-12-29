package com.rosemods.windswept.core;

import com.rosemods.windswept.core.data.client.WindsweptLangProvider;
import com.rosemods.windswept.core.data.client.WindsweptModelProvider;
import com.rosemods.windswept.core.data.client.WindsweptSoundProvider;
import com.rosemods.windswept.core.data.server.WindsweptLootTableProvider;
import com.rosemods.windswept.core.data.server.WindsweptRecipeProvider;
import com.rosemods.windswept.core.data.server.WindsweptStructureRepaletterProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptAdvancementModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptBiomeModifier;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptLootModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptModdedBiomeSliceProvider;
import com.rosemods.windswept.core.data.server.tags.*;
import com.rosemods.windswept.core.other.*;
import com.rosemods.windswept.core.registry.*;
import com.rosemods.windswept.core.registry.util.EffectSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Windswept.MOD_ID)
public class Windswept {
    public static final String MOD_ID = "windswept";
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, h -> h.putSubHelper(ForgeRegistries.MOB_EFFECTS, new EffectSubRegistryHelper(h)));

    public Windswept() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        WindsweptDataProcessors.registerData();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(bus);
        WindsweptTreeDecorators.DECORATORS.register(bus);
        WindsweptFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        WindsweptFeatures.FEATURES.register(bus);
        WindsweptFeatures.ConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        WindsweptFeatures.Placements.PLACED_FEATURES.register(bus);
        WindsweptEnchantments.ENCHANTMENTS.register(bus);
        WindsweptAttributes.ATTRIBUTES.register(bus);
        WindsweptBannerPatterns.BANNER_PATTERNS.register(bus);
        WindsweptTrunkPlacers.TRUNK_PLACERS.register(bus);
        WindsweptPaintingVariants.PAINTING_VARIANTS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        context.registerConfig(ModConfig.Type.COMMON, WindsweptConfig.COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, WindsweptConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WindsweptVillagerTypes.registerVillagerTypes();
            WindsweptBlockInfo.changeLocalisation();
            WindsweptBlockInfo.registerCompostables();
            WindsweptBlockInfo.registerFlammables();
            WindsweptEffects.registerPotionRecipes();
            WindsweptDispenseBehaviors.registerDispenseBehaviors();
            WindsweptCauldronInteractions.registerCauldronInteractions();
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(WindsweptEntityTypes::registerClient);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        boolean client = event.includeClient();
        gen.addProvider(client, new WindsweptSoundProvider(event));
        gen.addProvider(client, new WindsweptLangProvider(event));
        gen.addProvider(client, new WindsweptModelProvider(event));

        boolean server = event.includeServer();
        var blockTags = new WindsweptBlockTagProvider(event);
        gen.addProvider(server, blockTags);
        gen.addProvider(server, new WindsweptItemTagProvider(event, blockTags));
        gen.addProvider(server, new WindsweptEntityTagProvider(event));
        gen.addProvider(server, new WindsweptBiomeTagProvider(event));
        gen.addProvider(server, new WindsweptBannerPatternTagProvider(event));
        gen.addProvider(server, new WindsweptLootTableProvider(event));
        gen.addProvider(server, new WindsweptRecipeProvider(event));
        gen.addProvider(server, new WindsweptAdvancementModifierProvider(event));
        gen.addProvider(server, new WindsweptLootModifierProvider(event));
        gen.addProvider(server, new WindsweptStructureRepaletterProvider(event));
        gen.addProvider(server, new WindsweptModdedBiomeSliceProvider(event));
        gen.addProvider(server, new WindsweptPaintingVariantTagsProvider(event));
        gen.addProvider(server, WindsweptBiomeModifier.register(event));
    }

    public static ResourceLocation location(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

}
