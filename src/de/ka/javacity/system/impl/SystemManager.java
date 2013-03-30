package de.ka.javacity.system.impl;

import java.util.ArrayList;
import java.util.List;

import de.ka.javacity.system.ISystem;
import de.ka.javacity.system.ISystemManager;

public class SystemManager implements ISystemManager{

	private List<ISystem> systems;
	
	public SystemManager() {
		this.systems = new ArrayList<ISystem>();
	}
	
	@Override
	public void addSystem(ISystem system) {
		this.systems.add(system);
	}

	@Override
	public void removeSystem(ISystem system) {
		this.systems.remove(system);
	}

	@Override
	public void updateSystems() {
		for (ISystem system : this.systems) {
			system.update();
		}
	}

}
