package com.khanhtypo.typolib.registration.common.item;

import com.khanhtypo.typolib.registration.common.IItemStackLike;
import com.khanhtypo.typolib.registration.common.ObjectSupplier;
import com.khanhtypo.typolib.registration.common.itemtab.TypoItemGroup;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ItemObject<T extends Item> extends ObjectSupplier<Item, T> implements IItemStackLike {

    public ItemObject(ItemRegister itemRegister, String name, Supplier<T> supplier, @Nullable TypoItemGroup itemGroup) {
        super(itemRegister, name, supplier);
        if (itemGroup != null) {
            itemGroup.acceptItem(this);
        }
    }

    @Override
    public Item asItem() {
        return super.get();
    }
}
