package common;

import java.awt.Graphics2D;

/**
 * 캔버스에 배치되는 모든 객체(그림, 텍스트, 표, 캘린더 등)의 공통 인터페이스.
 * 구현 클래스는 생성 시 고유 ID(UUID 권장)를 정하고, 이후 절대 바꾸지 않아야 한다.
 * (향후 네트워크 동기화에서 객체를 ID로 찾아 수정/삭제하기 때문)
 */
public interface CanvasObject {

    /** 객체의 고유 ID. 객체가 존재하는 동안 항상 같은 값을 반환해야 한다. */
    String getId();

    /** 객체 스스로 자신을 화면에 그린다. */
    void draw(Graphics2D g);

    /** 객체를 (dx, dy)만큼 이동한다. */
    void move(int dx, int dy);

    /** 좌표 (x, y)가 객체 영역 안에 있으면 true. 선택 판정에 사용한다. */
    boolean contains(int x, int y);
}
