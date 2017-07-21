package engine.utils;

import java.util.Random;

public class NoiseGenerator {
	
	private static float AMPLITUDE = 10f;
	private static float ROUGHNESS = 7f;
	
	private Random random = new Random();
	private int seed;
	
	public NoiseGenerator(float amplitude, float roughness, int seed){
		if(amplitude != 0) AMPLITUDE = amplitude;
		if(roughness != 0) ROUGHNESS = roughness;
		if(seed == 0)
			this.seed = random.nextInt(1000000000);
		else this.seed = seed;
		
	}
	
	public float generateNoise(int x, int y){
		float total = getInterpolatedNoise(x / ROUGHNESS, y / ROUGHNESS) * AMPLITUDE;
		total += getInterpolatedNoise(x / ROUGHNESS / 2, y / ROUGHNESS / 2) * AMPLITUDE / 3f;
		return total;
	}
	
	private float getInterpolatedNoise(float x, float z){
		int intX = (int) x;
		int intZ = (int) z;
		float fracX = x - intX;
		float fracZ = z - intZ;
		
		float v1 = getSmoothNoise(intX, intZ);
		float v2 = getSmoothNoise(intX + 1, intZ);
		float v3 = getSmoothNoise(intX, intZ + 1);
		float v4 = getSmoothNoise(intX + 1, intZ + 1);
		float i1 = interpolate(v1, v2, fracX);
		float i2 = interpolate(v3, v4, fracX);
		return interpolate(i1, i2, fracZ);
	}
	
	private float interpolate(float a, float b, float blend){
		double theta = blend * Math.PI;
		float f = (float) ((1f - Math.cos(theta)) * 0.5f);
		return a * (1f - f) + b * f;
	}
	
	private float getSmoothNoise(int x, int z){
		float corners =  (getNoise(x - 1, z - 1) + getNoise(x + 1, z - 1) + getNoise(x - 1, z + 1) + getNoise(x + 1, z + 1)) / 16f;
		float sides = (getNoise(x - 1, z) + getNoise(x + 1, z) + getNoise(x, z - 1) + getNoise(x, z + 1)) / 8f;
		float center = getNoise(x, z) / 4f;
		return corners + sides + center;
	}
	
	private float getNoise(int x, int z){
		random.setSeed(x * 49632 + z * 324176 + seed);
		return random.nextFloat() * 2f - 1f;
	}

}
