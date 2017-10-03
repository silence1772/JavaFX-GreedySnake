package cn.silence1772.snake;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;
import cn.silence1772.core.SContants;
import cn.silence1772.core.SObject;

public class BaseBody extends SObject {

	BaseSnake snake;
	int length; 
	LinkedList<Point> list = new LinkedList<Point>();
	private int bias = 4;

	public BaseBody(BaseSnake snake) {
		
		this.snake = snake;
		// 属性绑定
		this.xProperty.bindBidirectional(snake.xProperty());
		this.yProperty.bindBidirectional(snake.yProperty());
		this.widthProperty.bindBidirectional(snake.widthProperty());
		this.heightProperty.bindBidirectional(snake.heightProperty());
		// 初始化位置列表
		init();
	}



	public void init() {
		
		super.init();
		
		this.length = snake.length;
		list.clear();
		for (int i = 0; i < snake.getLength(); i++) {
			Point point = new Point();
			point.setX(getX() - SContants.DISTANCE * i);
			point.setY(getY());
			list.add(point);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		if (snake.getLength() <= 1) {
			return;
		}

		// 原理：移动一次，那么后一个的位置就等于前一个的位置，也就是加入新的first，删除旧的last
		Point firstPoi = list.getFirst();
		// 位移已经达到一个身位再进行处理
		if (firstPoi.getX() + SContants.DISTANCE <= getX() || firstPoi.getX() - SContants.DISTANCE >= getX() //
				|| firstPoi.getY() + SContants.DISTANCE <= getY() || firstPoi.getY() - SContants.DISTANCE >= getY()) {
			Point poi = new Point();
			poi.setX(getX());
			poi.setY(getY());
			// 添加第一个头
			list.addFirst(poi);
			// 如果吃到了就不移除了，没吃到就删除最后一个
			if (this.length < snake.length) {
				this.length = this.length + 1;
			} else {
				// 移除最后一个
				list.removeLast();
			}
		}

		gc.setFill(snake.getSnakeColor());
		for (Point point : list) {
			gc.fillOval(point.getX(), point.getY(), getWidth(), getHeight());
		}
	}

	@Override
	public void update() {
		// 如果不显示了，重新展示
		if (!isVisible()) {
			init();
			setVisible(true);
			return;
		}
	}

	@Override
	public boolean isCollisionWith(SObject baseObject) {
		
		for (Point point : list) {
			if (isCollisionWith(point.getX(), point.getY(), baseObject))
				return true;
		}
		return false;
	}

	private boolean isCollisionWith(double x, double y, SObject baseObject) {
		
		if (x + getWidth() - bias > baseObject.getX() && x < baseObject.getX() + baseObject.getWidth() - bias
				&& y + getHeight() - bias > baseObject.getY() && y < baseObject.getY() + baseObject.getHeight() - bias) {
			return true;
		}
		return false;
	}
}

