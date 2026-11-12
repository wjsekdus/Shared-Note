package client.core;

import common.CanvasObject;
import common.Command;

/**
 * 객체 추가 작업. 모든 기능이 공통으로 쓰는 명령이라 core에 둔다.
 * 같은 객체 인스턴스를 계속 들고 있으므로 Undo 후 Redo해도 객체 ID가 그대로 유지된다.
 */
public class AddObjectCommand implements Command {

    private final ObjectManager objectManager;
    private final CanvasObject object;

    public AddObjectCommand(ObjectManager objectManager, CanvasObject object) {
        this.objectManager = objectManager;
        this.object = object;
    }

    @Override
    public void execute() {
        if (!objectManager.addObject(object)) {
            throw new IllegalStateException("이미 존재하는 객체 ID입니다: " + object.getId());
        }
    }

    @Override
    public void undo() {
        objectManager.removeObject(object.getId());
    }
}
