package com.khanhtypo.typolib.registration.common;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.ApiStatus;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Supplier;

public abstract class DeferredRegisterWrapper<T> {
    protected final DeferredRegister<T> deferredRegister;
    public final String modId;

    public DeferredRegisterWrapper(IForgeRegistry<T> forgeRegistry, String modId) {
        this.modId = modId;
        this.deferredRegister = DeferredRegister.create(forgeRegistry, modId);
        this.deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @ApiStatus.Internal
    public <A extends T> RegistryObject<A> registerInternal(String name, Supplier<A> supplier) {
        return this.deferredRegister.register(name, supplier);
    }
    protected <A extends ObjectSupplier<T, ?>> SortedSet<A> createSet() {
        return new TreeSet<>(Comparator.comparing(ObjectSupplier::getId));
    }
}
