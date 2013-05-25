package de.ka.javacity.helper;

import de.ka.javacity.component.impl.Chunk.BoxType;

public class WorldGenerator {

	private int size;
	private int height;
	private int[][] map;
	private int soillevel;
	private int waterlevel;
	private int grasslevel;
	private int rocklevel;
	
	
	public WorldGenerator(int[][] map, int size, int height, int soillevel, int waterlevel, int grasslevel, int rocklevel) {
		this.map  = map;
		this.size = size;
		this.height = height;
		this.soillevel = soillevel;
		this.waterlevel = waterlevel;
		this.grasslevel = grasslevel;
		this.rocklevel = rocklevel;
	}
	
	public BoxType[][][] generate() {
		BoxType[][][] world = new BoxType[this.size][this.height][this.size];
		
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < this.height; y++) {
				for (int z = 0; z < this.size; z++) {
					world[x][y][z] = BoxType.EMPTY;
				}
			}
		}
		
		int soil = 0;
		int water = 0;
		int grass = 0;
		int rock = 0;
		
		for (int x=0; x<this.size; x++) {
			for (int z=0; z<this.size; z++) {
				for (int y=0; y < this.map[x][z]; y++) {
					// Soillevel
					if (y < this.soillevel) {
						world[x][y][z] = BoxType.SOIL;
						soil++;
						continue;
					}

					// Waterlevel
					if (y < this.waterlevel) {
						world[x][y][z] = BoxType.WATER;
						water++;
						continue;
					}
					
					// Grasslevel
					if (y < this.grasslevel) {
						world[x][y][z] = BoxType.GRASS;
						grass++;
						continue;
					}
					
					// Rocklevel
					if (y < this.rocklevel) {
						world[x][y][z] = BoxType.ROCK;
						rock++;
						continue;
					}
				}
			}
		}
		
		System.out.println("soil: "+soil);
		System.out.println("water: "+water);
		System.out.println("grass: "+grass);
		System.out.println("rock: "+rock);
		
		return world;
	}
	
}
