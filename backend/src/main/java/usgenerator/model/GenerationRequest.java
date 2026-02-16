package usgenerator.model;

import java.util.List;

public class GenerationRequest {

    private String domain;
    private int storyCount;
    private List<Integer> combinations;

    // Required by Spring / Jackson
    public GenerationRequest() {
    }

    // Optional convenience constructor
    public GenerationRequest(String domain, int storyCount, List<Integer> combinations) {
        this.domain = domain;
        this.storyCount = storyCount;
        this.combinations = combinations;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getStoryCount() {
        return storyCount;
    }

    public void setStoryCount(int storyCount) {
        this.storyCount = storyCount;
    }

    public List<Integer> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Integer> combinations) {
        this.combinations = combinations;
    }

    // 🔒 Safety helper (optional use in service)
    public boolean isValid() {
        return domain != null
                && !domain.isBlank()
                && storyCount > 0
                && combinations != null
                && !combinations.isEmpty();
    }
}
