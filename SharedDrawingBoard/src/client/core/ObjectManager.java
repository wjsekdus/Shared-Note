package client.core;

import java.util.ArrayList;
import java.util.List;

import common.CanvasObject;

/**
 * 캔버스 위 객체 목록을 관리한다.
 * 목록 순서 = 추가된 순서 = 화면에 그려지는 순서(나중에 추가된 객체가 위에 그려짐).
 */
public class ObjectManager {

    private final List<CanvasObject> objects = new ArrayList<>();

    /**
     * 객체를 목록 끝에 추가한다.
     * @return 추가했으면 true, 같은 ID의 객체가 이미 있으면 false
     */
    public boolean addObject(CanvasObject object) {
        if (object == null) {
            throw new IllegalArgumentException("object는 null일 수 없습니다.");
        }
        if (getObjectById(object.getId()) != null) {
            return false;
        }
        objects.add(object);
        return true;
    }

    /**
     * ID로 객체를 찾아 제거한다.
     * @return 제거했으면 true, 해당 ID가 없으면 false
     */
    public boolean removeObject(String id) {
        return objects.removeIf(object -> object.getId().equals(id));
    }

    /** ID로 객체를 찾는다. 없으면 null. */
    public CanvasObject getObjectById(String id) {
        for (CanvasObject object : objects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
    }

    /** 현재 객체 목록의 읽기 전용 복사본(추가된 순서). 외부에서 목록을 바꿀 수 없다. */
    public List<CanvasObject> getObjects() {
        return List.copyOf(objects);
    }
}
