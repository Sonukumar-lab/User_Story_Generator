package usgenerator.service;

import org.springframework.stereotype.Service;

@Service
public class CombinationTrackerService {

    /**
     * Domain locking disabled
     */
    public void lockDomainIfRequired(String domain) {
        // No locking logic
    }

    /**
     * Allow same combination multiple times
     */
    public void validateCombination(int combination) {
        // No validation needed
        // Regeneration allowed
    }

    /**
     * No tracking required
     */
    public void markCombinationCompleted(int combination) {
        // Do nothing
    }

    /**
     * Optional status endpoint
     */
    public Object getStatus() {
        return "Tracking Disabled";
    }
}
