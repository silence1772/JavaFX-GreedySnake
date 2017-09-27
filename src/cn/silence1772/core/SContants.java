package cn.silence1772.core;


/**
 * 常量类
 * 
 * @author flyfox
 * @date 2014年11月4日
 */
public class SContants {
	/**
	 * 游戏屏幕大小
	 */
	public static int WIDTH = 800, HEIGHT = 600;
	/**
	 * 单位大小
	 */
	public static int MIN_X = 22, MIN_Y = 22;
	/**
	 * 蛇身距离
	 */
	public static int DISTANCE = 4;

	public static void init(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
}
