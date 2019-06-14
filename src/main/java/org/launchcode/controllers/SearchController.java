package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        ArrayList<HashMap<String, String>> jobs = JobData.findAll();

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String searchResults(Model model,
                                @RequestParam String searchType, @RequestParam String searchTerm) {
        if (searchType.equals("all")) {

            model.addAttribute("positions", JobData.findByValue(searchTerm));
            model.addAttribute("columns", ListController.columnChoices);
        } else {
            model.addAttribute("positions", JobData.findByColumnAndValue(searchTerm, searchType));
            model.addAttribute("columns", ListController.columnChoices);
        }
        return "search";
    }
}








