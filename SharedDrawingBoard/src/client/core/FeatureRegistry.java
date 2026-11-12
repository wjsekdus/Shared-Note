package client.core;

import java.util.ArrayList;
import java.util.List;

import common.Feature;

/**
 * 프로그램에서 사용할 기능(Feature) 목록. 등록 순서대로 도구 버튼이 만들어진다.
 * 새 기능은 Feature를 구현해 register()로 등록하기만 하면 되므로 이 클래스는 수정할 필요가 없다.
 */
public class FeatureRegistry {

    private final List<Feature> features = new ArrayList<>();

    /**
     * 기능을 등록한다.
     * @return 등록했으면 true, 같은 이름의 기능이 이미 있으면 false
     */
    public boolean register(Feature feature) {
        if (feature == null) {
            throw new IllegalArgumentException("feature는 null일 수 없습니다.");
        }
        for (Feature registered : features) {
            if (registered.getName().equals(feature.getName())) {
                return false;
            }
        }
        features.add(feature);
        return true;
    }

    /** 등록된 기능 목록의 읽기 전용 복사본(등록 순서). */
    public List<Feature> getFeatures() {
        return List.copyOf(features);
    }
}
