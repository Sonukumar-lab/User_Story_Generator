package usgenerator.model;

public class UserStory {

    private String userStory;
    private boolean unique;
    private boolean conflictFree;
    private boolean uniform;
    private boolean independent;
    private boolean complete;

    public UserStory() {}

    public UserStory(String userStory,
                     boolean unique,
                     boolean conflictFree,
                     boolean uniform,
                     boolean independent,
                     boolean complete) {
        this.userStory = userStory;
        this.unique = unique;
        this.conflictFree = conflictFree;
        this.uniform = uniform;
        this.independent = independent;
        this.complete = complete;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isConflictFree() {
        return conflictFree;
    }

    public void setConflictFree(boolean conflictFree) {
        this.conflictFree = conflictFree;
    }

    public boolean isUniform() {
        return uniform;
    }

    public void setUniform(boolean uniform) {
        this.uniform = uniform;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
