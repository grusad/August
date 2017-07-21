package engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class CursorProperties {
	
	public static Cursor CURSOR_DEFAULT = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/defaultCursor.png")), 8, 2);
	//public static Cursor CURSOR_MINE = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/cursor.png")), 0, 0);
	//public static Cursor CURSOR_PICK = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/cursor.png")), 0, 0);
	//public static Cursor CURSOR_HOLD = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/cursor.png")), 0, 0);
	
	public static void setCursor(Cursor cursor){
		Gdx.graphics.setCursor(CURSOR_DEFAULT);
	}
	
	public static void dispose(){
		CURSOR_DEFAULT.dispose();
	}

}
