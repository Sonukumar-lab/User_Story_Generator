window.CONFIG = {
    API_BASE_URL: "http://localhost:8080/api/user-stories",

    DOMAINS: [
        "Food Delivery",
        "E-Commerce",
        "Healthcare",
        "Banking",
        "Education"
    ],

    STORY_COUNT: {
        MIN: 1,
        MAX: 25
    },

    COMBINATIONS: Array.from({ length: 32 }, (_, i) => i + 1)
};
