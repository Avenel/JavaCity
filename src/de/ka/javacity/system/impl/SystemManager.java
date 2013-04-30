package de.ka.javacity.system.impl;

import java.util.HashMap;
import java.util.Map;

import de.ka.javacity.system.ISystem;
import de.ka.javacity.system.ISystemManager;

public class SystemManager implements ISystemManager{

	private Map<String, ISystem> systems;
	
	public SystemManager() {
		this.systems = new HashMap<String, ISystem>();
	}
	
	@Override
	public void addSystem(ISystem system) {
		this.systems.put(system.getClass().toString(), system);
	}

	@Override
	public void removeSystem(ISystem system) {
		this.systems.remove(system);
	}

	@Override
	public void updateSystems() {
		for (ISystem system : this.systems.values()) {
			system.update();
		}
	}

	@Override
	public ISystem getSystem(String name) {
		return this.systems.get(name);
	}

}
