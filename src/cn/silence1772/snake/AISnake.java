package cn.silence1772.snake;

import javafx.scene.paint.Color;

public class AISnake extends BaseSnake {
	public AISnake() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		direction = DIRECTIONS.DIR_RIGHT;
		color = Color.web("#666", 1.0);
		speed = 1.5;
		length = DEFAULT_LENGTH;
		setX(0);
		setY(0);
	}

	public void updateDir(Food food) {
		if (food.getX() > getX() && food.getY() > getY()) {
			direction = DIRECTIONS.DIR_RIGHTDOWN;
		} else if (food.getX() > getX() && food.getY() < getY()) {
			direction = DIRECTIONS.DIR_RIGHTUP;
		} else if (food.getX() < getX() && food.getY() < getY()) {
			direction = DIRECTIONS.DIR_LEFTUP;
		} else if (food.getX() < getX() && food.getY() > getY()) {
			direction = DIRECTIONS.DIR_LEFTDOWN;
		}
	}
}
