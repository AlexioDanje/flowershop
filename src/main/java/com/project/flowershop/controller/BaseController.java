//package com.project.flowershop.controller;
//
//
//import com.project.flowershop.repository.FlowerOrderTypeRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class BaseController {
//
//    private final FlowerOrderTypeRepository flowerorderTypeRepository;
//
//    public BaseController(FlowerOrderTypeRepository ftr) {
//        flowerorderTypeRepository = ftr;
//    }
//
//    @RequestMapping("/")
//    public String home(HttpSession session) {
//
//        //BJM 20200602 Issue#1 Set the current date in the session
//        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
//        session.setAttribute("currentDate", currentDate);
//
//        //Load the camper types into the session
////        FlowerOrderTypeBO camperTypeBO = new FlowerOrderTypeBO();
////        ArrayList<CamperType> camperTypes = new ArrayList();
////        camperTypes = camperTypeBO.load();
//        ArrayList<FlowerOrderType> flowerorderTypes = (ArrayList<FlowerOrderType>) flowerorderTypeRepository.findAll();
//        session.setAttribute("flowerorderTypes", flowerorderTypes);
//        System.out.println("AA, loaded "+flowerorderTypes.size()+" flower types");
//
//        HashMap<Integer, String> flowerorderTypesMap = FlowerOrderTypeBO.getFlowerOrderTypesMap();
//        flowerorderTypesMap.clear();
//        for(FlowerOrderType current: flowerorderTypes){
//            flowerorderTypesMap.put(current.getId(), current.getDescription());
//        }
//
//
//
//        return "index";
//    }
//
//    @RequestMapping("/about")
//    public String about() {
//        return "other/about";
//    }
//}
