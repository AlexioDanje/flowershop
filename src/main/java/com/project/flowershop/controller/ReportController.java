package com.project.flowershop.controller;


import com.project.flowershop.dto.ReportDto;
import com.project.flowershop.model.FlowerOrder;
import com.project.flowershop.repository.FlowerOrderRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Controller for the report functionality of the site
 *
 * @since 20201021
 * @author CIS2232
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private final FlowerOrderRepository flowerorderRepository;

    public ReportController(FlowerOrderRepository fr) {
        flowerorderRepository = fr;
    }

    /**
     * Page to allow user to specify birthday
     *
     * @since 20201021
     * @author BJM (modified from Fred/Amro's project
     */
    @RequestMapping("/month")
    public String getMonth(Model model) {

        return "report/birthday";
    }

    /**
     * Page to allow user to submit the birthday report
     *
     * @since 20201021
     * @author BJM (modified from Fred/Amro's project
     */
    @RequestMapping("/submitMonth")
    public String addSubmit(Model model, @RequestParam("orderDate1")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate1,@RequestParam("orderDate2")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate2) {
        System.out.println(loadFlowerOrdersByMonth(orderDate1,orderDate2));
        model.addAttribute("floweroders", loadFlowerOrdersByMonth(orderDate1,orderDate2));
        return "flowerorder/list";

    }

    public ArrayList<FlowerOrder> loadFlowerOrdersByMonth(LocalDate from, LocalDate to) {

        return flowerorderRepository.findByOrderDateBetween(from,to);

    }

}
