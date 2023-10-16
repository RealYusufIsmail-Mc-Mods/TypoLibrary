package com.khanhtypo.typolib.registration.common.block;

import com.khanhtypo.typolib.registration.common.IItemStackLike;
import com.khanhtypo.typolib.registration.common.ObjectSupplier;
import com.khanhtypo.typolib.registration.common.itemtab.TypoItemGroup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockObject<T extends Block> extends ObjectSupplier<Block, T> implements IItemStackLike {
    boolean hasItem;
    @Nullable
    Item.Properties itemProperties;
    @Nullable
    Function<Item.Properties, ? extends BlockItem> customItemConstructor;
    @Nullable
    TypoItemGroup itemGroup;

    BlockObject(BlockRegister blockRegister, String name, Supplier<T> supplier) {
        super(blockRegister, name, supplier);
        this.hasItem = true;
    }

    public BlockObject<T> setItemProperties(@Nonnull Item.Properties itemProperties) {
        this.itemProperties = itemProperties;
        return this;
    }

    public <ITEM extends BlockItem> BlockObject<T> setCustomItemConstructor(@Nonnull Function<Item.Properties, ITEM> customItemConstructor) {
        this.customItemConstructor = customItemConstructor;
        return this;
    }

    public BlockObject<T> setItemGroup(@Nonnull TypoItemGroup itemGroup) {
        this.itemGroup = itemGroup;
        return this;
    }

    public BlockObject<T> noItem() {
        this.hasItem = false;
        return this;
    }

    public boolean hasItem() {
        return this.hasItem;
    }

    @Override
    public Item asItem() {
        return this.get().asItem();
    }
}
