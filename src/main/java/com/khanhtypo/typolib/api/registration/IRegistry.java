package com.khanhtypo.typolib.api.registration;


import com.khanhtypo.typolib.internals.registration.ObjectSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface IRegistry<T> {
    <A extends T> ObjectSupplier<A> register(String id, Supplier<A> supplier);

    ResourceKey<? extends Registry<T>> getRegistryKey();
}
