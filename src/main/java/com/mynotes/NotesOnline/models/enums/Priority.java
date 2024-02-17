package com.mynotes.NotesOnline.models.enums;

public enum Priority {
    HIGH("red", "#fff3f3"),
    MEDIUM("goldenrod", "#ffffd4"),
    LOW("green", "#cfffcf");

    private final String colorCode;
    private final String backColorCode;

    Priority(String colorCode, String backColorCode) {
        this.colorCode = colorCode;
        this.backColorCode = backColorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getBackColorCode() {
        return backColorCode;
    }
}
