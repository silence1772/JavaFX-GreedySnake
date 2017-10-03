package cn.silence1772.snake;

import cn.silence1772.core.SContants;
import cn.silence1772.core.SObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver extends SObject{

	String string;
	int myScore;
	int aiScore;
	
	public GameOver(int score1, int score2) {
		
		if (score1 > score2) {
			string = "You're Winner!";
		} else {
			string = "Sorry! You fail";
		}
		myScore = score1;
		aiScore = score2;
	}
	
	
	
	@Override
	public void draw(GraphicsContext gc) {

		gc.setFill(Color.web("#333", 0.6));
		gc.fillRect(0, 0, SContants.WIDTH, SContants.HEIGHT);
		
		gc.setFill(Color.web("#ddd"));
		gc.fillRoundRect(300, 180, 350, 240, 20, 20);
		
		gc.setFont(Font.font(null, FontWeight.BLACK, 40));
		gc.setFill(Color.web("#333"));
		gc.fillText(string, 340, 230);
		
		gc.setFont(Font.font(null, FontWeight.BLACK, 20));
		gc.fillText("你的得分：" + myScore, 350, 280);
		gc.fillText("敌方得分：" + aiScore, 350, 320);
		
		gc.setFill(Color.web("#80b4ff"));
		gc.fillRoundRect(403, 360, 150, 40, 15, 15);
		
		gc.setFill(Color.web("#fff"));
		gc.fillText("返回", 460, 386);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
