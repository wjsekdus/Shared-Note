package client.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import client.core.ObjectManager;
import client.core.ToolManager;
import common.CanvasObject;
import common.Tool;

/**
 * 객체 목록을 그리고, 마우스 입력을 현재 도구에 전달하는 화면 영역.
 * 객체별 그리기 코드는 갖지 않는다. 각 객체의 draw()를 호출할 뿐이다.
 */
public class CanvasPanel extends JPanel {

    private final ObjectManager objectManager;
    private final ToolManager toolManager;

    public CanvasPanel(ObjectManager objectManager, ToolManager toolManager) {
        this.objectManager = objectManager;
        this.toolManager = toolManager;
        setBackground(Color.WHITE);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Tool tool = toolManager.getCurrentTool();
                if (tool != null) {
                    tool.mousePressed(e);
                    repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Tool tool = toolManager.getCurrentTool();
                if (tool != null) {
                    tool.mouseDragged(e);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Tool tool = toolManager.getCurrentTool();
                if (tool != null) {
                    tool.mouseReleased(e);
                    repaint();
                }
            }
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    // 객체들에게 그리기를 요청하는 역할. 실제 그림 그리기는 CavasObject 구현 클래스 draw()
    protected void paintComponent(Graphics g) {
    	// repaint() 가 요청되가나 창 크기가 변경되면 Swing이 호출
        super.paintComponent(g);
        // 현재 캔버스에 존재하는 객체 가져오기
        List<CanvasObject> objects = objectManager.getObjects();
        for (CanvasObject object : objects) {
            // 객체마다 Graphics 복사본을 넘겨, 한 객체가 바꾼 색·선 굵기가 다음 객체에 번지지 않게 한다.
        	// 예) 색상 빨간색 선택 후 다음 그리기 -> 빨간색 으로 출력되는 문제 차단
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            object.draw(g2);
            // 리소스 정리
            g2.dispose();
        }
        // 테스트 확인용: 현재 ObjectManager에 저장된 객체 수
        g.setColor(Color.GRAY);
        g.drawString("객체 수: " + objects.size(), 10, getHeight() - 10);
    }
}
