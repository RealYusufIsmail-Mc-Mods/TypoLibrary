package com.khanhtypo.typolib.data;

import com.khanhtypo.typolib.utils.TextUtils;
import com.khanhtypo.typolib.utils.VanillaRegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class TypoLanguageProvider extends LanguageProvider {
    public TypoLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    protected final void addItemTab(Supplier<CreativeModeTab> tab, String name) {
        super.add(
                VanillaRegistryHelper.getNameFromObject(Registries.CREATIVE_MODE_TAB, tab.get()).toLanguageKey("itemGroup"),
                name
        );
    }

    protected final void addBiome(ResourceKey<Biome> biomeResourceKey) {
        addBiome(biomeResourceKey, TextUtils.transferKeyToName(biomeResourceKey));
    }

    protected final void addBiome(ResourceKey<Biome> biomeResourceKey, String name) {
        super.add(biomeResourceKey.location().toLanguageKey("biome"), name);
    }

    protected final void add(Component component, String name) {
        if (component.getContents() instanceof TranslatableContents translatableContents) {
            super.add(translatableContents.getKey(), name);
        }
    }
}
