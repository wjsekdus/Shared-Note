package common;

/**
 * 실행 취소/다시 실행이 가능한 사용자 작업(객체 추가, 삭제, 이동 등).
 * 작업에 실패하면 예외를 던져야 HistoryManager가 잘못된 기록을 남기지 않는다.
 */
public interface Command {

    /** 작업을 실행한다. 다시 실행(Redo) 시에도 이 메서드가 호출된다. */
    void execute();

    /** execute()로 바뀐 상태를 이전 상태로 되돌린다. */
    void undo();
}
