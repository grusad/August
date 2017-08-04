package engine.utils;

import com.badlogic.gdx.math.Vector2;

public class Box {

	private Vector2 position;
	public float x;
	public float y;
	public int width;
	public int height;

	public Box(Vector2 position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.x = position.x;
		this.y = position.y;
	}

	public Box(float x, float y, int width, int height) {
		this.position = new Vector2(x, y);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean intersects(Box box) {
		int tw = this.width;
		int th = this.height;
		int rw = box.width;
		int rh = box.height;
		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		float tx = this.x;
		float ty = this.y;
		float rx = box.x;
		float ry = box.y;
		rw += rx;
		rh += ry;
		tw += tx;
		th += ty;
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty) && (tw < tx || tw > rx) && (th < ty || th > ry));

	}

	
	public boolean contains(Box b) {
		return contains(b.x, b.y, b.width, b.height);
	}

	public boolean contains(float X, float Y, int W, int H) {
		int w = this.width;
		int h = this.height;
		if ((w | h | W | H) < 0) {
			return false;
		}
		float x = this.x;
		float y = this.y;
		if (X < x || Y < y) {
			return false;
		}
		w += x;
		W += X;
		if (W <= X) {
			if (w >= x || W > w)
				return false;
		} else {
			if (w >= x && W > w)
				return false;
		}
		h += y;
		H += Y;
		if (H <= Y) {
			if (h >= y || H > h)
				return false;
		} else {
			if (h >= y && H > h)
				return false;
		}
		return true;
	}

	public boolean contains(float X, float Y) {
		int w = this.width;
		int h = this.height;
		if ((w | h) < 0) {
			return false;
		}
		float x = this.x;
		float y = this.y;
		if (X < x || Y < y) {
			return false;
		}
		w += x;
		h += y;
		return ((w < x || w > X) && (h < y || h > Y));
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Vector2 getCenterPos(){
		Vector2 vector = new Vector2();
		vector.x = x + width / 2;
		vector.y = y + height / 2;
		return vector;
	}

}
