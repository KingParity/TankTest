package com.kingparity.tanktest.tank;

import com.kingparity.tanktest.init.TankTestTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TankTile extends TileEntity
{
    private static final int MAX_CONTENTS = 10000;       // 10 buckets
    
    private FluidTank tank = new FluidTank(MAX_CONTENTS)
    {
        @Override
        protected void onContentsChanged()
        {
            BlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            markDirty();
        }
    };
    
    private final LazyOptional<IFluidHandler> holder = LazyOptional.of(() -> tank);
    
    public TankTile()
    {
        super(TankTestTileEntities.TANK);
    }
    
    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT nbtTag = super.getUpdateTag();
        CompoundNBT tankNBT = new CompoundNBT();
        tank.writeToNBT(tankNBT);
        nbtTag.put("tank", tankNBT);
        return nbtTag;
    }
    
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }
    
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
    {
        tank.readFromNBT(packet.getNbtCompound().getCompound("tank"));
    }
    
    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        tank.readFromNBT(compound.getCompound("tank"));
    }
    
    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        CompoundNBT tankNBT = new CompoundNBT();
        tank.writeToNBT(tankNBT);
        compound.put("tank", tankNBT);
        return super.write(compound);
    }
    
    public FluidTank getTank()
    {
        return this.tank;
    }
    
    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing)
    {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return holder.cast();
        }
        return super.getCapability(capability, facing);
    }
}
