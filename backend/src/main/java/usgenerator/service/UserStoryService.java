package usgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import usgenerator.model.CombinationCriteria;
import usgenerator.model.GenerationRequest;
import usgenerator.model.UserStory;
import usgenerator.util.PromptBuilder;

import java.util.*;

@Service
public class UserStoryService {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private CombinationTrackerService trackerService;

    @Autowired
    private ExcelService excelService;

    // 🔥 MAIN API
    public Map<Integer, List<UserStory>> generateUserStories(
            GenerationRequest request
    ) {

        String domain = request.getDomain();
        int storyCount = request.getStoryCount();
        List<Integer> combinations = request.getCombinations();

        //trackerService.lockDomainIfRequired(domain);

        Map<Integer, List<UserStory>> result = new HashMap<>();

        for (int combination : combinations) {

            trackerService.validateCombination(combination);

            CombinationCriteria criteria =
                    buildCriteriaFromCombination(combination);

            String prompt =
                    PromptBuilder.buildPrompt(domain, storyCount, criteria);

            // 🔹 Gemini RAW TEXT
            String geminiText =
                    geminiService.callGemini(prompt);

            // 🔹 CONVERT STRING → List<UserStory>
            List<UserStory> stories =
                    parseStories(geminiText, criteria);

            // 🔹 Console print (as you wanted)
            stories.forEach(s ->
                    System.out.println("C" + combination + ": " + s.getUserStory())
            );

            trackerService.markCombinationCompleted(combination);

            // 🔹 Excel generation (CORRECT TYPE)
            excelService.generateExcel(domain, combination, stories);

            result.put(combination, stories);
        }

        return result;
    }

    // 🔹 STRING → OBJECTS
    private List<UserStory> parseStories(
            String text,
            CombinationCriteria criteria
    ) {

        List<UserStory> list = new ArrayList<>();

        String[] lines = text.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            list.add(
                    new UserStory(
                            line.trim(),
                            criteria.isUnique(),
                            criteria.isConflictFree(),
                            criteria.isUniform(),
                            criteria.isIndependent(),
                            criteria.isComplete()
                    )
            );
        }

        return list;
    }

    private CombinationCriteria buildCriteriaFromCombination(int combination) {

        return new CombinationCriteria(
                combination,
                (combination & 1) != 0,
                (combination & 2) != 0,
                (combination & 4) != 0,
                (combination & 8) != 0,
                (combination & 16) != 0
        );
    }

    public Object getCombinationStatus() {
        return trackerService.getStatus();
    }

    public ResponseEntity<?> downloadSingleCombination(int combination) {
        return excelService.downloadSingleExcel(combination);
    }

    public ResponseEntity<?> downloadAllCombinations() {
        return excelService.downloadAllExcel();
    }
}
