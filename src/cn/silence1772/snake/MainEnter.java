package cn.silence1772.snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        
		Image image = new Image("image/01.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(600);
		imageView.setFitHeight(130);
		imageView.setLayoutX(170);
		imageView.setLayoutY(100);
		getRoot().getChildren().add(imageView);
		
		Button button = new Button();
		button.setText("开始游戏");
		button.setLayoutX(SContants.WIDTH / 2 - 80);
		button.setLayoutY(SContants.HEIGHT / 2);
		button.setMinWidth(150);
		button.setMinHeight(70);
		button.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				getRoot().getChildren().remove(imageView);
				getRoot().getChildren().remove(button);
				
				GameScreen gameScreen = new GameScreen(SContants.WIDTH, SContants.HEIGHT);
				getRoot().getChildren().add(gameScreen);
				
				gameScreen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						
						if ((event.getX() > 403 && event.getX() < 403 + 150 && event.getY() > 360 && event.getY() < 360 + 40) || 
								(event.getX() > SContants.WIDTH-70 && event.getY() > SContants.HEIGHT-20)) {
							
							getRoot().getChildren().remove(gameScreen);
							getRoot().getChildren().add(imageView);
							getRoot().getChildren().add(button);
						}
					}
					
				});
				
				gameScreen.start();
				gameScreen.initEvents();
				getScene().setFill(Color.web("#ececf4"));
				gameScreen.setGameState(1);
			}
		});
		getRoot().getChildren().add(button);

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
