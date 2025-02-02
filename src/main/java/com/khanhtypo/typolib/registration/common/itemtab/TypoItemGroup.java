package com.khanhtypo.typolib.registration.common.itemtab;

import com.khanhtypo.typolib.TypoLibrary;
import com.khanhtypo.typolib.utils.VanillaRegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

public final class TypoItemGroup implements Supplier<CreativeModeTab> {
    public final ResourceLocation groupId;
    public final Component groupTitle;
    private final Supplier<? extends ItemLike> icon;
    private final Set<Supplier<ItemStack>> items;
    private boolean locked;

    private TypoItemGroup(ResourceLocation groupId, Supplier<? extends ItemLike> icon, Set<Supplier<ItemStack>> items) {
        this.groupId = groupId;
        this.icon = icon;
        this.items = items;
        this.locked = false;
        this.groupTitle = Component.translatable(this.groupId.toLanguageKey("itemGroup"));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerFromEvent);
    }

    public void acceptItem(Supplier<? extends ItemLike> itemObject) {
        if (this.locked) {
            TypoLibrary.LOGGER.warn(String.format("Can not add item (%s) to tab (%s), the tab has locked.", VanillaRegistryHelper.getNameFromObject(Registries.ITEM, itemObject.get().asItem()), this.groupId));
            return;
        }
        this.items.add(() -> itemObject.get().asItem().getDefaultInstance());
    }

    private void registerFromEvent(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, this.groupId, () -> {
            locked = true;
            return CreativeModeTab.builder()
                    .title(groupTitle)
                    .icon(() -> icon.get().asItem().getDefaultInstance())
                    .displayItems((pParameters, pOutput) -> pOutput.acceptAll(items.stream().map(Supplier::get).toList()))
                    .build();
        });
    }

    public static TypoItemGroup create(String modId, String tabId, Supplier<Supplier<? extends ItemLike>> itemLikeObject) {
        return new TypoItemGroup(new ResourceLocation(modId, tabId), itemLikeObject.get(), new TreeSet<>(Comparator.comparing(itemStackSupplier -> VanillaRegistryHelper.getNameFromObject(Registries.ITEM, itemStackSupplier.get().getItem()))));
    }

    @Override
    public CreativeModeTab get() {
        return VanillaRegistryHelper.getObjectFromName(Registries.CREATIVE_MODE_TAB, this.groupId);
    }
}
