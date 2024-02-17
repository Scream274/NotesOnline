package com.mynotes.NotesOnline.models.enums;

public enum Priority {
    LOW("green", "#cfffcf"),
    MEDIUM("goldenrod", "#ffffd4"),
    HIGH("red", "#fff3f3");

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
