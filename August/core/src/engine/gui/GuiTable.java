package engine.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import engine.graphics.SkinLoader;

public abstract class GuiTable extends Table{
	
	protected GuiManager guiManager;
	
	protected static Skin skin = SkinLoader.getDefaultSkin();
	
	public GuiTable(GuiManager guiManager){
		this.guiManager = guiManager;
		setFillParent(true);
		components();

	}
	
	protected abstract void components();

}