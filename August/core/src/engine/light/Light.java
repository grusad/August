package engine.light;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import engine.graphics.Camera;

public class Light {

	private PointLight light;

	public Light(Vector2 position, float radius, int rays, float brightness, RayHandler handler) {
		light = new PointLight(handler, rays, new Color(255, 255, 255, brightness), radius, position.x / Camera.SCALE,
				position.y / Camera.SCALE);
		light.setActive(true);

	}

	public void update(Vector2 position) {
		light.setPosition(position);
	}

	public void remove() {
		light.remove();
	}
}
