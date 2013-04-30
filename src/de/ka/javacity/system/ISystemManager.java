package de.ka.javacity.system;

public interface ISystemManager {
	public void addSystem(ISystem system);
	public void removeSystem(ISystem system);
	public void updateSystems();
	public ISystem getSystem(String name);
}
