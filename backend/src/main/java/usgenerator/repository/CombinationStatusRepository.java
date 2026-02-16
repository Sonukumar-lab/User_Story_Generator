package usgenerator.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CombinationStatusRepository {

    private final Set<Integer> completedCombinations = new HashSet<>();

    public void markCompleted(int combination) {
        completedCombinations.add(combination);
    }

    public boolean isCompleted(int combination) {
        return completedCombinations.contains(combination);
    }

    public Set<Integer> getAllCompleted() {
        return completedCombinations;
    }

    public boolean isAllCompleted() {
        return completedCombinations.size() == 32;
    }

    public void reset() {
        completedCombinations.clear();
    }
}
