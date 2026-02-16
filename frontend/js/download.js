const downloadSingleBtn = document.getElementById("downloadSingleBtn");
const downloadAllBtn = document.getElementById("downloadAllBtn");

downloadSingleBtn.addEventListener("click", () => {
    if (!generatedCombinations.length) {
        alert("No combination generated yet");
        return;
    }

    const combination =
        generatedCombinations[currentCombinationIndex];

    downloadExcelAPI(combination);
});

downloadAllBtn.addEventListener("click", () => {

    if (!generatedCombinations.length) {
        alert("No combination generated yet");
        return;
    }

    downloadAllExcelAPI();
});
