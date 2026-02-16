function validateForm(domain, storyCount, combinations) {

    if (!domain) {
        alert("Please select a domain");
        return false;
    }

    if (
        storyCount < CONFIG.STORY_COUNT.MIN ||
        storyCount > CONFIG.STORY_COUNT.MAX
    ) {
        alert(
            `Story count must be between ${CONFIG.STORY_COUNT.MIN} and ${CONFIG.STORY_COUNT.MAX}`
        );
        return false;
    }

    if (!combinations || combinations.length === 0) {
        alert("Please select at least one combination");
        return false;
    }

    return true;
}
