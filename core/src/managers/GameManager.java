package managers;

import java.lang.System.Logger.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

import objects.Door;

public class GameManager {
	static Array<Door> doors; // array of the three doors
	static Texture doorTexture; //imagen textura de la puerta
	static Texture carTexture;
	static Texture goatTexture;
	static Vector3 temp = new Vector3(); //temp vectore to store input coordinates
	static IntArray goatIndices;
	
	private static final float DOOR_RESIZE_FACTOR = 2500f;
	private static final float DOOR_VERT_POSITION_FACTOR = 3f;
	private static final float DOOR1_HORIZ_POSITION_FACTOR = 7.77f;
	private static final float DOOR2_HORIZ_POSITION_FACTOR = 2.57f;
	private static final float DOOR3_HORIZ_POSITION_FACTOR = 1.52f;
	static float width,height;
	
	static Sprite backSprite;
	
	public static enum Level {
		START,
		CONFIRM,
		END
	}
	static Level level;
	
	static boolean hasWon= false;
	
	public static void initialize(float width, float height) {
		GameManager.width = width;
		GameManager.height = height;
		doorTexture = new Texture(Gdx.files.internal("data/door_close.png"));
		carTexture = new Texture(Gdx.files.internal("data/door_open_car.png"));
		goatTexture = new Texture(Gdx.files.internal("data/door_open_goat.png"));
		initDoors();
		
		goatIndices = new IntArray();
		level = Level.START;
		
	}
	public static void renderGame(SpriteBatch batch) {
		for(Door door: doors) {
			door.render(batch);
		}
	}
	public static void dispose() {
		doorTexture.dispose();
		carTexture.dispose();
		goatTexture.dispose();
	}
	public static void initDoors() {
		doors = new Array<Door>();
		for(int i=0; i<3; i++) {
			doors.add(new Door());
		}
		doors.get(0).position.set(width/DOOR1_HORIZ_POSITION_FACTOR, height/DOOR_VERT_POSITION_FACTOR);
		doors.get(1).position.set(width/DOOR2_HORIZ_POSITION_FACTOR, height/DOOR_VERT_POSITION_FACTOR);
		doors.get(2).position.set(width/DOOR3_HORIZ_POSITION_FACTOR, height/DOOR_VERT_POSITION_FACTOR);
		
		for(Door door : doors){
			//instantiate sprite for the closed door with the texture of it
			door.closeSprite = new Sprite(doorTexture);
			door.openSprite = new Sprite();
			door.width = door.closeSprite.getWidth()*(width/DOOR_RESIZE_FACTOR);
			door.height = door.closeSprite.getHeight()*(width/DOOR_RESIZE_FACTOR);
			door.closeSprite.setSize(door.width, door.height);
			door.closeSprite.setPosition(door.position.x, door.position.y);
			
			//set the dimension for the openDoor
			door.openSprite.setSize(door.width, door.height);
			door.openSprite.setPosition(door.position.x, door.position.y);
		}
		
		doors.get(0).openSprite.setRegion(goatTexture);
		doors.get(0).isGoat = true;
		doors.get(1).openSprite.setRegion(carTexture);
		doors.get(1).isGoat = false;
		doors.get(2).openSprite.setRegion(goatTexture);
		doors.get(2).isGoat = true;
	}
	
	public static IntArray getGoatIndices( int selectedDoorIndex) {
		goatIndices.clear(); //remove all previous values from the array
		
		for(int i=0; i<doors.size; i++) {
			//exclude selected door
			if( i!= selectedDoorIndex && doors.get(i).isGoat) {
				goatIndices.add(i);
			}
		}
		return goatIndices;
	}

}
