package com.rag.RagsJobPosts.models.enums;

import lombok.Getter;

@Getter
public enum ExpLevel {
    INTERN(0, 0),
    JUNIOR(0, 2),
    MID(2, 5),
    SENIOR(5, 8),
    LEAD(8, 50);

    private final int minYears;
    private final int maxYears;

    ExpLevel(int minYears, int maxYears) {
        this.minYears = minYears;
        this.maxYears = maxYears;
    }
}
