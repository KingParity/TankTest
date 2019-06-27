package com.kingparity.tanktest.proxy;

import com.kingparity.tanktest.tank.TankTESR;
import com.kingparity.tanktest.tank.TankTile;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy
{
    @Override
    public void clientRegistries()
    {
        MinecraftForge.EVENT_BUS.register(this);
        ClientRegistry.bindTileEntitySpecialRenderer(TankTile.class, new TankTESR());
    }
    
    @Override
    public boolean isSinglePlayer()
    {
        return Minecraft.getInstance().isSingleplayer();
    }
    
    @Override
    public boolean isDedicatedServer()
    {
        return false;
    }
}
