package com.huto.harmonicresonance.init;

import com.huto.harmonicresonance.HarmonicResonance;
import com.huto.harmonicresonance.container.ContainerVirtuousEnchanter;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HarmonicResonance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerInit {
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, HarmonicResonance.MOD_ID);
	public static final RegistryObject<ContainerType<ContainerVirtuousEnchanter>> virtuous_enchanter = CONTAINERS
			.register("virtuous_enchanter", () -> IForgeContainerType.create(ContainerVirtuousEnchanter::new));

}
