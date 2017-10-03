package cn.silence1772.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import cn.silence1772.core.SContants;
import cn.silence1772.core.SScreen;

public class GameScreen extends SScreen {

	Info info = new Info();

	UserSnake userSnake = new UserSnake();
	BaseBody userBody = new BaseBody(userSnake);
	
	AISnake aiSnake = new AISnake(0, 0, 100, Color.web("#3ceadc", 1.0));
	BaseBody aiBody = new BaseBody(aiSnake);
	AISnake aiSnake2 = new AISnake(SContants.WIDTH, SContants.HEIGHT, 80, Color.web("#fd6767", 1.0));
	BaseBody aiBody2 = new BaseBody(aiSnake2);
	AISnake aiSnake3 = new AISnake(0, SContants.HEIGHT, 40, Color.web("#fff", 1.0));
	BaseBody aiBody3 = new BaseBody(aiSnake3);
	AISnake aiSnake4 = new AISnake(SContants.WIDTH, 0, 10, Color.web("#9d9d9d", 1.0));
	BaseBody aiBody4 = new BaseBody(aiSnake4);
	
	Food food = new Food();


	public GameScreen(double width, double height) {
		
		super(width, height);

		addObject(userBody);
		addObject(userSnake);
		
		addObject(aiBody);
		addObject(aiSnake);
		addObject(aiBody2);
		addObject(aiSnake2);
		addObject(aiBody3);
		addObject(aiSnake3);
		addObject(aiBody4);
		addObject(aiSnake4);
		
		addObject(food);
		// 信息在最上层
		addObject(info);

		mGameState = GameState.GAME_PAUSE;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		if (info.getTimes() == 0) {
			mGameState = GameState.GAME_PAUSE;
			GameOver gameOver = new GameOver(userSnake.score(), aiSnake.score() + aiSnake2.score() + aiSnake3.score() + aiSnake4.score());
			addObject(gameOver);
		}

		super.draw(gc);
	}

	@Override
	public void update() {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}
		
		// 设置显示生命和积分
		info.setLength(userSnake.getLength());
		info.setKills(userSnake.getKills());
		info.setScore(userSnake.score());
		info.setAIScore(aiSnake.score() + aiSnake2.score() + aiSnake3.score() + aiSnake4.score());
		// 调用更新操作
		super.update();

		crossBorder(userSnake);
		crossBorder(aiSnake);
		crossBorder(aiSnake2);
		crossBorder(aiSnake3);
		crossBorder(aiSnake4);

		// 吃到
		eatFood(userSnake);
		eatFood(aiSnake);
		eatFood(aiSnake2);
		eatFood(aiSnake3);
		eatFood(aiSnake4);
		
		collisionTwoWay(userSnake, userBody, aiSnake, aiBody);
		collisionTwoWay(userSnake, userBody, aiSnake2, aiBody2);
		collisionTwoWay(userSnake, userBody, aiSnake3, aiBody3);
		collisionTwoWay(userSnake, userBody, aiSnake4, aiBody4);

		aiSnake.updateDir(food);
		aiSnake2.updateDir(food);
		aiSnake3.updateDir(food);
		aiSnake4.updateDir(food);

	}
	
	public void crossBorder(BaseSnake baseSnake) {
		
		if (baseSnake.getX() < 0) {
			baseSnake.setX(SContants.WIDTH);
		}
		if (baseSnake.getX() > SContants.WIDTH) {
			baseSnake.setX(0);
		}
		if (baseSnake.getY() < 0) {
			baseSnake.setY(SContants.HEIGHT);
		}
		if (baseSnake.getY() > SContants.HEIGHT) {
			baseSnake.setY(0);
		}
	}

	public void eatFood(BaseSnake baseSnake) {
		
		if (baseSnake.isCollisionWith(food)) {
			baseSnake.addScore(3);
			baseSnake.addLength();
			food.setVisible(false);
		}
	}
	
	public void collisionTwoWay(BaseSnake passive, BaseBody passiveBody, BaseSnake active, BaseBody activeBody) {
		
		collision(passive, passiveBody, active, activeBody);
		collision(active, activeBody, passive, passiveBody);
	}
	
	public void collision(BaseSnake passive, BaseBody passiveBody, BaseSnake active, BaseBody activeBody) {
		
		if (passiveBody.isCollisionWith(active)) {
			active.death();
			activeBody.setVisible(false);
			passive.addKills();
			passive.addScore(3);
			return;
		}
	}
	
	@Override
	public void onKeyReleased(KeyEvent event) {
		// 暂停
		if (event.getCode() == KeyCode.ESCAPE) {
			if (mGameState == GameState.GAME_PAUSE) {
				mGameState = GameState.GAME_START;
			} else {
				mGameState = GameState.GAME_PAUSE;
			}
		}

	}

	public void setGameState(int i) {
		if (i == 1) {
			mGameState = GameState.GAME_START;
		}
	}
}