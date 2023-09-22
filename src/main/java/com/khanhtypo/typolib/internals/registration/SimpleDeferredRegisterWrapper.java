package com.khanhtypo.typolib.internals.registration;

import com.khanhtypo.typolib.TypoLibrary;
import com.khanhtypo.typolib.api.registration.IRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SimpleDeferredRegisterWrapper<T> implements IRegistry<T> {
    private final DeferredRegister<T> deferredRegister;
    private final ResourceKey<? extends Registry<T>> registryKey;

    private SimpleDeferredRegisterWrapper(DeferredRegister<T> deferredRegister, ResourceKey<? extends Registry<T>> registryKey) {
        this.deferredRegister = deferredRegister;
        this.registryKey = registryKey;
        this.deferredRegister.register(TypoLibrary.MOD_EVENT_BUS);
    }

    SimpleDeferredRegisterWrapper(String modid, ResourceKey<? extends Registry<T>> registryKey) {
        this(
                DeferredRegister.create(registryKey, modid),
                registryKey
        );
    }

    @Override
    public <A extends T> ObjectSupplier<A> register(String id, Supplier<A> supplier) {
        return new ObjectSupplier<>(this.deferredRegister.register(id, supplier));
    }

    @Override
    public ResourceKey<? extends Registry<T>> getRegistryKey() {
        return this.registryKey;
    }
}
