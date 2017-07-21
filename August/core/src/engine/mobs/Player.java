package engine.mobs;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import engine.audio.AudioManager;
import engine.debug.DebugManager;
import engine.elements.Element;
import engine.graphics.Animation;
import engine.graphics.Textures;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.Box;
import engine.utils.DataManager.PlayerData;
import engine.utils.Input;
import engine.utils.ValueBouncer;
import engine.utils.ValueBouncer.InvalidNumberException;

public class Player extends Mob{
	
	
	private static float HIT_DMG = 1;
	private static float MOVE_SPEED = 1;
	private static float AIM_LENGTH = 30;
	
	public static float HEALTH = 100;
	public static float STAMINA = 100;
	public static float BODY_TEMP = 36;
	public static float HUNGER = 100;
	public static float THIRST = 100;
	
	private float aimAngle = 0;
	private float cooldown = .05f;
	private float cooldownTimer = 0;
	
	private Element selectedElement = null;
	private ValueBouncer valueBouncer = null;

	public Player(Vector2 position) {
		super(position);
	}

	@Override
	public void update() {
		
		if(DebugManager.IN_DEBUG_MODE){
			
			if(Gdx.input.isKeyPressed(Keys.SPACE)) MOVE_SPEED = 30;
			else MOVE_SPEED = 1;
		}
		
		super.update();

		float x0 = Gdx.graphics.getWidth() / 2;
		float x1 = Input.getScreenPosition().x;
		float y0 = Gdx.graphics.getHeight() / 2;
		float y1 = Input.getScreenPosition().y;

		float dx = x1 - x0;
		float dy = y1 - y0;
		aimAngle = (float) Math.toDegrees(Math.atan2(dy, dx));
	
		if(aimAngle < -67.5 && aimAngle > -112.5) direction = 0;
		else if(aimAngle < -22.5 && aimAngle > -67.5) direction = 1; 
		else if(aimAngle > -22.5 && aimAngle < 22.5) direction = 2;
		else if(aimAngle > 22.5 && aimAngle < 67.5) direction = 3;
		else if(aimAngle > 67.5 && aimAngle < 112.5) direction = 4;
		else if(aimAngle > 112.5 && aimAngle < 157.5) direction = 5;
		else if(aimAngle > 157.5 || aimAngle < -157.5) direction = 6;
		else if(aimAngle > -157.5 && aimAngle < 112.5) direction = 7;

		float xx = 0;
		float yy = 0;
		
		
		if(Gdx.input.isKeyPressed(Keys.D)) xx += (MOVE_SPEED - Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.A)) xx -= (MOVE_SPEED - Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.W)) yy += (MOVE_SPEED - Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.S)) yy -= (MOVE_SPEED - Gdx.graphics.getDeltaTime());
		
		
		
		cooldownTimer += Gdx.graphics.getDeltaTime();
		if(cooldownTimer >= cooldown) cooldownTimer = cooldown;
		
		selectElement();
	
		if(Gdx.input.isTouched()){
			if(cooldownTimer == cooldown){
				hit();
				cooldownTimer -= cooldown;
			}
		}
		
		if(isSwimming()) MOVE_SPEED = 0.5f;
		else MOVE_SPEED = 1;
		
		move(xx, yy);
		
	}
	
	private void selectElement(){
		
		//Checks if the element is removed or not. If it is removed, make selectedElement to null.
		if(selectedElement != null){
			 if(selectedElement.getCurrentHP() <= 0){
				 selectedElement = null;
			 }
		}
		
		//Checks if the aimbox is not intersecting with the selectedElements hitbox. if its not, make selectedElement to null.
		if(selectedElement != null){
			
			selectedElement.setTransparency(valueBouncer.updateAndReturnValue());
			
			if(!getAimBox().intersects(selectedElement.getHitBox())){
				selectedElement.resetElement();
				selectedElement = null;
			}else{
				return;
			}
			
		}
		
		Box hitBox = getAimBox();
		List<Element> elements = worldManager.getElementManager().getElementsOnScreen();
		
		for(int i = 0; i < elements.size(); i++){
			Element element = elements.get(i);
			if(element.isMinable()){
				if(hitBox.intersects(element.getHitBox())){
					selectedElement = element;
					selectedElement.setRenderHP(true);
					valueBouncer = new ValueBouncer(0.5f, 1, 1, 1.5f, false);
					return;
				}
			}
		}
	}

	private void hit(){
		if(selectedElement != null){
			selectedElement.mine(HIT_DMG);
			ParticleManager.spawnParticleEffect(ParticleType.MineParticle, getAimBox().getPosition(), 10);
			AudioManager.playSound(AudioManager.getSound("mine"));
		}
	}
	
	@Override
	protected void setAnimations() {
		
		animations[0] = new Animation(Textures.PLAYER_N);
		animations[1] = new Animation(Textures.PLAYER_NE);
		animations[2] = new Animation(Textures.PLAYER_E);
		animations[3] = new Animation(Textures.PLAYER_SE);
		animations[4] = new Animation(Textures.PLAYER_S);
		animations[5] = new Animation(Textures.PLAYER_SW);
		animations[6] = new Animation(Textures.PLAYER_W);
		animations[7] = new Animation(Textures.PLAYER_NW);
		
		swimAnimations[0] = new Animation(Textures.PLAYER_SWIM_N);
		swimAnimations[1] = new Animation(Textures.PLAYER_SWIM_NE);
		swimAnimations[2] = new Animation(Textures.PLAYER_SWIM_E);
		swimAnimations[3] = new Animation(Textures.PLAYER_SWIM_SE);
		swimAnimations[4] = new Animation(Textures.PLAYER_SWIM_S);
		swimAnimations[5] = new Animation(Textures.PLAYER_SWIM_SW);
		swimAnimations[6] = new Animation(Textures.PLAYER_SWIM_W);
		swimAnimations[7] = new Animation(Textures.PLAYER_SWIM_NW);
		
	}

	@Override
	public Box getHitBox() {
		return new Box(new Vector2(position.x + 11, position.y + 4), getStaticWidth() - 23, getStaticHeight() / 2 - 12);
	}
	
	public Box getAimBox(){
		
		float x;
		float y;
		
		float x0 = getCenteredPositionCurrent().x;
		float x1 = Input.getWorldPosition().x;
		float y0 = getCenteredPositionCurrent().y;
		float y1 = Input.getWorldPosition().y;

		float dx = x1 - x0;
		float dy = y1 - y0;
		
		double value = Math.sqrt(dx * dx + dy * dy); 

		if(value <= AIM_LENGTH){
			x = Input.getWorldPosition().x;
			y = Input.getWorldPosition().y;
		}
		
		else{
			x = getCenteredPositionCurrent().x;
			y = getCenteredPositionCurrent().y; 
			
			float angle = (float) Math.toRadians(aimAngle);
			
			x += Math.cos(angle) * AIM_LENGTH;
			y += -Math.sin(angle) * AIM_LENGTH;
		}
		
		return new Box(new Vector2(x - 1, y - 1), 3, 3);
	}
	
	public PlayerData getData(){
		PlayerData data = new PlayerData();
		
		data.x = position.x;
		data.y = position.y;
		data.aimLength = AIM_LENGTH;
		data.bodyTemp = BODY_TEMP;
		data.health = HEALTH;
		data.stamina = STAMINA;
		data.hunger = HUNGER;
		data.thirst = THIRST;
		data.moveSpeed = MOVE_SPEED;

		return data;
		
	}
	
	public void setData(PlayerData data){
		
		this.position.x = data.x;
		this.position.y = data.y;
		MOVE_SPEED = data.moveSpeed;
		AIM_LENGTH = data.aimLength;
		BODY_TEMP = data.bodyTemp;
		HEALTH = data.health;
		STAMINA = data.stamina;
		THIRST = data.thirst;
		HUNGER = data.hunger;
	}

}
