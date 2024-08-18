package net.vector.weaponseffect.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.vector.weaponseffect.WeaponsEffect;
import net.vector.weaponseffect.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_IGNITHRA_ORE_KEY = registerKey("ignithra_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ANTRACITE_ORE_KEY = registerKey("nether_antracite_ores");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_STONE_CELESTINE_ORE_KEY = registerKey("end_stone_celestine_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_STONE_ZENITHRA_ORE_KEY = registerKey("end_stone_zenithra_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_NEXALITE_ORE_KEY = registerKey("deepslate_nexalite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_ASTRALITE_ORE_KEY = registerKey("deepslate_astralite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_IGNITHRA_ORE_KEY = registerKey("deepslate_ignithra_ore");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>>context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new TagMatchTest(Tags.Blocks.NETHERRACK);
        RuleTest endReplaceables = new TagMatchTest(Tags.Blocks.END_STONES);


        List<OreConfiguration.TargetBlockState> overworldIgnithraOres = List.of(OreConfiguration.target(stoneReplaceables,
                ModBlocks.IGNITHRA_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_IGNITHRA_ORE.get().defaultBlockState()));

        //OVERWORLD ORES
        register(context, OVERWORLD_IGNITHRA_ORE_KEY, Feature.ORE, new OreConfiguration(overworldIgnithraOres, 5));//vein size
        register(context, OVERWORLD_DEEPSLATE_ASTRALITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldIgnithraOres, 6));
        register(context, OVERWORLD_DEEPSLATE_IGNITHRA_ORE_KEY, Feature.ORE, new OreConfiguration(overworldIgnithraOres, 5));
        register(context, OVERWORLD_DEEPSLATE_NEXALITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldIgnithraOres, 6));

        //NETHER ORES
        register(context, NETHER_ANTRACITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHER_ANTRACITE_ORE.get().defaultBlockState(),4));//vein size

        //END ORES
        register(context, END_STONE_CELESTINE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_STONE_CELESTINE_ORE.get().defaultBlockState(),4));//vein size
        register(context, END_STONE_ZENITHRA_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.END_STONE_ZENITHRA_ORE.get().defaultBlockState(),4));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(WeaponsEffect.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> Key, F feature, FC configuration) {
        context.register(Key, new ConfiguredFeature<>(feature, configuration));
    }
}
