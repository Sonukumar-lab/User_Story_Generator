document.getElementById("prevBtn")
    .addEventListener("click", () => {
        if (currentCombinationIndex > 0) {
            currentCombinationIndex--;
            updateCombinationLabel();
            renderTable();
        }
    });

document.getElementById("nextBtn")
    .addEventListener("click", () => {
        if (currentCombinationIndex < generatedCombinations.length - 1) {
            currentCombinationIndex++;
            updateCombinationLabel();
            renderTable();
        }
    });
