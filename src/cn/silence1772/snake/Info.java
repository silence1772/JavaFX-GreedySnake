package cn.silence1772.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import cn.silence1772.core.SContants;
import cn.silence1772.core.SObject;

public class Info extends SObject {

	private boolean isLive;
	private int score = 0;
	private int aiScore = 0;
	private int length = 0;
	private int kills = 0;
	private int hp = 0;
	private CountDown countDown = new CountDown(300);
    
	public Info() {
		
		init();
		countDown.start();
	}

	@Override
	public void init() {
		
		super.init();
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		gc.setFill(Color.web("#666"));
		gc.setFont(Font.font(null, FontWeight.BLACK, 20));
		gc.fillText("长度：" + getLength(), 16, 34);
		gc.fillText("击杀：" + getKills(), 16, 62);
		
		gc.setFill(Color.web("#999", 0.5));
		gc.fillRoundRect(SContants.WIDTH-160, 5, 150, 70, 15, 15);
		gc.fillRoundRect(SContants.WIDTH/2 - 80, SContants.HEIGHT - 18, 150, 20, 10, 10);
		
		gc.setFill(Color.web("#666"));
		gc.fillText("我的分数：" + getScore(), SContants.WIDTH-150, 30);
		gc.fillText("敌方分数：" + getAIScore(), SContants.WIDTH-150, 60);
		
		gc.setFont(Font.font(null, FontWeight.BLACK, 12));
		gc.fillText(" ESC键暂停", SContants.WIDTH/2 - 40, SContants.HEIGHT - 4);
		
		gc.setFont(Font.font(null, FontWeight.BLACK, 40));
		gc.fillText(String.format("%02d", (countDown.getCnt() / 60)) + ":" + String.format("%02d", (countDown.getCnt() % 60)), SContants.WIDTH/2 - 50, 50);
		
		gc.setFill(Color.web("#80b4ff"));
		gc.fillRoundRect(SContants.WIDTH - 70, SContants.HEIGHT - 20, 90, 40, 10, 10);
		gc.setFill(Color.web("#fff"));
		gc.setFont(Font.font(null, FontWeight.BLACK, 12));
		gc.fillText("点此返回", SContants.WIDTH - 60, SContants.HEIGHT - 4);
	}

	@Override
	public void update() {

	}

	public boolean isLive() {
		
		return isLive;
	}

	public void setLive(boolean isLive) {
		
		this.isLive = isLive;
	}

	public int getScore() {
		
		return score;
	}

	public void setScore(int score) {
		
		this.score = score;
	}
	
	public int getAIScore() {
		
		return aiScore;
	}

	public void setAIScore(int aiScore) {
		
		this.aiScore = aiScore;
	}
	
	public void setLength(int length) {
		
		this.length = length;
	}
	
	public int getLength() {
		
		return length;
	}
	
	public void setKills(int kills) {
		
		this.kills = kills;
	}
	
	public int getKills() {
		
		return kills;
	}

	public int getHp() {
		
		return hp;
	}

	public void setHp(int hp) {
		
		this.hp = hp;
	}

	public int getTimes() {
		
		return countDown.getCnt();
	}
}
