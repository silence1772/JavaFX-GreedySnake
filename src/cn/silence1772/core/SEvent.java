package cn.silence1772.core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * 基础事件类，利用JDK8默认时间特性
 * 
 * @author silence1772
 * @date 2017年9月16日
 */
public interface SEvent {
	/**
	 * 初始化事件
	 * 
	 */
	default public void init() {
	}

	/**
	 * 默认按键按下事件
	 * 
	 * @param event
	 */
	default public void onKeyPressed(KeyEvent event) {
	}

	/**
	 * 默认按键释放事件
	 * 
	 * @param event
	 */
	default public void onKeyReleased(KeyEvent event) {
	}

	/**
	 * 默认鼠标移动事件
	 * 
	 * @param event
	 */
	default public void onMouseMoved(MouseEvent event) {
	}
}