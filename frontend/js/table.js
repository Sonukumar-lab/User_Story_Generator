let currentCombinationIndex = 0;
let generatedCombinations = [];
let generatedStories = {}; // 🔥 KEY FIX

function setGeneratedCombinations(combinations) {
    generatedCombinations = combinations;
    currentCombinationIndex = 0;
    updateCombinationLabel();
}

function setGeneratedStories(data) {
    generatedStories = data;
    renderTable();
}

function updateCombinationLabel() {
    const label = document.getElementById("currentCombinationLabel");

    if (!generatedCombinations.length) {
        label.innerText = "No Combination";
        return;
    }

    label.innerText =
        "Combination " + generatedCombinations[currentCombinationIndex];
}

function renderTable() {
    const tbody = document.querySelector("#storyTable tbody");
    tbody.innerHTML = "";

    const combination =
        generatedCombinations[currentCombinationIndex];

    const stories = generatedStories[combination];

    if (!stories || !stories.length) return;

    stories.forEach(s => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${s.userStory}</td>
            <td>${s.unique ? "✔" : "✖"}</td>
            <td>${s.conflictFree ? "✔" : "✖"}</td>
            <td>${s.uniform ? "✔" : "✖"}</td>
            <td>${s.independent ? "✔" : "✖"}</td>
            <td>${s.complete ? "✔" : "✖"}</td>
        `;

        tbody.appendChild(tr);
    });

    document
        .getElementById("resultSection")
        .classList.remove("hidden");
}
