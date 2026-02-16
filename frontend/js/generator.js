// ================================
// WAIT UNTIL DOM LOAD
// ================================
document.addEventListener("DOMContentLoaded", () => {

    // ================================
    // ELEMENTS
    // ================================
    const generateBtn = document.getElementById("generateBtn");
    const loader = document.getElementById("loader");
    const combinationContainer = document.getElementById("combinationContainer");

    // Safety check
    if (!combinationContainer) {
        console.error("combinationContainer not found in DOM");
        return;
    }

    if (!window.CONFIG || !CONFIG.COMBINATIONS) {
        console.error("CONFIG not loaded properly");
        return;
    }

    // ================================
    // RENDER COMBINATIONS
    // ================================
    function getCombinationDetails(num) {
        return {
            unique: (num & 1) !== 0,
            conflictFree: (num & 2) !== 0,
            uniform: (num & 4) !== 0,
            independent: (num & 8) !== 0,
            complete: (num & 16) !== 0
        };
    }

    function renderCombinations() {
        combinationContainer.innerHTML = "";

        CONFIG.COMBINATIONS.forEach(num => {

            const wrapper = document.createElement("div");
            wrapper.classList.add("combo-item");

            const input = document.createElement("input");
            input.type = "checkbox";
            input.id = `c${num}`;
            input.value = num;

            const label = document.createElement("label");
            label.setAttribute("for", `c${num}`);
            label.innerText = `C${num}`;

            const tooltip = document.createElement("div");
            tooltip.classList.add("combo-tooltip");

            const details = getCombinationDetails(num);

            tooltip.innerHTML = `
                <div>Unique <span class="${details.unique ? 'yes' : 'no'}">${details.unique ? "✔" : "✖"}</span></div>
                <div>Conflict Free <span class="${details.conflictFree ? 'yes' : 'no'}">${details.conflictFree ? "✔" : "✖"}</span></div>
                <div>Uniform <span class="${details.uniform ? 'yes' : 'no'}">${details.uniform ? "✔" : "✖"}</span></div>
                <div>Independent <span class="${details.independent ? 'yes' : 'no'}">${details.independent ? "✔" : "✖"}</span></div>
                <div>Complete <span class="${details.complete ? 'yes' : 'no'}">${details.complete ? "✔" : "✖"}</span></div>
            `;

            wrapper.appendChild(input);
            wrapper.appendChild(label);
            wrapper.appendChild(tooltip);

            combinationContainer.appendChild(wrapper);
        });
    }

    // Call render on page load
    renderCombinations();

    // ================================
    // GENERATE BUTTON CLICK
    // ================================
    if (generateBtn) {
        generateBtn.addEventListener("click", async () => {

            const domain = document.getElementById("domainSelect").value;
            const storyCount = Number(document.getElementById("storyCount").value);

            const selectedCombinations = Array.from(
                document.querySelectorAll("#combinationContainer input:checked")
            ).map(cb => Number(cb.value));

            if (!validateForm(domain, storyCount, selectedCombinations)) {
                return;
            }

            const payload = {
                domain,
                storyCount,
                combinations: selectedCombinations
            };

            loader.classList.remove("hidden");

            try {
                const response = await generateUserStoriesAPI(payload);
                console.log("Backend response:", response);

                // assuming backend returns:
                // { 1: [...stories], 2: [...stories] }

                setGeneratedCombinations(Object.keys(response).map(Number));
                setGeneratedStories(response);

                alert("User stories generated successfully");

            } catch (err) {
                console.error(err);
                alert("Generation failed");
            } finally {
                loader.classList.add("hidden");
            }
        });
    }

});
