package com.khanhtypo.typolib.api.registration;

import com.khanhtypo.typolib.internals.registration.SimpleRegistryManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface IRegistryManager {
    static IRegistryManager forMod(String modid) {
        return new SimpleRegistryManager(modid);
    }

    <T> IRegistry<T> createRegistry(ResourceKey<? extends Registry<T>> vanillaRegistryLocation);
}
