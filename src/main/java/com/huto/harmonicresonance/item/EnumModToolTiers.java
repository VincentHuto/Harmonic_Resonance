package com.huto.harmonicresonance.item;

import java.util.function.Supplier;

import com.huto.harmonicresonance.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum EnumModToolTiers implements IItemTier {

	NULL(5, 1024, 10.0F, 5.0F, 25, () -> {
		return Ingredient.fromItems(ItemInit.shattered_ingot.get());
	}),

	MYSTIC(4, 512, 8.0F, 4.0F, 20, () -> {
		return Ingredient.fromItems(ItemInit.emanating_ingot.get());
	});
	;

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;

	private EnumModToolTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn,
			int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}
}