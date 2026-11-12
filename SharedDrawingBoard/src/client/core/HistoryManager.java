package client.core;

import java.util.ArrayDeque;
import java.util.Deque;

import common.Command;

/**
 * Command 기록을 관리하여 실행 취소(Undo)와 다시 실행(Redo)을 제공한다.
 */
public class HistoryManager {

    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    /**
     * 명령을 실행하고 기록한다. 새 작업이 생기면 Redo 기록은 의미가 없어지므로 비운다.
     * execute()가 예외를 던지면 기록하지 않고 예외를 그대로 호출한 쪽에 전달한다.
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    /** 가장 최근 작업을 취소한다. 기록이 없으면 아무것도 하지 않는다. */
    public void undo() {
        Command command = undoStack.peek();
        if (command == null) {
            return;
        }
        command.undo();
        // undo()가 성공한 뒤에만 스택을 옮겨, 실패 시 기록이 사라지지 않게 한다.
        undoStack.pop();
        redoStack.push(command);
    }

    /** 가장 최근에 취소한 작업을 다시 실행한다. 기록이 없으면 아무것도 하지 않는다. */
    public void redo() {
        Command command = redoStack.peek();
        if (command == null) {
            return;
        }
        command.execute();
        redoStack.pop();
        undoStack.push(command);
    }
}
