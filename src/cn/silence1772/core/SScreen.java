package cn.silence1772.core;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * 基础展示类
 * 
 * @author silence1772
 * @date 2017年9月16日
 */
public abstract class SScreen extends Canvas implements SEvent {
	protected enum GameState {
		GAME_MENU, GAME_START, GAME_CONTINUE, GAME_HELP, GAME_SET, GAME_END, GAME_PAUSE
	};

	private List<SObject> mObjects = new ArrayList<SObject>();
	private Timeline timeline;
	private KeyFrame keyFrame;
	private int duration = 10;
	protected GameState mGameState = GameState.GAME_MENU;

	public SScreen(double width, double height) {
		super(width, height);
		initTimeLine();
	}

	public void initEvents() {
		getParent().getScene().setOnKeyPressed(event -> {
			onKeyPressed(event);
		});

		getParent().getScene().setOnKeyReleased(event -> {
			onKeyReleased(event);
		});

		getParent().getScene().setOnMouseMoved(event -> {
			onMouseMoved(event);
		});
	}

	public void onKeyPressed(KeyEvent event) {
		for (SObject wObject : mObjects) {
			wObject.onKeyPressed(event);
		}
	}

	public void onKeyReleased(KeyEvent event) {
		for (SObject wObject : mObjects) {
			wObject.onKeyReleased(event);
		}
	}

	public void onMouseMoved(MouseEvent event) {
		for (SObject wObject : mObjects) {
			wObject.onMouseMoved(event);
		}
	}

	/**
	 * add the object
	 * 
	 * @param baseObject
	 *            the object to be add
	 */
	public void addObject(SObject baseObject) {
		this.mObjects.add(baseObject);
	}

	/**
	 * remove the object
	 * 
	 * @param baseObject
	 *            the object to be remove
	 */
	public void removeObject(SObject baseObject) {
		this.mObjects.remove(baseObject);
	}

	/**
	 * remove the object with the index
	 * 
	 * @param index
	 *            the index of the object
	 */
	public void removeObjectAtIndex(int index) {
		this.mObjects.remove(index);
	}

	/**
	 * draw the objects
	 * 
	 * @param gc
	 */
	public void draw(GraphicsContext gc) {
		gc.setEffect(null);
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < mObjects.size(); i++) {
			SObject wObject = mObjects.get(i);
			if (wObject.isVisible()) {
				wObject.draw(gc);
			}
		}
	}

	/**
	 * update all the objects
	 */
	public void update() {
		for (int i = 0; i < mObjects.size(); i++) {
			SObject wObject = mObjects.get(i);
			if (wObject.isUpdate()) {
				mObjects.get(i).update();
			}
		}
	}

	/**
	 * init the timeline
	 */
	private void initTimeLine() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				draw(getGraphicsContext2D());
				update();
			}
		});
		timeline.getKeyFrames().add(keyFrame);
	}

	/**
	 * start the update timeline
	 */
	public void start() {
		timeline.play();
	}

	/**
	 * pause the update timeline
	 */
	public void pause() {
		timeline.pause();
	}

	/**
	 * stop the update timeline
	 */
	public void stop() {
		timeline.stop();
	}

}
