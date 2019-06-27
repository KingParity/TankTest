package com.kingparity.tanktest.tank;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;

public class TankBlock extends Block
{
    public TankBlock(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        if(!world.isRemote)
        {
            TankTile tile = getTile(world, pos);
            if(tile != null)
            {
                FluidUtil.interactWithFluidHandler(player, hand, tile.getTank());
                System.out.println(player);
                System.out.println(hand);
                System.out.println(tile.getTank());
            }
        }
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TankTile();
    }
    
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    private TankTile getTile(World world, BlockPos pos)
    {
        return (TankTile)world.getTileEntity(pos);
    }
}
