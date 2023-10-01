package com.khanhtypo.typolib.utils;

import com.google.common.base.Preconditions;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

/**
 * Utility/Helper class that is used to get necessary id/name of objects without the deprecation of vanilla's {@link net.minecraft.core.registries.BuiltInRegistries}.
 * This class will not use the {@link net.minecraft.core.registries.BuiltInRegistries} but the {@link net.minecraft.resources.ResourceKey} or the vanilla registries in {@link net.minecraft.core.registries.Registries}
 * <p>
 * Please note that this class is only used for VANILLA REGISTRIES.
 *
 * @see net.minecraft.core.registries.Registries
 * @see net.minecraft.core.registries.BuiltInRegistries#REGISTRY
 */

@SuppressWarnings("unchecked")
public class VanillaRegistryHelper {
    private VanillaRegistryHelper() {
    }

    public static <T> int getIntIdFromName(ResourceKey<Registry<T>> registryKey, ResourceLocation name) {
        return getIntIdFromObject(registryKey, getObjectFromName(registryKey, name));
    }

    public static <T> T getObjectFromIntId(ResourceKey<Registry<T>> registryKey, int intId) {
        return getRegistry(registryKey).getHolder(intId).orElseThrow(() -> new IllegalStateException("there is not object represent for id " + intId + " in " + registryKey.location())).get();
    }

    public static <T> int getIntIdFromObject(ResourceKey<Registry<T>> registryKey, T object) {
        return getRegistry(registryKey).getId(object);
    }

    public static <T> T getObjectFromName(ResourceKey<Registry<T>> registryKey, ResourceLocation name) {
        return getObjectOptionalFromName(registryKey, name).orElseThrow(() -> new IllegalStateException("There is no object present for registry " + registryKey.location()));
    }

    public static <T> Optional<T> getObjectOptionalFromName(ResourceKey<Registry<T>> registryKey, ResourceLocation name) {
        return getRegistry(registryKey).getOptional(name);
    }

    public static <T> ResourceLocation getNameFromObject(ResourceKey<Registry<T>> registryKey, T object) {
        return getRegistry(registryKey).getKey(object);
    }

    public static <T> Registry<T> getRegistry(ResourceKey<Registry<T>> registryKey) {
        Preconditions.checkState(registryKey.registry() == BuiltInRegistries.ROOT_REGISTRY_NAME, registryKey + " must be a vanilla registry key. You passed a ResourceKey with type " + registryKey.registry() + " but " + BuiltInRegistries.ROOT_REGISTRY_NAME + " is required");
        return (Registry<T>) BuiltInRegistries.REGISTRY.getOptional(registryKey.location()).orElseThrow(() -> new IllegalStateException(registryKey + " is not present in the game registry"));
    }
}
