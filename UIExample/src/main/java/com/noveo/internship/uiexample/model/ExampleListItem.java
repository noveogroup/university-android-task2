package com.noveo.internship.uiexample.model;

public class ExampleListItem {

    private String title;
    private String leftText;
    private String rightText;

    public ExampleListItem(String title, String leftText, String rightText) {
        this.title = title;
        this.leftText = leftText;
        this.rightText = rightText;
    }

    public String getTitle() {
        return title;
    }

    public String getLeftText() {
        return leftText;
    }

    public String getRightText() {
        return rightText;
    }
}
