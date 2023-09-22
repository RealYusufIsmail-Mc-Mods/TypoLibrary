package com.khanhtypo.typolib.internals.registration;

import com.khanhtypo.typolib.api.registration.IRegistry;
import com.khanhtypo.typolib.api.registration.IRegistryManager;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class SimpleRegistryManager implements IRegistryManager {
    private final String modid;

    public SimpleRegistryManager(String modid) {
        this.modid = modid;
    }

    @Override
    public <T> IRegistry<T> createRegistry(ResourceKey<? extends Registry<T>> vanillaRegistryLocation) {
        return new SimpleDeferredRegisterWrapper<>(this.modid, vanillaRegistryLocation);
    }
}
