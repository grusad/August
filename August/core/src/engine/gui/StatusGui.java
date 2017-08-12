package engine.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;

import engine.mobs.Player;
import engine.overwrites.OverwrittenProgressBar;

public class StatusGui extends GuiTable{
	
	private static final int BAR_HEIGHT = 64;
	
	private OverwrittenProgressBar health;
	private OverwrittenProgressBar hunger;
	private OverwrittenProgressBar thirst;
	

	public StatusGui(GuiManager guiManager) {
		super(guiManager);
		align(Align.bottomRight);
	}

	@Override
	protected void components() {
		
		health = new OverwrittenProgressBar(0, 100, 1, Player.HEALTH, skin, Color.RED);
		health.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				super.exit(event, x, y, pointer, toActor);
			}
		});
		
		hunger = new OverwrittenProgressBar(0, 100, 1, Player.HUNGER, skin, Color.GREEN);
		hunger.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
			}
		});
		
		thirst = new OverwrittenProgressBar(0, 100, 1, Player.THIRST, skin, Color.SKY);
		thirst.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
			}
		});
		
		add(health).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		add(hunger).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		add(thirst).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		
	}
	
	public void udpateComponents(){
		
		health.setValue(Player.HEALTH);

		hunger.setValue(Player.HUNGER);
		thirst.setValue(Player.THIRST);
	}

}
