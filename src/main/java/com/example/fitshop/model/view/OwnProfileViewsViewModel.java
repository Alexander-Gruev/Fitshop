package com.example.fitshop.model.view;

public class OwnProfileViewsViewModel {

    private int views;

    public OwnProfileViewsViewModel() {
    }

    public OwnProfileViewsViewModel(int views) {
        this.views = views;
    }

    public OwnProfileViewsViewModel setViews(int views) {
        this.views = views;
        return this;
    }

    public int getViews() {
        return views;

    }
}
