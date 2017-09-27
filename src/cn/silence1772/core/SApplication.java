package cn.silence1772.core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 主页面父类
 * 
 * @author silence1772
 * @date 2017年9月16日
 */
public abstract class SApplication extends Application {
	private Group mGroup;
	private Scene mScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		before();
		mGroup = new Group();
		mScene = new Scene(mGroup, SContants.WIDTH, SContants.HEIGHT);
		after();
		showStage(primaryStage);
	}

	protected void before() {

	}

	protected void after() {

	}

	protected void showStage(Stage stage) {
		stage.setScene(mScene);
		stage.show();
	}

	protected Scene getScene() {
		return mScene;
	}

	protected Group getRoot() {
		return mGroup;
	}

	public void setWindowSize(int width, int height) {
		SContants.init(width, height);
	}

}
