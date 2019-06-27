package com.kingparity.tanktest.proxy;

public interface IProxy
{
    default void setup()
    {
    }
    
    default void clientRegistries()
    {
    }
    
    boolean isSinglePlayer();
    
    boolean isDedicatedServer();
}
