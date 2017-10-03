package cn.silence1772.snake;

import cn.silence1772.core.SContants;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class UserSnake extends BaseSnake{
	public UserSnake() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		
		direction = DIRECTIONS.DIR_RIGHT;
		color = Color.web("#38a3fd", 1.0);
		speed = 1.5;
		length = DEFAULT_LENGTH;
		setX(SContants.WIDTH / 2);
		setY(SContants.HEIGHT / 2);
	}
	
	@Override
	public void onKeyPressed(KeyEvent event) {
		
		KeyCode tmpCode = event.getCode();
		// 如果反方向那么不处理，蛇不会掉头
		if ((tmpCode == KeyCode.UP && direction == DIRECTIONS.DIR_DOWN) 
				|| (tmpCode == KeyCode.DOWN && direction == DIRECTIONS.DIR_UP) 
				|| (tmpCode == KeyCode.RIGHT && direction == DIRECTIONS.DIR_LEFT) 
				|| (tmpCode == KeyCode.LEFT && direction == DIRECTIONS.DIR_RIGHT) 
		) {
			return;
		}

		updateDir(tmpCode, direction);
	}
	
	public void updateDir(KeyCode keyCode, DIRECTIONS dir) {
		
		if (keyCode == KeyCode.UP) {
			switch (dir) {
			case DIR_LEFTDOWN:
				direction = DIRECTIONS.DIR_LEFT;
				break;
			case DIR_LEFT:
				direction = DIRECTIONS.DIR_LEFTUP;
				break;
			case DIR_LEFTUP:
				direction = DIRECTIONS.DIR_UP;
				break;
			case DIR_RIGHTDOWN:
				direction = DIRECTIONS.DIR_RIGHT;
				break;
			case DIR_RIGHT:
				direction = DIRECTIONS.DIR_RIGHTUP;
				break;
			case DIR_RIGHTUP:
				direction = DIRECTIONS.DIR_UP;
				break;
			default:
				break;
			}
		} else if (keyCode == KeyCode.DOWN) {
			switch (dir) {
			case DIR_LEFTDOWN:
				direction = DIRECTIONS.DIR_DOWN;
				break;
			case DIR_LEFT:
				direction = DIRECTIONS.DIR_LEFTDOWN;
				break;
			case DIR_LEFTUP:
				direction = DIRECTIONS.DIR_LEFT;
				break;
			case DIR_RIGHTDOWN:
				direction = DIRECTIONS.DIR_DOWN;
				break;
			case DIR_RIGHT:
				direction = DIRECTIONS.DIR_RIGHTDOWN;
				break;
			case DIR_RIGHTUP:
				direction = DIRECTIONS.DIR_RIGHT;
				break;
			default:
				break;
			}
		} else if (keyCode == KeyCode.LEFT) {
			switch (dir) {
			case DIR_LEFTUP:
				direction = DIRECTIONS.DIR_LEFT;
				break;
			case DIR_LEFTDOWN:
				direction = DIRECTIONS.DIR_LEFT;
				break;
			case DIR_UP:
				direction = DIRECTIONS.DIR_LEFTUP;
				break;
			case DIR_DOWN:
				direction = DIRECTIONS.DIR_LEFTDOWN;
				break;
			case DIR_RIGHTUP:
				direction = DIRECTIONS.DIR_UP;
				break;
			case DIR_RIGHTDOWN:
				direction = DIRECTIONS.DIR_DOWN;
				break;
			default:
				break;
			}
		} else if (keyCode == KeyCode.RIGHT) {
			switch (dir) {
			case DIR_LEFTUP:
				direction = DIRECTIONS.DIR_UP;
				break;
			case DIR_LEFTDOWN:
				direction = DIRECTIONS.DIR_DOWN;
				break;
			case DIR_UP:
				direction = DIRECTIONS.DIR_RIGHTUP;
				break;
			case DIR_DOWN:
				direction = DIRECTIONS.DIR_RIGHTDOWN;
				break;
			case DIR_RIGHTUP:
				direction = DIRECTIONS.DIR_RIGHT;
				break;
			case DIR_RIGHTDOWN:
				direction = DIRECTIONS.DIR_RIGHT;
				break;
			default:
				break;
			}
		}
	}
}
