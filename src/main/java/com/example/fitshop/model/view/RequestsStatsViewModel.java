package com.example.fitshop.model.view;

public class RequestsStatsViewModel {

    private int authenticatedRequests;
    private int guestRequests;

    public RequestsStatsViewModel() {
    }

    public RequestsStatsViewModel(int authenticatedRequests, int guestRequests) {
        this.authenticatedRequests = authenticatedRequests;
        this.guestRequests = guestRequests;
    }

    public int getAuthenticatedRequests() {
        return authenticatedRequests;
    }

    public RequestsStatsViewModel setAuthenticatedRequests(int authenticatedRequests) {
        this.authenticatedRequests = authenticatedRequests;
        return this;
    }

    public int getGuestRequests() {
        return guestRequests;
    }

    public RequestsStatsViewModel setGuestRequests(int guestRequests) {
        this.guestRequests = guestRequests;
        return this;
    }

    public int getTotal() {
        return this.authenticatedRequests + this.guestRequests;
    }
}
