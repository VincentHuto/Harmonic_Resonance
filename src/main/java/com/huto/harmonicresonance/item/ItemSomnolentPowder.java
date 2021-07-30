package com.huto.harmonicresonance.item;

import com.huto.harmonicresonance.entity.item.EntityManaDustItem;
import com.huto.harmonicresonance.init.EntityInit;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class ItemSomnolentPowder extends Item {

	public ItemSomnolentPowder(Properties prop) {
		super(prop);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(Level world, Entity location, ItemStack itemstack) {
		EntityManaDustItem itementity = new EntityManaDustItem(EntityInit.vibratorydust.get(), world, location.getPosX(),
				location.getPosY(), location.getPosZ(), itemstack);
		itementity.setPickupDelay(40);
		itementity.setMotion(location.getMotion());
		return itementity;
	}
}