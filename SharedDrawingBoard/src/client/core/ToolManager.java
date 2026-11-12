package client.core;

import common.Tool;

/**
 * 현재 선택된 도구를 보관한다. 도구만 바꿀 뿐 객체 데이터는 건드리지 않는다.
 */
public class ToolManager {

    private Tool currentTool;

    /** 현재 도구를 바꾼다. null이면 "선택된 도구 없음" 상태가 된다. */
    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
    }

    /** 현재 도구. 아직 선택하지 않았다면 null을 반환하므로 호출하는 쪽에서 확인해야 한다. */
    public Tool getCurrentTool() {
        return currentTool;
    }
}
