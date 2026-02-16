async function generateUserStoriesAPI(payload) {

    const response = await fetch(
        `${CONFIG.API_BASE_URL}/generate`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        }
    );

    if (!response.ok) {
        throw new Error("Failed to generate user stories");
    }

    return response.json();
}

async function downloadExcelAPI(combination) {
    window.location.href =
        `${CONFIG.API_BASE_URL}/download/${combination}`;
}

function downloadAllExcelAPI() {
    window.location.href =
        `${CONFIG.API_BASE_URL}/download/all`;
}

