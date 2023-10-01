package com.khanhtypo.typolib.registration.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class ObjectSupplier<TYPE, T extends TYPE> implements Supplier<T> {
    protected final RegistryObject<T> registryObject;

    public ObjectSupplier(DeferredRegisterWrapper<TYPE> deferredRegisterWrapper, String name, Supplier<T> supplier) {
        this.registryObject = deferredRegisterWrapper.registerInternal(name, supplier);
    }

    @Override
    public final T get() {
        return this.registryObject.get();
    }

    public final ResourceLocation getId() {
        return this.registryObject.getId();
    }
}
