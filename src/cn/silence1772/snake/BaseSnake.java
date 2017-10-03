package cn.silence1772.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

import cn.silence1772.core.SObject;
import cn.silence1772.core.SContants;

public class BaseSnake extends SObject {
	
	protected enum DIRECTIONS {
		
		DIR_UP, DIR_DOWN, DIR_LEFT, DIR_RIGHT, DIR_LEFTUP, DIR_LEFTDOWN, DIR_RIGHTUP, DIR_RIGHTDOWN
	};
	public static final int DEFAULT_LENGTH = 12;
	AtomicInteger score = new AtomicInteger(0);
	Color color;
	DIRECTIONS direction;
	double speed;
	int length;
	int kills;


	public BaseSnake() {
		
		super.init();
		
		score.set(0);
		kills = 0;
		setWidth(SContants.MIN_X);
		setHeight(SContants.MIN_Y);
		
		init();
	}

	public void init() {

	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());

		if (direction == DIRECTIONS.DIR_DOWN || direction == DIRECTIONS.DIR_UP) {
			gc.setFill(Color.web("#fff"));
			gc.fillOval(getX() - 2,            getY() + 3, getWidth() / 2, getHeight() / 2);
			gc.fillOval(getX() + getWidth()/2, getY() + 3, getWidth() / 2, getHeight() / 2);
			
			gc.setFill(Color.web("#333"));
			gc.fillOval(getX() - 2 +2,            getY() + 3 +2, getWidth() / 3, getHeight() / 3);
			gc.fillOval(getX() + getWidth()/2 +2, getY() + 3 +2, getWidth() / 3, getHeight() / 3);
		} else if (direction == DIRECTIONS.DIR_LEFT || direction == DIRECTIONS.DIR_RIGHT) {
			gc.setFill(Color.web("#fff"));
			gc.fillOval(getX() + 3, getY() -1,             getWidth() / 2, getHeight() / 2);
			gc.fillOval(getX() + 3, getY() + getWidth()/2, getWidth() / 2, getHeight() / 2);
			
			gc.setFill(Color.web("#333"));
			gc.fillOval(getX() + 3 + 2, getY() +2, 				   getWidth() / 3, getHeight() / 3);
			gc.fillOval(getX() + 3 + 2, getY() + getWidth()/2 + 2, getWidth() / 3, getHeight() / 3);
		} else if (direction == DIRECTIONS.DIR_LEFTUP || direction == DIRECTIONS.DIR_RIGHTDOWN) {
			gc.setFill(Color.web("#fff"));
			gc.fillOval(getX() + 2,                getY() + 1.1 * getWidth()/2,                getWidth() / 2, getHeight() / 2);
			gc.fillOval(getX() + 2 + getWidth()/2, getY() + 1.1 * getWidth()/2 - getWidth()/2, getWidth() / 2, getHeight() / 2);
			
			gc.setFill(Color.web("#333"));
			gc.fillOval(getX() + 2 + 2,                getY() + 1.1 * getWidth()/2 + 2, 			   getWidth() / 3, getHeight() / 3);
			gc.fillOval(getX() + 2 + getWidth()/2 + 2, getY() + 1.1 * getWidth()/2 - getWidth()/2 + 2, getWidth() / 3, getHeight() / 3);
		
		} else if (direction == DIRECTIONS.DIR_LEFTDOWN || direction == DIRECTIONS.DIR_RIGHTUP) {
			gc.setFill(Color.web("#fff"));
			gc.fillOval(getX(),                getY(),                getWidth() / 2, getHeight() / 2);
			gc.fillOval(getX() + getWidth()/2, getY() + getWidth()/2, getWidth() / 2, getHeight() / 2);
			
			gc.setFill(Color.web("#333"));
			gc.fillOval(getX() + 2,                getY() + 2, 				  getWidth() / 3, getHeight() / 3);
			gc.fillOval(getX() + getWidth()/2 + 2, getY() + getWidth()/2 + 2, getWidth() / 3, getHeight() / 3);
		
		}
	}

	@Override
	public void update() {

		if (direction == DIRECTIONS.DIR_UP) {
			moveY(-speed);
		} else if (direction == DIRECTIONS.DIR_DOWN) {
			moveY(speed);
		} else if (direction == DIRECTIONS.DIR_LEFT) {
			moveX(-speed);
		} else if (direction == DIRECTIONS.DIR_RIGHT) {
			moveX(speed);
		} else if (direction == DIRECTIONS.DIR_LEFTUP) {
			moveX(-speed * Math.sqrt(2)/2);
			moveY(-speed * Math.sqrt(2)/2);
		} else if (direction == DIRECTIONS.DIR_LEFTDOWN) {
			moveX(-speed * Math.sqrt(2)/2);
			moveY(speed * Math.sqrt(2)/2);
		} else if (direction == DIRECTIONS.DIR_RIGHTUP) {
			moveX(speed * Math.sqrt(2)/2);
			moveY(-speed * Math.sqrt(2)/2);
		} else if (direction == DIRECTIONS.DIR_RIGHTDOWN) {
			moveX(speed * Math.sqrt(2)/2);
			moveY(speed * Math.sqrt(2)/2);
		}
	}

	public void death() {
		
		setX(0);
		setY(0);
		direction = DIRECTIONS.DIR_RIGHT;
		this.length = DEFAULT_LENGTH;
	}

	public void onKeyPressed() {

	}
	
	public int score() {
		
		return score.get();
	}

	public void addScore(int delta) {
		
		score.incrementAndGet();
		score.addAndGet(delta);
	}

	public int getKills() {
		
		return kills;
	}
	
	public void addKills() {
		
		kills += 1;
	}

	public int getLength() {
		
		return length;
	}

	public void addLength() {
		
		this.length = this.length + 1;
	}

	public Color getSnakeColor() {
		
		return color;
	}

}