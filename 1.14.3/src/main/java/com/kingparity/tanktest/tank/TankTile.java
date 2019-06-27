package com.kingparity.tanktest.tank;

import com.kingparity.tanktest.init.TankTestTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.TileFluidHandler;

import javax.annotation.Nullable;

public class TankTile extends TileFluidHandler
{
    private static final int MAX_CONTENTS = 10000;       // 10 buckets
    
    public TankTile()
    {
        super(TankTestTileEntities.TANK);
        this.tank = new FluidTank(MAX_CONTENTS)
        {
            @Override
            protected void onContentsChanged()
            {
                BlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
                markDirty();
            }
        };
    }
    
    @Override
    public CompoundNBT getUpdateTag()
    {
        return write(new CompoundNBT());
    }
    
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(getPos(), 0, getUpdateTag());
    }
    
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        read(packet.getNbtCompound());
    }
    
    public FluidTank getTank()
    {
        return this.tank;
    }
}
