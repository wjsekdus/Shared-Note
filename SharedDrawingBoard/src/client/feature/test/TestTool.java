package client.feature.test;

import java.awt.event.MouseEvent;

import client.core.AddObjectCommand;
import client.core.HistoryManager;
import client.core.ObjectManager;
import common.Tool;

/**
 * 캔버스를 클릭하면 클릭한 위치에 TestObject를 만든다.
 * ObjectManager에 직접 넣지 않고 AddObjectCommand로 실행해야 실행 취소/다시 실행 기록이 남는다.
 */
public class TestTool implements Tool {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 60;

    private final ObjectManager objectManager;
    private final HistoryManager historyManager;

    public TestTool(ObjectManager objectManager, HistoryManager historyManager) {
        this.objectManager = objectManager;
        this.historyManager = historyManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TestObject object = new TestObject(e.getX(), e.getY(), WIDTH, HEIGHT);
        historyManager.executeCommand(new AddObjectCommand(objectManager, object));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 테스트 도구는 드래그 동작이 없다.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // 테스트 도구는 놓기 동작이 없다.
    }
}
