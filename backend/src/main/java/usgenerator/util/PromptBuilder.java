package usgenerator.util;

import usgenerator.model.CombinationCriteria;

public class PromptBuilder {

    private PromptBuilder() {}

    public static String buildPrompt(
            String domain,
            int storyCount,
            CombinationCriteria criteria
    ) {

        return """
        You are an AI-Based User Story Generator.

        DOMAIN: %s
        USER STORY COUNT: %d

        HARD FORMAT RULE (NON-NEGOTIABLE):
        As a <role>, I want <goal> so that <reason>.

        CRITERIA (STRICTLY FOLLOW):
        - Unique: %s
        - Conflict Free: %s
        - Uniform: %s
        - Independent: %s
        - Complete: %s

        INTERNAL PROCESS:
        - Generate
        - Validate
        - Auto-correct
        - Repeat until PERFECT

        OUTPUT:
        - Only final validated user stories
        - No explanation
        """.formatted(
                domain,
                storyCount,
                yesNo(criteria.isUnique()),
                yesNo(criteria.isConflictFree()),
                yesNo(criteria.isUniform()),
                yesNo(criteria.isIndependent()),
                yesNo(criteria.isComplete())
        );
    }

    private static String yesNo(boolean value) {
        return value ? "Yes" : "No";
    }
}
