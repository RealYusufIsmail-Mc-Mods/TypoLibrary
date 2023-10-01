package com.khanhtypo.typolib.registration.common.block;

import com.khanhtypo.typolib.registration.common.DeferredRegisterWrapper;
import com.khanhtypo.typolib.registration.common.item.ItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BlockRegister extends DeferredRegisterWrapper<Block> {
    final Set<BlockObject<?>> allBlockObjects;
    final ItemRegister itemRegister;

    public BlockRegister(ItemRegister itemRegister) {
        super(ForgeRegistries.BLOCKS, itemRegister.modId);
        this.itemRegister = itemRegister;
        this.allBlockObjects = super.createSet();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerToItem);
    }

    /**
     * @param name          id of the block
     * @param blockSupplier block constructor
     */
    public <B extends Block> BlockObject<B> register(String name, Supplier<B> blockSupplier) {
        return new BlockObject<>(this, name, blockSupplier);
    }

    /**
     * Register a simple {@link Block} instance
     *
     * @param name             the id of the block
     * @param otherBlockObject the modded block that is safely be copied to {@link BlockBehaviour}. Either passing modded block or vanilla block will work.
     */
    public SimpleBlockObject registerSimple(String name, Supplier<? extends Block> otherBlockObject) {
        return new SimpleBlockObject(this, this.itemRegister, name, () -> new Block(BlockBehaviour.Properties.copy(otherBlockObject.get())));
    }

    /**
     * Register a simple {@link Block} instance
     *
     * @param name             the id of the block
     * @param otherBlockObject the block that is safely be copied to {@link BlockBehaviour.Properties}. Either passing modded block or vanilla block will work.
     * @param extraSettings    additional behaviour tweaks
     */
    public SimpleBlockObject registerSimple(String name, Supplier<? extends Block> otherBlockObject, Consumer<BlockBehaviour.Properties> extraSettings) {
        return new SimpleBlockObject(this, this.itemRegister, name, () -> {
            BlockBehaviour.Properties blockBehaviour = BlockBehaviour.Properties.copy(otherBlockObject.get());
            extraSettings.accept(blockBehaviour);
            return new Block(blockBehaviour);
        });
    }

    /**
     * Register a simple {@link Block} instance
     * CAUTION : DO NOT USE MODDED BLOCKS FOR {@link BlockBehaviour.Properties#copy(BlockBehaviour)}
     *
     * @param name       the id of the block
     * @param properties properties of the given block
     */
    public SimpleBlockObject registerSimple(String name, BlockBehaviour.Properties properties) {
        return new SimpleBlockObject(this, this.itemRegister, name, () -> new Block(properties));
    }

    public Stream<BlockObject<?>> getEntries() {
        return this.allBlockObjects.stream();
    }

    private void registerToItem(RegisterEvent registerEvent) {
        registerEvent.register(Registries.ITEM, helper ->
                this.getEntries().filter(BlockObject::hasItem).forEachOrdered(blockObject -> {
                    Item.Properties itemProperties = blockObject.itemProperties == null ? new Item.Properties() : blockObject.itemProperties;
                    BlockItem blockItem = blockObject.customItemConstructor == null ? new BlockItem(blockObject.get(), itemProperties) : blockObject.customItemConstructor.apply(itemProperties);
                    helper.register(blockObject.getId(), blockItem);
                })
        );
    }
}
