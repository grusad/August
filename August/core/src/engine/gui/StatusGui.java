package engine.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import engine.mobs.Player;
import engine.overwrites.OverwrittenProgressBar;

public class StatusGui extends GuiTable{
	
	private static final int BAR_HEIGHT = 64;
	
	private OverwrittenProgressBar health;
	private OverwrittenProgressBar hunger;
	private OverwrittenProgressBar thirst;
	
	private Label healthLabel;
	private Label hungerLabel;
	private Label thirstLabel;

	public StatusGui(GuiManager guiManager) {
		super(guiManager);
		align(Align.bottomRight);
	}

	@Override
	protected void components() {
		
		healthLabel = new Label("", skin);
		hungerLabel = new Label("", skin);
		thirstLabel = new Label("", skin);
		
		health = new OverwrittenProgressBar(0, 100, 1, Player.HEALTH, skin, Color.RED);
		health.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				healthLabel.setVisible(true);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				super.exit(event, x, y, pointer, toActor);
				healthLabel.setVisible(false);
			}
		});
		
		hunger = new OverwrittenProgressBar(0, 100, 1, Player.HUNGER, skin, Color.GREEN);
		hunger.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				hungerLabel.setVisible(true);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				super.exit(event, x, y, pointer, toActor);
				hungerLabel.setVisible(false);
			}
		});
		
		thirst = new OverwrittenProgressBar(0, 100, 1, Player.THIRST, skin, Color.SKY);
		thirst.addListener(new InputListener(){
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				thirstLabel.setVisible(true);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				super.exit(event, x, y, pointer, toActor);
				thirstLabel.setVisible(false);
			}
		});
		
		add(healthLabel).pad(0, 0, 0, 8);
		add(hungerLabel).pad(0, 0, 0, 8);
		add(thirstLabel).pad(0, 0, 0, 8).row();
		
		healthLabel.setVisible(false);
		hungerLabel.setVisible(false);
		thirstLabel.setVisible(false);
		
		add(health).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		add(hunger).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		add(thirst).height(BAR_HEIGHT).pad(0, 0, 8, 8);
		
	}
	
	public void udpateComponents(){
		
		health.setValue(Player.HEALTH);
		hunger.setValue(Player.HUNGER);
		thirst.setValue(Player.THIRST);
		
		healthLabel.setText("" + (int) health.getValue());
		hungerLabel.setText("" + (int) hunger.getValue());
		thirstLabel.setText("" + (int) thirst.getValue());
	}

}
