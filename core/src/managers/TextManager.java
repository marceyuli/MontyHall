package managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import objects.Door;

public class TextManager {
	static BitmapFont font;
	static String start;
	static StringBuffer confirm;
	static String win;
	static String lose;
	static float width;
	static float height;
	
	static {
		TextManager.start = "Selecciona una puerta";
		TextManager.win = "¡GANASTE!";
		TextManager.lose = "perdiste :c" ;
	}
	
	public static void initialize(final float width, final float height) {
		TextManager.width = width;
		TextManager.height = height;
		(TextManager.font = new BitmapFont()).setColor(Color.CYAN);
		TextManager.font.getData().setScale(width/500.0f);
		TextManager.confirm = new StringBuffer("Seleccionaste la puerta. Quieres mantenerte o cambiarla?");
		
	}
	public static void displayMessage(final SpriteBatch batch) {
		switch(GameManager.level) {
		case START: {
			TextManager.font.draw((Batch) batch, (CharSequence) TextManager.start, TextManager.width /2.0f - 50, ((Door)GameManager.doors.first()).closeSprite.getY() / 2.0f + 20);
			break;
		}
		case CONFIRM: {
			TextManager.font.draw((Batch) batch, (CharSequence) TextManager.confirm, TextManager.width /2.0f - 250, ((Door)GameManager.doors.first()).closeSprite.getY() / 2.0f + 20);
			break;
		}
		case END: {
			if(GameManager.hasWon) {
				TextManager.font.draw((Batch) batch, (CharSequence) TextManager.win, TextManager.width /2.0f - 50, ((Door)GameManager.doors.first()).closeSprite.getY() / 2.0f + 20);
			} else {
				TextManager.font.draw((Batch) batch, (CharSequence) TextManager.lose, TextManager.width /2.0f - 50, ((Door)GameManager.doors.first()).closeSprite.getY() / 2.0f + 20);

			}
			break;
		}
		
		}
	}
	
	public static void setSelectedDoor(final int doorIndex) {
		TextManager.confirm.insert(TextManager.confirm.indexOf("door no")+ "door no".length(), " " + (doorIndex + 1));
	}
	
}
