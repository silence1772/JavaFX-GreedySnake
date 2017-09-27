package cn.silence1772.snake;

import java.security.SecureRandom;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import cn.silence1772.core.SObject;
import cn.silence1772.core.SContants;

public class Food extends SObject {

	private SecureRandom random = new SecureRandom();

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
		gc.setFill(Color.RED);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		if (!isVisible()) {
			createRandomFood();
			setVisible(true);
		}
	}

	private void createRandomFood() {
		// 保证是在最小单元上，不会错位
		int x = random.nextInt(SContants.WIDTH / SContants.MIN_X) * SContants.MIN_X;
		int y = random.nextInt(SContants.HEIGHT / SContants.MIN_Y) * SContants.MIN_Y;
		setX(x);
		setY(y);
	}

}
