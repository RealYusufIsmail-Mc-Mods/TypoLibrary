package com.khanhtypo.typolib.registration.common.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public abstract class TypoBaseMenu extends AbstractContainerMenu {
    protected final Inventory inventory;
    protected final Player player;
    protected final Level level;
    protected int inventoryX;
    protected int inventoryY;
    protected int inventoryStartIndex;
    protected int hotbarStartIndex;

    protected TypoBaseMenu(Supplier<MenuType<?>> pMenuType, int pContainerId, Inventory inventory) {
        super(pMenuType.get(), pContainerId);
        this.inventory = inventory;
        this.player = this.inventory.player;
        this.level = this.player.level();
    }

    protected final void addInventorySlots(int topLeftX, int topLeftY) {
        this.inventoryX = topLeftX;
        this.inventoryY = topLeftY;
        this.inventoryStartIndex = super.slots.size();
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(this.inventory, j + i * 9 + 9, topLeftX + j * 18, topLeftY + i * 18));
            }
        }
        this.hotbarStartIndex = super.slots.size();
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(this.inventory, k, topLeftX + k * 18, topLeftY + 58));
        }
    }

    public int getInventoryX() {
        return inventoryX;
    }

    public int getInventoryY() {
        return inventoryY;
    }
}
