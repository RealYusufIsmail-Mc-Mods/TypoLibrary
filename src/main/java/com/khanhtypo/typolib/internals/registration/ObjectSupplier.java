package com.khanhtypo.typolib.internals.registration;

import com.khanhtypo.typolib.api.registration.IObjectSupplier;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ObjectSupplier<T> implements IObjectSupplier<T> {
    private final RegistryObject<T> registryObject;

    public ObjectSupplier(RegistryObject<T> registryObject) {
        this.registryObject = registryObject;
    }

    @Override
    public T get() {
        return this.registryObject.get();
    }
}
