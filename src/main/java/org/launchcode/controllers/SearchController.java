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
//        ArrayList<HashMap<String, String>> jobs = JobData.findAll();
//        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping("results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> positions = new ArrayList<>();

        if (!searchType.equals("all")) {
            positions = JobData.findByColumnAndValue(searchType, searchTerm);
        } else {
            if (!searchTerm.isEmpty()){
                positions = JobData.findByValue(searchTerm);
            } else {
                positions = JobData.findAll();
            }
//
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("positions", positions);




        return "search";
    }
}








