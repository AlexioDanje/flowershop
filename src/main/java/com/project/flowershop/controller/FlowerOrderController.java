package com.project.flowershop.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.flowershop.dto.CustomerDto;
import com.project.flowershop.model.Customer;
import com.project.flowershop.model.FlowerOrder;
import com.project.flowershop.repository.FlowerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller for the camper functionality of the site
 *
 * @since 20200930
 * @author CIS2232
 */
@Controller
@RequestMapping("/api/OrderService")
public class FlowerOrderController {

    private final FlowerOrderRepository flowerorderRepository;
    @Autowired
    public FlowerOrderController(FlowerOrderRepository fr) {
        flowerorderRepository = fr;
    }

    /**
     * Page to allow user to view campers
     *
     * @since 20200528
     * @author BJM (modified from Fred/Amro's project
     */
    @GetMapping("/orders")
    public String list(Model model) {

        //Go get the campers from the database.
        //Use the jpa repository to get the campers.
        model.addAttribute("floweroders", loadFlowerOrders());
        System.out.println("orders"+loadFlowerOrders().get(0));
        return "flowerorder/list";
    }
    @GetMapping("/orders/add")
    public String add(Model model) {
        model.addAttribute("message","Place an Order");
        return "flowerorder/add";
    }
    @GetMapping("/orders/{id}")
    public String getOrder(Model model, @PathVariable("id") Integer id) {

        //Go get the campers from the database.
        //Use the jpa repository to get the campers.
        FlowerOrder flowerOrder = flowerorderRepository.getOne(id);
        model.addAttribute("customer", flowerOrder);
        return "flowerorder/list";
    }
    /**
     * Page to allow user to add a camper
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project
     */
    @PostMapping("/orders/add")
    public String add(Model model,@Valid @ModelAttribute FlowerOrder flowerOrder) {

        model.addAttribute("message", "Add a customer");
        //flowerOrder.setCreatedDateTime(LocalDateTime.now().toString());
        FlowerOrder Order = flowerorderRepository.save(flowerOrder);
        model.addAttribute("customer", Order);

        return "flowerorder/add";
    }

    @PostMapping("/orders/file")
    public String save(Model model) throws IOException {
        ArrayList<FlowerOrder> flowerOrders = loadFlowerOrders();
        Gson gson = new GsonBuilder().create();
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.json'").format(new Date());
        File f = new File("C:\\fitness\\"+"orders_"+ fileName);
        boolean s =f.createNewFile();
        Writer writer = new FileWriter(f.getCanonicalPath());
         gson.toJson(flowerOrders, writer);
        writer.flush();
        writer.close();
        model.addAttribute("floweroders", flowerOrders);

        return "flowerorder/list";
    }

    /**
     * Page to allow user to submit the add a camper. It will put the camper in
     * the database.
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project
     */
    @RequestMapping("/addSubmit")
    public String addSubmit(Model model, @Valid @ModelAttribute("flowerorder") FlowerOrder flowerorder, BindingResult result) {

        //If errors put the object back in model and send back to the add page.
        if (result.hasErrors()) {
            System.out.println("Validation error");
            return "flowerorder/add";
        }

        //Save that camper to the database
//        CamperBO camperBO = new CamperBO();
//        camperBO.save(camper);
        flowerorderRepository.save(flowerorder);

        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successAddString = rb.getString("message.flowerorder.saved");

        model.addAttribute("message", successAddString);
        model.addAttribute("flowerorders", loadFlowerOrders());

        return "flowerorder/list";

    }

    /**
     * Page to allow user to edit a camper
     *
     * @since 20201007
     * @author BJM (modified from Fred/Amro's project
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("AATEST - id=" + id);
        flowerorderRepository.findById(id).ifPresent(found -> model.addAttribute("flowerorder", found));

        return "flowerorder/update";
    }

    /**
     * Page to allow user to delete a camper
     *
     * @since 20201009
     * @author BJM
     */
    @RequestMapping("/delete")
    public String delete(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("BJTEST - id=" + id);

        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successString;
        try {
            flowerorderRepository.deleteById(id);
            successString = rb.getString("message.flowerorder.deleted") + " (" + id + ")";
        } catch (EmptyResultDataAccessException e) {
            successString = rb.getString("message.flowerorder.deleted.error") + " (" + id + ")";
        }

        model.addAttribute("message", successString);
        model.addAttribute("flowerorders", loadFlowerOrders());

        return "flowerorder/list";
    }

    public ArrayList<FlowerOrder> loadFlowerOrders() {

        return (ArrayList<FlowerOrder>) flowerorderRepository.findAll();

    }

}
