package cn.silence1772.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import cn.silence1772.core.SContants;
import cn.silence1772.core.SScreen;

public class GameScreen extends SScreen {

	Info info = new Info();

	UserSnake userSnake = new UserSnake();
	BaseBody userBody = new BaseBody(userSnake);
	
	AISnake aiSnake = new AISnake();
	BaseBody aiBody = new BaseBody(aiSnake);
	
	Food food = new Food();
	
	int cnt = 0;

	public GameScreen(double width, double height) {
		super(width, height);

		addObject(userBody);
		addObject(userSnake);
		addObject(aiBody);
		addObject(aiSnake);
		addObject(food);
		// 信息在最上层
		addObject(info);

		mGameState = GameState.GAME_START;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// 暂停

		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		super.draw(gc);
	}

	@Override
	public void update() {
		// 暂停
		if (mGameState == GameState.GAME_PAUSE) {
			return;
		}

		// 死了


		// 设置显示生命和积分

		//info.setScore(userSnake.score());
		info.setLength(userSnake.getLength());
		info.setKills(userSnake.getKills());
		info.setScore(userSnake.score());
		info.setAIScore(aiSnake.score());
		// 调用更新操作
		super.update();

		// 不长眼，撞边框上了，减少一条命，复原
		/*if (snake.getX() > SContants.WIDTH || snake.getX() < 0 //
				|| snake.getY() > SContants.HEIGHT || snake.getY() < 0) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}*/
		if (userSnake.getX() < 0) {
			userSnake.setX(SContants.WIDTH);
		}
		if (userSnake.getX() > SContants.WIDTH) {
			userSnake.setX(0);
		}
		if (userSnake.getY() < 0) {
			userSnake.setY(SContants.HEIGHT);
		}
		if (userSnake.getY() > SContants.HEIGHT) {
			userSnake.setY(0);
		}
		
		if (aiSnake.getX() < 0) {
			aiSnake.setX(SContants.WIDTH);
		}
		if (aiSnake.getX() > SContants.WIDTH) {
			aiSnake.setX(0);
		}
		if (aiSnake.getY() < 0) {
			aiSnake.setY(SContants.HEIGHT);
		}
		if (aiSnake.getY() > SContants.HEIGHT) {
			aiSnake.setY(0);
		}

		// 撞到自己的身体了
		/*if (snakeBody.isCollisionWith(snake)) {
			snake.death();
			snakeBody.setVisible(false);
			return;
		}*/

		// 吃到了~！~
		if (userSnake.isCollisionWith(food)) {
			userSnake.addScore(2);
			userSnake.addLength();
			food.setVisible(false);
		}
		
		if (aiSnake.isCollisionWith(food)) {
			aiSnake.addScore(2);
			aiSnake.addLength();
			food.setVisible(false);
		}
		
		if (aiBody.isCollisionWith(userSnake)) {
			userSnake.death();
			userBody.setVisible(false);
			aiSnake.addKills();
			aiSnake.addScore(5);
			return;
		}
		
		if (userBody.isCollisionWith(aiSnake)) {
			aiSnake.death();
			aiBody.setVisible(false);
			userSnake.addKills();
			userSnake.addScore(5);
			return;
		}

		
		if (cnt > 50) {
			cnt = 0;
			aiSnake.updateDir(food);
		} else {
			cnt++;
		}
	}

	@Override
	public void onKeyReleased(KeyEvent event) {
		// 暂停
		if (event.getCode() == KeyCode.SPACE) {
			if (mGameState == GameState.GAME_PAUSE) {
				mGameState = GameState.GAME_CONTINUE;
			} else {
				mGameState = GameState.GAME_PAUSE;
			}
		}

		// 重新开始
		if (event.getCode() == KeyCode.S) {
			info.init();
			userSnake.init();
			userSnake.init();
			food.init();
			mGameState = GameState.GAME_START;
		}
	}

}