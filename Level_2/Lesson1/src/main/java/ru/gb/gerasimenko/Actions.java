package ru.gb.gerasimenko;

public interface Actions extends Running, Jumping{
    public void setActive(boolean flag);
    public boolean isActive();
    public String getName();
    public int getMaxRun();
    public int getMaxJump();
}
