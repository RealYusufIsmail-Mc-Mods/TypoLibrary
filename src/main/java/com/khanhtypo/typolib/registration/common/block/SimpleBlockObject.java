package com.khanhtypo.typolib.registration.common.block;

import com.khanhtypo.typolib.registration.common.item.ItemRegister;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class SimpleBlockObject extends BlockObject<Block> {
    SimpleBlockObject(BlockRegister blockRegister, ItemRegister itemRegister, String name, Supplier<Block> supplier) {
        super(blockRegister, name, supplier);
    }
}
