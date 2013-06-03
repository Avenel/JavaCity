package de.ka.javacity.helper;

public class HeightMapGenerator {
	// size == 2^n
	private int size;
	
	// maxHeight
	private int maxHeight;
	
	/**
	 * Public constructor
	 * @param size
	 * @param maxHeight
	 */
	public HeightMapGenerator(int size, int maxHeight) {
		this.size = size;
		this.maxHeight = maxHeight;
	}
	
	public int[][] generate(int amountOfMontains) {
		int[][] map = new int[this.size][this.size];
		
		float frequency=(1.0f/((float)this.size))*amountOfMontains;
		
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map.length; y++) {
				map[x][y] = (int)(Math.abs(Noise.noise(x*frequency, y*frequency)) * this.maxHeight);
			}
		}

		// generate moutains
		/*for (int i=0; i<amountOfMontains; i++) {
			
			int mountainX = (int)(Math.random() * this.size);
			int mountainY = (int)(Math.random() * this.size);
			int height = (int)((Math.random()) * this.maxHeight);
			
			if (height >= maxHeight) {
				height = maxHeight - 1;
			}
			
			map[mountainX][mountainY] = height;
			
			System.out.println("Mountain X:" + mountainX);
			System.out.println("Mountain Y:" + mountainY);
			System.out.println("Mountain Height: " + height);
			
			// interpolate
			float radius = (float)(this.size * Math.random());
			System.out.println("radius" + radius);
			float fall = (float) height / (float) radius;
			System.out.println("fall: "+fall);
			
			for (int x = 0; x < this.size; x++) {
				for (int y = 0; y < this.size; y++) {
					float distanceX = Math.abs(x - mountainX);
					float distanceY = Math.abs(y - mountainY);
					
					float distance = (float) Math.sqrt(distanceX*distanceX + distanceY*distanceY);
					
					if (distance < radius) {
						map[x][y] += (int)((float)height - (fall * distance));
						if (map[x][y] > this.maxHeight) map[x][y] = this.maxHeight - 1;
						if (map[x][y] <= 1) map[x][y] = 1;
					}
				}
			}
		} */
	
		return map;
	}
	
}
