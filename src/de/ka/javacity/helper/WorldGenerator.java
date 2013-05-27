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
		int reduced = 0;
		
		for (int x=0; x<this.size; x++) {
			for (int z=0; z<this.size; z++) {
				for (int y=0; y <= this.map[x][z]; y++) {
					
					// reduce! :D
					int current_height = y;
					if (x > 0 && x < this.size - 1 &&
						z > 0 && z < this.size -1) {
						if (map[x+1][z] > y &&
							map[x-1][z] > y	&&
							map[x][z+1] > y &&
							map[x][z-1] > y) {
							reduced++;
							continue;
						}
					}
					
					
					if (current_height < 0) current_height = 0;
					if (current_height >= this.height) current_height = height - 1;
					
					// Waterlevel
					if (y < this.waterlevel) {
						world[x][current_height][z] = BoxType.WATER;
						water++;
						continue;
					}
					
					// Soillevel
					if (y < this.soillevel) {
						world[x][current_height][z] = BoxType.SOIL;
						soil++;
						continue;
					}

					// Grasslevel
					if (y < this.grasslevel) {
						world[x][current_height][z] = BoxType.GRASS;
						grass++;
						continue;
					}
					
					// Rocklevel
					if (y >= this.grasslevel) {
						world[x][current_height][z] = BoxType.ROCK;
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
		System.out.println("Reduced: "+reduced);
		
		return world;
	}
	
}
