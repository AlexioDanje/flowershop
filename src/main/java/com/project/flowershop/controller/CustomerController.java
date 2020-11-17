/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.flowershop.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.flowershop.dto.CustomerDto;
import com.project.flowershop.model.Customer;
import com.project.flowershop.repository.CustomerRepository;
import com.project.flowershop.service.CustomerService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author The source
 */
@Controller
@RequestMapping("api/CustomerService")
public class CustomerController {
    
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerRepository cr, CustomerService customerService) {
        customerRepository = cr;
        this.customerService = customerService;
    }

    /**
     * Page to allow user to view campers
     *
     * @since 20200528
     * @author BJM (modified from Fred/Amro's project
     */
    @GetMapping("/customers")
    public String list(Model model) {

        //Go get the campers from the database.
        //Use the jpa repository to get the campers.
        model.addAttribute("customers", loadCustomers());
        return "customers/list";
    }
    @GetMapping("/customers/add")
    public String add(Model model) {
        model.addAttribute("message","Add Customer");
        return "customers/add";
    }
    @GetMapping("/customers/{id}")
    public String getCustomer(Model model, @PathVariable("id") Integer id) {

        //Go get the campers from the database.
        //Use the jpa repository to get the campers.
        Customer customer = customerRepository.getOne(id);
        model.addAttribute("customer", customer);
        return "customers/list";
    }
    /**
     * Page to allow user to add a camper
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project
     */
    @PostMapping("/customers/add")
    public String add(Model model,@Valid @ModelAttribute CustomerDto customerDto) {

        model.addAttribute("message", "Add a customer");

        Customer customer = customerService.save(customerDto);
        model.addAttribute("customer", customer);

        return "customers/add";
    }
    @PostMapping("/customers/file")
    public String save(Model model) throws IOException {
        ArrayList<Customer> customers = loadCustomers();
        Gson gson = new GsonBuilder().create();
        String fileName = new SimpleDateFormat("yyyyMMddHHmm'.json'").format(new Date());
        File f = new File("C:\\fitness\\"+"customers_"+ fileName);
        boolean s =f.createNewFile();
        Writer writer = new FileWriter(f.getCanonicalPath());
        gson.toJson(customers, writer);
        writer.flush();
        writer.close();
        model.addAttribute("customers", customers);

        return "customers/add";
    }
    /**
     * Page to allow user to submit the add a camper. It will put the camper in
     * the database.
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project
     */
    @RequestMapping("/addSubmit")
    public String addSubmit(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

        //If errors put the object back in model and send back to the add page.
        if (result.hasErrors()) {
            System.out.println("Validation error");
            return "camper/add";
        }

        //Save that camper to the database
//        CamperBO camperBO = new CamperBO();
//        camperBO.save(camper);
        customerRepository.save(customer);

        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successAddString = rb.getString("message.customer.saved");

        model.addAttribute("message", successAddString);
        model.addAttribute("customers", loadCustomers());

        return "customers/list";

    }

    /**
     * Page to allow user to edit a camper
     *
     * @since 20201007
     * @author BJM (modified from Fred/Amro's project
     */
    @PutMapping("/update")
    public String edit(Model model, HttpServletRequest request,@Valid @ModelAttribute CustomerDto customerDto) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        Optional<Customer> found = customerRepository.findById(id);
        customerService.save(customerDto);


        model.addAttribute("customer", found);
        return "customers/add";
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
        System.out.println("AATEST - id=" + id);


        try {
            customerRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

        }

        model.addAttribute("customers", loadCustomers());

        return "customers/list";
    }

    private ArrayList<Customer> loadCustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();

    }
    
}
