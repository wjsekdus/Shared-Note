package client.feature.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.UUID;

import common.CanvasObject;

/**
 * 아키텍처 검증용 사각형 객체. 화면에 ID 앞 8자리를 표시해 객체 구분과 ID 유지 여부를 눈으로 확인할 수 있다.
 */
public class TestObject implements CanvasObject {

    private final String id = UUID.randomUUID().toString();
    private int x;
    private int y;
    private final int width;
    private final int height;

    public TestObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(180, 210, 255));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLUE);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawString(id.substring(0, 8), x + 5, y + 15);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public boolean contains(int px, int py) {
        return px >= x && px < x + width && py >= y && py < y + height;
    }
}
