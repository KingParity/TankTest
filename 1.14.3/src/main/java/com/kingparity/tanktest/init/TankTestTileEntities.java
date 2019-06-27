package com.kingparity.tanktest.init;

import com.kingparity.tanktest.Reference;
import com.kingparity.tanktest.tank.TankTile;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Reference.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TankTestTileEntities
{
    private static final List<TileEntityType<?>> TILE_ENTITY_TYPES = new LinkedList<>();
    
    public static TileEntityType<TankTile> TANK;
    
    public static List<TileEntityType<?>> getTileEntityTypes()
    {
        return Collections.unmodifiableList(TILE_ENTITY_TYPES);
    }
    
    @SubscribeEvent
    public static void addTileEntityTypes(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        TANK = register("tank", TankTile::new, TankTestBlocks.TANK);
        
        TILE_ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        TILE_ENTITY_TYPES.clear();
    }
    
    private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> factory, Block... validBlocks)
    {
        TileEntityType<T> type = TileEntityType.Builder.create(factory, validBlocks).build(null);
        type.setRegistryName(new ResourceLocation(Reference.ID, name));
        TILE_ENTITY_TYPES.add(type);
        return type;
    }
}