package com.khanhtypo.typolib.registration.common;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class ObjectSupplier<TYPE, T extends TYPE> implements Supplier<T> {
    protected final RegistryObject<T> registryObject;
    private final ResourceKey<? extends Registry<TYPE>> registry;

    public ObjectSupplier(DeferredRegisterWrapper<TYPE> deferredRegisterWrapper, String name, Supplier<T> supplier) {
        this.registryObject = deferredRegisterWrapper.registerInternal(name, supplier);
        this.registry = deferredRegisterWrapper.deferredRegister.getRegistryKey();
    }

    @Override
    public final T get() {
        return this.registryObject.get();
    }

    public final ResourceLocation getId() {
        return this.registryObject.getId();
    }

    public ResourceKey<? extends Registry<TYPE>> getRegistry() {
        return this.registry;
    }
}
