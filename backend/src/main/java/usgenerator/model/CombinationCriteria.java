package usgenerator.model;

public class CombinationCriteria {

    private int combinationNumber;
    private boolean unique;
    private boolean conflictFree;
    private boolean uniform;
    private boolean independent;
    private boolean complete;

    public CombinationCriteria(int combinationNumber,
                               boolean unique,
                               boolean conflictFree,
                               boolean uniform,
                               boolean independent,
                               boolean complete) {
        this.combinationNumber = combinationNumber;
        this.unique = unique;
        this.conflictFree = conflictFree;
        this.uniform = uniform;
        this.independent = independent;
        this.complete = complete;
    }

    public int getCombinationNumber() {
        return combinationNumber;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isConflictFree() {
        return conflictFree;
    }

    public boolean isUniform() {
        return uniform;
    }

    public boolean isIndependent() {
        return independent;
    }

    public boolean isComplete() {
        return complete;
    }
}
