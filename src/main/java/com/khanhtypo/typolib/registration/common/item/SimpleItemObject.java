package com.khanhtypo.typolib.registration.common.item;

import com.khanhtypo.typolib.registration.common.itemtab.ItemGroup;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class SimpleItemObject extends ItemObject<Item> {
    protected SimpleItemObject(ItemRegister itemRegister, String name, Item.Properties itemProperties, @Nullable ItemGroup itemGroup) {
        super(itemRegister, name, () -> new Item(itemProperties), itemGroup);
    }
}
