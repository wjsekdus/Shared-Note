package common;

/**
 * 프로그램에 추가되는 하나의 기능(표, 캘린더 등) 단위.
 * FeatureRegistry에 등록하면 상단 도구 버튼으로 나타나고,
 * 버튼을 누르면 createTool()로 만든 도구가 현재 도구가 된다.
 */
public interface Feature {

    /** 기능 이름. 도구 버튼에 표시되며, 등록 시 중복 판단 기준이 된다. */
    String getName();

    /** 이 기능이 사용할 도구를 새로 만들어 반환한다. */
    Tool createTool();
}
