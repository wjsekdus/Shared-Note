package client;

import javax.swing.SwingUtilities;

import client.core.FeatureRegistry;
import client.core.HistoryManager;
import client.core.ObjectManager;
import client.core.ToolManager;
import client.feature.test.TestFeature;
import client.ui.MainFrame;

/**
 * 프로그램 시작점. 관리자 객체를 만들고 기능을 등록한 뒤 창을 띄운다.
 * 새 기능을 추가할 때는 아래 register(...) 줄만 하나 추가하면 된다.
 */
public class Main {

    public static void main(String[] args) {
        // Swing 화면은 반드시 이벤트 처리 스레드(EDT)에서 만들고 다뤄야 한다.
        SwingUtilities.invokeLater(() -> {
            ObjectManager objectManager = new ObjectManager();
            ToolManager toolManager = new ToolManager();
            HistoryManager historyManager = new HistoryManager();
            FeatureRegistry featureRegistry = new FeatureRegistry();

            featureRegistry.register(new TestFeature(objectManager, historyManager));

            MainFrame frame = new MainFrame(featureRegistry, objectManager, toolManager, historyManager);
            frame.setVisible(true);
        });
    }
}
