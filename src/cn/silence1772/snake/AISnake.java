package cn.silence1772.snake;

import javafx.scene.paint.Color;

public class AISnake extends BaseSnake {
	
	private int cnt = 0;
	private int cntMax = 50;
	
	public AISnake(int x, int y, int cntMax, Color color) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		this.cntMax = cntMax;
		this.color = color;
	}

	@Override
	public void init() {
		
		direction = DIRECTIONS.DIR_RIGHT;
		speed = 1.5;
		length = DEFAULT_LENGTH;
		
	}

	public void updateDir(Food food) {
		if (cnt > cntMax) {
			cnt = 0;
			
			if (food.getX() > getX() && food.getY() > getY()) {
				direction = DIRECTIONS.DIR_RIGHTDOWN;
			} else if (food.getX() > getX() && food.getY() < getY()) {
				direction = DIRECTIONS.DIR_RIGHTUP;
			} else if (food.getX() < getX() && food.getY() < getY()) {
				direction = DIRECTIONS.DIR_LEFTUP;
			} else if (food.getX() < getX() && food.getY() > getY()) {
				direction = DIRECTIONS.DIR_LEFTDOWN;
			}
		} else {
			cnt++;
		}
		
		
	}
}
