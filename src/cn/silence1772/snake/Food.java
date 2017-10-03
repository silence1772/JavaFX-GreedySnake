package cn.silence1772.snake;

import java.security.SecureRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import cn.silence1772.core.SObject;
import cn.silence1772.core.SContants;

public class Food extends SObject {

	private SecureRandom random = new SecureRandom();
	private Color color;
	
	public Food() {
		init();
	}

	public void init() {
		
		super.init();
		
		createRandomFood();
		setWidth(SContants.MIN_X);
		setHeight(SContants.MIN_Y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		gc.setFill(color);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		
		if (!isVisible()) {
			color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			createRandomFood();
			setVisible(true);
		}
	}

	private void createRandomFood() {

		int x = random.nextInt(SContants.WIDTH);
		int y = random.nextInt(SContants.HEIGHT);
		setX(x);
		setY(y);
	}

}
