package engine.mobs;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import engine.audio.AudioManager;
import engine.debug.DebugManager;
import engine.elements.Element;
import engine.entities.Entity;
import engine.entities.InteractableEntity;
import engine.graphics.Animation;
import engine.graphics.Textures;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.resources.Resource;
import engine.tiles.Tile;
import engine.utils.Box;
import engine.utils.DataManager.PlayerData;
import engine.utils.Input;

public class Player extends Mob{
	
	private static float HIT_DMG = 20;
	private static float DEBUG_SPEED = 64 * 5;
	private static float MOVE_SPEED = 64;
	private static float SWIM_SPEED = 32;
	private static float AIM_LENGTH = 30;
	
	public static float HEALTH = 100;
	public static float STAMINA = 100;
	public static float BODY_TEMP = 36;
	public static float HUNGER = 100;
	public static float THIRST = 100;
	
	private float aimAngle = 0;
	private float cooldown = .25f;
	private float cooldownTimer = 0;
	
	private static boolean LOCK_AT_SELECTED = false;
	
	private InteractableEntity selectedEntity = null;

	public Player(Vector2 position) {
		super(position);
	}

	@Override
	public void update() {
		
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
		
		float speed = MOVE_SPEED;
		
		if(isSwimming()) speed = SWIM_SPEED;
		
		if(DebugManager.IN_DEBUG_MODE && Gdx.input.isKeyPressed(Keys.SPACE)) speed = DEBUG_SPEED;
		
		if(Gdx.input.isKeyPressed(Keys.D)) xx += speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.A)) xx -= speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.W)) yy += speed * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.S)) yy -= speed * Gdx.graphics.getDeltaTime();
		
		cooldownTimer += Gdx.graphics.getDeltaTime();
		if(cooldownTimer >= cooldown) cooldownTimer = cooldown;
		
		selectEntity();
		updateSelectedEntity();
		
		move(xx, yy);
		
	}
	
	private void updateSelectedEntity(){
		
		Box box = getAimBox();
		
		if(selectedEntity != null){
			
			if(selectedEntity instanceof Element){
				Element element = (Element) selectedEntity;
				if(element.getCurrentHP() <= 0){
					selectedEntity = null;
					return;
				}
				
				if(Gdx.input.isTouched()){
					if(cooldownTimer == cooldown){
						if(selectedEntity instanceof Element){
							mineElement(element);
						}
						cooldownTimer -= cooldown;
					}
				}
				
				return;
			}
			
			if(selectedEntity instanceof Resource){
				
				Resource resource = (Resource) selectedEntity;
				
				if(Gdx.input.isTouched()){
					
					LOCK_AT_SELECTED = true;
					
					resource.getPosition().x = box.getX() - resource.getHitBox().width / 2;
					resource.getPosition().y = box.getY() - resource.getHitBox().height / 2;
					
				}
				else{
					if(!canDropSelectedEntity()){
						selectedEntity.getPosition().x = getCenteredHitBox().x;
						selectedEntity.getPosition().y = getCenteredHitBox().y;
					}
					LOCK_AT_SELECTED = false;
				}
				
				return;
				
			}
		}
		
	}

	private void selectEntity() {
		
		if(LOCK_AT_SELECTED) return;
		
		if(selectedEntity != null){
			
			if(!getAimBox().intersects(selectedEntity.getHitBox())){
				selectedEntity.stopInteract();
				selectedEntity = null;
			}else{
				return;
			}
		}
		
		Box hitBox = getAimBox();
		List<InteractableEntity> interactableEntities = worldManager.getAllInteractableEntities();
		
		for(int i = 0; i < interactableEntities.size(); i++){
			InteractableEntity entity = interactableEntities.get(i);
			
			if(hitBox.intersects(entity.getHitBox())){
				selectedEntity = entity;
				entity.startInteract();
			}
		}
		
	}

	private void mineElement(Element element){
		element.mine(HIT_DMG);
		ParticleManager.spawnParticleEffect(ParticleType.MineParticle, getAimBox().getPosition(), 10);
		AudioManager.playSound(AudioManager.getSound("mine"));
		
	}
	
	private boolean canDropSelectedEntity(){
		List<Entity> entities = worldManager.getAllEntitiesInScene();
		
		for(int i = 0; i < entities.size(); i++){
			Entity entity = entities.get(i);
			if(!entity.isSolid() || entity.equals(selectedEntity)) continue;
			if(selectedEntity.getHitBox().intersects(entity.getHitBox())) return false;
		}
		
		Tile tile = worldManager.getTileManager().getTile((int)selectedEntity.getPosition().x / Tile.SIZE, 
				(int) selectedEntity.getPosition().y / Tile.SIZE);
		
		if(tile.getID() == Tile.WATER.getID()) return false;
		
		return true;
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
		
		return new Box(new Vector2(x - 1, y - 1), 1, 1);
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
