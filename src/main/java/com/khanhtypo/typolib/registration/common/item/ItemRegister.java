package com.khanhtypo.typolib.registration.common.item;

import com.khanhtypo.typolib.registration.common.DeferredRegisterWrapper;
import com.khanhtypo.typolib.registration.common.itemtab.TypoItemGroup;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class ItemRegister extends DeferredRegisterWrapper<Item> {
    private final Set<ItemObject<?>> allItemObjects;
    public ItemRegister(String modId) {
        super(ForgeRegistries.ITEMS, modId);
        this.allItemObjects = super.createSet();
    }

    public SimpleItemObject registerSimple(String name, Item.Properties itemProperties, @Nullable TypoItemGroup itemGroup) {
        return new SimpleItemObject(this, name, itemProperties, itemGroup);
    }

    public <T extends Item> ItemObject<T> register(String name, Item.Properties itemProperties, Function<Item.Properties, T> itemConstructor, @Nullable TypoItemGroup itemGroup) {
        return new ItemObject<>(this, name, () -> itemConstructor.apply(itemProperties), itemGroup);
    }

    public Stream<ItemObject<?>> getEntries() {
        return this.allItemObjects.stream();
    }


}
