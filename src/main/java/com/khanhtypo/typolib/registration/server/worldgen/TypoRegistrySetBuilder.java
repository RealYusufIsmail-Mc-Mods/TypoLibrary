package com.khanhtypo.typolib.registration.server.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An extended version of {@link net.minecraft.core.RegistrySetBuilder} but with mod id stored.
 */
public class TypoRegistrySetBuilder extends RegistrySetBuilder {
    private final String modId;

    public TypoRegistrySetBuilder(String modId) {
        this.modId = modId;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> addFeatureConfigure(String configName, Supplier<ConfiguredFeature<?, ?>> supplier) {
        ResourceKey<ConfiguredFeature<?, ?>> resourceKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(modId, configName));
        super.add(Registries.CONFIGURED_FEATURE, bootstrap -> bootstrap.register(resourceKey, supplier.get()));
        return resourceKey;
    }

    public ResourceKey<PlacedFeature> addPlacedFeature(String featureName, ResourceKey<ConfiguredFeature<?, ?>> configName, Function<Holder<ConfiguredFeature<?, ?>>, PlacedFeature> supplier) {
        ResourceKey<PlacedFeature> resourceKey = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(modId, featureName));
        super.add(Registries.PLACED_FEATURE, bootstrap -> {
            HolderGetter<ConfiguredFeature<?, ?>> getter = bootstrap.lookup(Registries.CONFIGURED_FEATURE);
            bootstrap.register(resourceKey, supplier.apply(getter.getOrThrow(configName)));
        });
        return resourceKey;
    }

    public ResourceKey<Biome> addBiome(String biomeName, Supplier<Biome> biomeSupplier) {
        ResourceKey<Biome> resourceKey = ResourceKey.create(Registries.BIOME, new ResourceLocation(modId, biomeName));
        super.add(Registries.BIOME, bootstrap -> bootstrap.register(resourceKey, biomeSupplier.get()));
        return resourceKey;
    }
}