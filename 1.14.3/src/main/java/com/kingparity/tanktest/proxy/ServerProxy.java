package com.kingparity.tanktest.proxy;

import net.minecraftforge.common.MinecraftForge;

public class ServerProxy implements IProxy
{
    @Override
    public void clientRegistries()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @Override
    public boolean isSinglePlayer()
    {
        return false;
    }
    
    @Override
    public boolean isDedicatedServer()
    {
        return true;
    }
}
