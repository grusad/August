package engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public class CursorProperties {
	
	public static Cursor CURSOR_DEFAULT = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/defaultCursor.png")), 10, 0);
	public static Cursor CURSOR_MINE = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/mineCursor.png")), 10, 0);
	public static Cursor CURSOR_GRAB = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursorImages/grabCursor.png")), 10, 0);
	
	public static void setCursor(Cursor cursor){
		Gdx.graphics.setCursor(cursor);
	}
	
	public static void dispose(){
		CURSOR_DEFAULT.dispose();
		CURSOR_MINE.dispose();
	}

}
