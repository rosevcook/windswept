package com.rosemods.windswept.core.data.server.modifiers;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.WindsweptBiomes;
import com.teamabnormals.blueprint.core.registry.BlueprintBiomes;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WindsweptModdedBiomeSliceProvider {/* extends ModdedBiomeSliceProvider {

    public WindsweptModdedBiomeSliceProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID);
    }

    @Override
    protected void registerSlices() {
        List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> entries = new ArrayList<>();
        new WindsweptBiomeBuilder().addBiomesToSlice(entries::add);
        this.registerSlice("main", 4, new BiomeUtil.MultiNoiseModdedBiomeProvider(new Climate.ParameterList<>(entries)), LevelStem.OVERWORLD.location());
    }

    private static final class WindsweptBiomeBuilder {
        private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1f, 1f);
        private final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1f, -.45f), Climate.Parameter.span(-.45f, -.15f), Climate.Parameter.span(-.15f, .2f), Climate.Parameter.span(.2f, .55f), Climate.Parameter.span(.55f, 1f)};
        private final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1f, -.35f), Climate.Parameter.span(-.35f, -.1f), Climate.Parameter.span(-.1f, .1f), Climate.Parameter.span(.1f, .3f), Climate.Parameter.span(.3f, 1f)};
        private final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1f, -.78f), Climate.Parameter.span(-.78f, -.375f), Climate.Parameter.span(-.375f, -.2225f), Climate.Parameter.span(-.2225f, .05f), Climate.Parameter.span(.05f, .45f), Climate.Parameter.span(.45f, .55f), Climate.Parameter.span(.55f, 1f)};
        private final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
        private final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
        private final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2f, -1.05f);
        private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05f, -.455f);
        private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-.455f, -.19f);
        private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-.19f, -.11f);
        private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-.11f, .55f);
        private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-.11f, .03f);
        private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(.03f, .3f);
        private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(.3f, 1f);
        private final ResourceKey<Biome> VANILLA = BlueprintBiomes.ORIGINAL_SOURCE_MARKER;
        private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{{WindsweptBiomes.TUNDRA, WindsweptBiomes.TUNDRA, WindsweptBiomes.TUNDRA, WindsweptBiomes.SNOWY_CHESTNUT_FOREST, WindsweptBiomes.CHESTNUT_FOREST}, {VANILLA, VANILLA, VANILLA, WindsweptBiomes.CHESTNUT_FOREST, WindsweptBiomes.PINE_BARRENS}, {VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}, {VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}, {VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}};
        private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{{VANILLA, null, WindsweptBiomes.SNOWY_CHESTNUT_FOREST, null, null}, {null, null, null, null, WindsweptBiomes.PINE_BARRENS}, {VANILLA, null, null, WindsweptBiomes.LAVENDER_MEADOW, null}, {null, null, VANILLA, VANILLA, VANILLA}, {null, null, null, null, null}};
        private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{{WindsweptBiomes.TUNDRA, WindsweptBiomes.TUNDRA, WindsweptBiomes.TUNDRA, WindsweptBiomes.SNOWY_CHESTNUT_FOREST, WindsweptBiomes.SNOWY_CHESTNUT_FOREST}, {WindsweptBiomes.LAVENDER_MEADOW, WindsweptBiomes.LAVENDER_MEADOW, VANILLA, WindsweptBiomes.CHESTNUT_FOREST, WindsweptBiomes.PINE_BARRENS}, {WindsweptBiomes.LAVENDER_MEADOW, WindsweptBiomes.LAVENDER_MEADOW, WindsweptBiomes.LAVENDER_MEADOW, WindsweptBiomes.LAVENDER_MEADOW, VANILLA}, {VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}, {VANILLA, VANILLA, VANILLA, VANILLA, VANILLA}};
        private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{{VANILLA, null, null, null, null}, {null, null, WindsweptBiomes.LAVENDER_MEADOW, WindsweptBiomes.LAVENDER_MEADOW, VANILLA}, {null, null, VANILLA, VANILLA, null}, {null, null, null, null, null}, {VANILLA, VANILLA, null, null, null}};

        private void addBiomesToSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
            this.addOffCoastBiomes(consumer);
            this.addInlandBiomes(consumer);
            this.addUndergroundBiomes(consumer);
        }

        private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
            this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0f, VANILLA);

            for (Climate.Parameter temperature : this.temperatures) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0f, VANILLA);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0f, VANILLA);
            }
        }

        private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
            this.addMidSlice(consumer, Climate.Parameter.span(-1f, -.93333334f));
            this.addHighSlice(consumer, Climate.Parameter.span(-.93333334f, -.7666667f));
            this.addPeaks(consumer, Climate.Parameter.span(-.7666667f, -.56666666f));
            this.addHighSlice(consumer, Climate.Parameter.span(-.56666666f, -.4f));
            this.addMidSlice(consumer, Climate.Parameter.span(-.4f, -.26666668f));
            this.addLowSlice(consumer, Climate.Parameter.span(-.26666668f, -.05f));
            this.addValleys(consumer, Climate.Parameter.span(-.05f, .05f));
            this.addLowSlice(consumer, Climate.Parameter.span(.05f, .26666668f));
            this.addMidSlice(consumer, Climate.Parameter.span(.26666668f, .4f));
            this.addHighSlice(consumer, Climate.Parameter.span(.4f, .56666666f));
            this.addPeaks(consumer, Climate.Parameter.span(.56666666f, .7666667f));
            this.addHighSlice(consumer, Climate.Parameter.span(.7666667f, .93333334f));
            this.addMidSlice(consumer, Climate.Parameter.span(.93333334f, 1f));
        }

        private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
            for (int i = 0; i < this.temperatures.length; ++i) {
                Climate.Parameter temperature = this.temperatures[i];

                for (int j = 0; j < this.humidities.length; ++j) {
                    Climate.Parameter humidity = this.humidities[j];
                    ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                    ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                    ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0f, VANILLA);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0f, resourcekey2);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0f, VANILLA);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0f, resourcekey3);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0f, resourcekey1);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0f, resourcekey3);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey5);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, resourcekey);
                }
            }
        }

        private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
            for (int i = 0; i < this.temperatures.length; ++i) {
                Climate.Parameter temperature = this.temperatures[i];

                for (int j = 0; j < this.humidities.length; ++j) {
                    Climate.Parameter humidity = this.humidities[j];
                    ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                    ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                    ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, resourcekey);
                    ResourceKey<Biome> resourcekey6 = this.pickSlopeBiome(i, j, weirdness);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, this.erosions[0], weirdness, 0f, resourcekey6);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0f, VANILLA);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, this.erosions[1], weirdness, 0f, resourcekey2);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0f, resourcekey6);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0f, resourcekey3);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0f, resourcekey1);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0f, resourcekey3);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey5);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, resourcekey);
                }
            }
        }

        private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
            this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, VANILLA);

            for (int i = 0; i < this.temperatures.length; ++i) {
                Climate.Parameter temperature = this.temperatures[i];

                for (int j = 0; j < this.humidities.length; ++j) {
                    Climate.Parameter humidity = this.humidities[j];
                    ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                    ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                    ResourceKey<Biome> resourcekey3 = this.pickShatteredBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey4 = this.pickPlateauBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey6 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, resourcekey);
                    ResourceKey<Biome> resourcekey7 = this.pickShatteredCoastBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey8 = this.pickSlopeBiome(i, j, weirdness);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0f, resourcekey8);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0f, resourcekey2);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.farInlandContinentalness, this.erosions[1], weirdness, 0f, i == 0 ? resourcekey8 : resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, this.erosions[2], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.midInlandContinentalness, this.erosions[2], weirdness, 0f, resourcekey1);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.farInlandContinentalness, this.erosions[2], weirdness, 0f, resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0f, resourcekey1);
                    if (weirdness.max() < 0L) {
                        this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[4], weirdness, 0f, VANILLA);
                        this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0f, resourcekey);
                    } else {
                        this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0f, resourcekey);
                    }

                    this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0f, resourcekey7);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0f, resourcekey6);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey3);
                    if (weirdness.max() < 0L) {
                        this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0f, VANILLA);
                    } else {
                        this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0f, resourcekey);
                    }

                    if (i == 0) {
                        this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, resourcekey);
                    }
                }
            }
        }

        private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
            this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, VANILLA);

            for (int i = 0; i < this.temperatures.length; ++i) {
                Climate.Parameter temperature = this.temperatures[i];

                for (int j = 0; j < this.humidities.length; ++j) {
                    Climate.Parameter humidity = this.humidities[j];
                    ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
                    ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                    ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                    ResourceKey<Biome> resourcekey3 = VANILLA;
                    ResourceKey<Biome> resourcekey4 = this.maybePickWindsweptSavannaBiome(i, j, weirdness, resourcekey);
                    ResourceKey<Biome> resourcekey5 = this.pickShatteredCoastBiome(i, j, weirdness);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, resourcekey1);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, resourcekey2);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0f, resourcekey1);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0f, resourcekey3);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0f, resourcekey5);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0f, resourcekey4);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0f, resourcekey);
                    this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0f, resourcekey3);
                    if (i == 0) {
                        this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, resourcekey);
                    }
                }
            }
        }

        private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
            this.addSurfaceBiome(consumer, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.FROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, VANILLA);
            this.addSurfaceBiome(consumer, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0f, VANILLA);

            for (int i = 0; i < this.temperatures.length; ++i) {
                Climate.Parameter temperature = this.temperatures[i];

                for (int j = 0; j < this.humidities.length; ++j) {
                    Climate.Parameter humidity = this.humidities[j];
                    ResourceKey<Biome> resourcekey = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                    this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0f, resourcekey);
                }
            }
        }

        private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
            this.addUndergroundBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(.8f, 1f), this.FULL_RANGE, this.FULL_RANGE, 0f, VANILLA);
            this.addUndergroundBiome(consumer, this.FULL_RANGE, Climate.Parameter.span(.7f, 1f), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0f, VANILLA);
        }

        private ResourceKey<Biome> pickMiddleBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
            if (weirdness.max() < 0L) {
                return this.MIDDLE_BIOMES[temperatureIndex][humidityIndex];
            } else {
                ResourceKey<Biome> resourcekey = this.MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
                return resourcekey == null ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : resourcekey;
            }
        }

        private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int p_187192_, int p_187193_, Climate.Parameter p_187194_) {
            return p_187192_ == 4 ? VANILLA : this.pickMiddleBiome(p_187192_, p_187193_, p_187194_);
        }

        private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int p_187212_, int p_187213_, Climate.Parameter p_187214_) {
            return p_187212_ == 0 ? this.pickSlopeBiome(p_187212_, p_187213_, p_187214_) : this.pickMiddleBiomeOrBadlandsIfHot(p_187212_, p_187213_, p_187214_);
        }

        private ResourceKey<Biome> maybePickWindsweptSavannaBiome(int p_201991_, int p_201992_, Climate.Parameter p_201993_, ResourceKey<Biome> p_201994_) {
            return p_201991_ > 1 && p_201992_ < 4 && p_201993_.max() >= 0L ? VANILLA : p_201994_;
        }

        private ResourceKey<Biome> pickShatteredCoastBiome(int temperatureIndex, int humidityIndex, Climate.Parameter p_187225_) {
            ResourceKey<Biome> resourcekey = p_187225_.max() >= 0L ? this.pickMiddleBiome(temperatureIndex, humidityIndex, p_187225_) : VANILLA;
            return this.maybePickWindsweptSavannaBiome(temperatureIndex, humidityIndex, p_187225_, resourcekey);
        }

        private ResourceKey<Biome> pickPlateauBiome(int p_187234_, int p_187235_, Climate.Parameter p_187236_) {
            if (p_187236_.max() < 0L) {
                return this.PLATEAU_BIOMES[p_187234_][p_187235_];
            } else {
                ResourceKey<Biome> resourcekey = this.PLATEAU_BIOMES_VARIANT[p_187234_][p_187235_];
                return resourcekey == null ? this.PLATEAU_BIOMES[p_187234_][p_187235_] : resourcekey;
            }
        }

        private ResourceKey<Biome> pickSlopeBiome(int p_187245_, int p_187246_, Climate.Parameter p_187247_) {
            if (p_187245_ >= 3) {
                return this.pickPlateauBiome(p_187245_, p_187246_, p_187247_);
            } else {
                return WindsweptBiomes.SNOWY_PINE_BARRENS;
            }
        }

        private ResourceKey<Biome> pickShatteredBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
            return temperatureIndex > 2 ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : VANILLA;
        }

        private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187181_, Climate.Parameter p_187182_, Climate.Parameter p_187183_, Climate.Parameter p_187184_, Climate.Parameter p_187185_, Climate.Parameter p_187186_, float p_187187_, ResourceKey<Biome> p_187188_) {
            p_187181_.accept(Pair.of(Climate.parameters(p_187182_, p_187183_, p_187184_, p_187185_, Climate.Parameter.point(0f), p_187186_, p_187187_), p_187188_));
            p_187181_.accept(Pair.of(Climate.parameters(p_187182_, p_187183_, p_187184_, p_187185_, Climate.Parameter.point(1f), p_187186_, p_187187_), p_187188_));
        }

        private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187201_, Climate.Parameter p_187202_, Climate.Parameter p_187203_, Climate.Parameter p_187204_, Climate.Parameter p_187205_, Climate.Parameter p_187206_, float p_187207_, ResourceKey<Biome> p_187208_) {
            p_187201_.accept(Pair.of(Climate.parameters(p_187202_, p_187203_, p_187204_, p_187205_, Climate.Parameter.span(.2f, .9f), p_187206_, p_187207_), p_187208_));
        }

    }


*/
}
