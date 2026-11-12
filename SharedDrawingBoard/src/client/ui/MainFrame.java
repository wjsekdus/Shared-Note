package client.ui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import client.core.FeatureRegistry;
import client.core.HistoryManager;
import client.core.ObjectManager;
import client.core.ToolManager;
import common.Feature;

/**
 * 프로그램 기본 창. 상단에 도구 버튼과 실행 취소/다시 실행 버튼, 중앙에 캔버스를 배치한다.
 * 도구 버튼은 FeatureRegistry에 등록된 기능 목록으로 자동 생성되므로, 기능을 추가해도 이 클래스는 수정하지 않는다.
 */
public class MainFrame extends JFrame {

    private final ToolManager toolManager;
    private final HistoryManager historyManager;
    private final CanvasPanel canvasPanel;

    public MainFrame(FeatureRegistry featureRegistry, ObjectManager objectManager,
                     ToolManager toolManager, HistoryManager historyManager) {
        super("공유 낙서장");
        this.toolManager = toolManager;
        this.historyManager = historyManager;
        this.canvasPanel = new CanvasPanel(objectManager, toolManager);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        add(createToolBar(featureRegistry), BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
    }

    private JToolBar createToolBar(FeatureRegistry featureRegistry) {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        ButtonGroup toolGroup = new ButtonGroup();
        for (Feature feature : featureRegistry.getFeatures()) {
            JToggleButton toolButton = new JToggleButton(feature.getName());
            toolButton.addActionListener(e -> toolManager.setCurrentTool(feature.createTool()));
            toolGroup.add(toolButton);
            toolBar.add(toolButton);
        }

        toolBar.addSeparator();

        JButton undoButton = new JButton("실행 취소");
        undoButton.addActionListener(e -> {
            historyManager.undo();
            canvasPanel.repaint();
        });
        toolBar.add(undoButton);

        JButton redoButton = new JButton("다시 실행");
        redoButton.addActionListener(e -> {
            historyManager.redo();
            canvasPanel.repaint();
        });
        toolBar.add(redoButton);

        return toolBar;
    }
}
