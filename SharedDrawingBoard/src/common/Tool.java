package common;

import java.awt.event.MouseEvent;

/**
 * 사용자가 선택하는 도구(펜, 표, 캘린더 등)의 공통 인터페이스.
 * CanvasPanel은 마우스 이벤트를 현재 도구에 그대로 전달하고,
 * 실제 동작은 각 Tool 구현 클래스가 결정한다.
 */
public interface Tool {

    void mousePressed(MouseEvent e);

    void mouseDragged(MouseEvent e);

    void mouseReleased(MouseEvent e);
}
