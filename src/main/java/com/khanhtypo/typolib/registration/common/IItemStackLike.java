package com.khanhtypo.typolib.registration.common;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public interface IItemStackLike extends ItemLike {
    default ItemStack toStack() {
        return toStack(1);
    }

    default ItemStack toStack(int count) {
        return new ItemStack(this.asItem(), count);
    }
}
