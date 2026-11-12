package client.feature.test;

import client.core.HistoryManager;
import client.core.ObjectManager;
import common.Feature;
import common.Tool;

/**
 * 테스트 기능. 도구가 객체를 추가하려면 관리자 객체가 필요하므로 생성자로 받아 두었다가 도구에 넘긴다.
 */
public class TestFeature implements Feature {

    private final ObjectManager objectManager;
    private final HistoryManager historyManager;

    public TestFeature(ObjectManager objectManager, HistoryManager historyManager) {
        this.objectManager = objectManager;
        this.historyManager = historyManager;
    }

    @Override
    public String getName() {
        return "테스트 사각형";
    }

    @Override
    public Tool createTool() {
        return new TestTool(objectManager, historyManager);
    }
}
