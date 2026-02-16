package usgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usgenerator.model.GenerationRequest;
import usgenerator.service.UserStoryService;

@RestController
@RequestMapping("/api/user-stories")
@CrossOrigin(origins = "*")
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    // 🔥 FIXED: Generate User Stories (STRUCTURED DATA)
    @PostMapping("/generate")
    public ResponseEntity<?> generateUserStories(
            @RequestBody GenerationRequest request
    ) {
        return ResponseEntity.ok(
                userStoryService.generateUserStories(request)
        );
    }

    // ✅ Combination completion status (1–32)
    @GetMapping("/combinations/status")
    public ResponseEntity<?> getCombinationStatus() {
        return ResponseEntity.ok(
                userStoryService.getCombinationStatus()
        );
    }

    // ✅ Download single combination Excel
    @GetMapping("/download/{combination}")
    public ResponseEntity<?> downloadCombinationExcel(
            @PathVariable int combination
    ) {
        return userStoryService.downloadSingleCombination(combination);
    }

    // ✅ Download all combinations Excel
    @GetMapping("/download/all")
    public ResponseEntity<?> downloadAllCombinations() {
        return userStoryService.downloadAllCombinations();
    }
}
