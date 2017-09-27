package cn.silence1772.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage; 

import cn.silence1772.core.SApplication;
import cn.silence1772.core.SContants;

public class MainEnter extends SApplication{
	@Override
	protected void before() {
		setWindowSize(960, 640);
	}
	
	@Override
	protected void after() {
		for (int i = 0; i < SContants.WIDTH; i += 18) {
			Line line = new Line(i, 0, i, SContants.HEIGHT);
			line.setStroke(Color.web("#999", 0.5));
			getRoot().getChildren().add(line);
		}
		for (int i = 0; i < SContants.HEIGHT; i += 18) {
			Line line = new Line(0, i, SContants.WIDTH, i);
			line.setStroke(Color.web("#999", 0.5));
			getRoot().getChildren().add(line);
		}
		
		
        
		//Screen screen = new Screen(SContants.WIDTH, SContants.HEIGHT);
		GameScreen gameScreen = new GameScreen(SContants.WIDTH, SContants.HEIGHT);
		getRoot().getChildren().add(gameScreen);
		
		gameScreen.start();
		gameScreen.initEvents();
		
		getScene().setFill(Color.web("#ececf4"));
		

	}
	
	@Override
	protected void showStage(Stage stage) {
		super.showStage(stage);
		stage.setTitle("Greedy Snake");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
