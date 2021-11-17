package com.example.fitshop.web;

import com.example.fitshop.service.OwnProfileViewsService;
import com.example.fitshop.service.RequestsStatsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatsController {

    private final RequestsStatsService requestsStatsService;
    private final OwnProfileViewsService ownProfileViewsService;

    public StatsController(RequestsStatsService requestsStatsService, OwnProfileViewsService ownProfileViewsService) {
        this.requestsStatsService = requestsStatsService;
        this.ownProfileViewsService = ownProfileViewsService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/requests")
    public String requestsStats(Model model) {
        model.addAttribute("stats", requestsStatsService.getStats());
        return "requests-stats";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ownProfileViews")
    public String ownProfileViewsStats(Model model) {
        model.addAttribute("views", this.ownProfileViewsService.getViews());
        return "own-profile-views-stats";
    }
}
