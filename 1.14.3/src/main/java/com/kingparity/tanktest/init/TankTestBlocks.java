package com.kingparity.tanktest.init;

import com.kingparity.tanktest.Reference;
import com.kingparity.tanktest.tank.TankBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TankTestBlocks
{
    private static final List<Block> BLOCKS = new ArrayList<>();
    private static final List<Item> ITEMS = new ArrayList<>();
    
    public static Block TANK;
    
    static
    {
        register(TANK = new TankBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 10.0F)), "tank");
    }
    
    private static void register(Block block, String name)
    {
        register(block, new Item.Properties(), name);
    }
    
    private static void register(Block block, Item.Properties properties, String name)
    {
        register(block, new BlockItem(block, properties.group(ItemGroup.MISC)), name);
    }
    
    private static void register(Block block, BlockItem item, String name)
    {
        block.setRegistryName(name);
        BLOCKS.add(block);
        if(block.getRegistryName() != null)
        {
            item.setRegistryName(name);
            ITEMS.add(item);
        }
    }
    
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        BLOCKS.forEach(block -> event.getRegistry().register(block));
        BLOCKS.clear();
    }
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();
    }
}
