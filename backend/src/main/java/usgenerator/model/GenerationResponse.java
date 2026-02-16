package usgenerator.model;

import java.util.List;

public class GenerationResponse {

    private int combinationNumber;
    private List<UserStory> userStories;

    public GenerationResponse() {}

    public GenerationResponse(int combinationNumber, List<UserStory> userStories) {
        this.combinationNumber = combinationNumber;
        this.userStories = userStories;
    }

    public int getCombinationNumber() {
        return combinationNumber;
    }

    public void setCombinationNumber(int combinationNumber) {
        this.combinationNumber = combinationNumber;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
