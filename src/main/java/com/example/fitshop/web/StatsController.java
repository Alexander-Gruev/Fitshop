package com.example.fitshop.web;

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

    public StatsController(RequestsStatsService requestsStatsService) {
        this.requestsStatsService = requestsStatsService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/requests")
    public String requestsStats(Model model) {
        model.addAttribute("stats", requestsStatsService.getStats());
        return "requests-stats";
    }
}
