package com.codegym.controller;

import com.codegym.model.Receptionist;
import com.codegym.service.ReceptionisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReceptionistController {
    @Autowired
    ReceptionisService receptionisService;
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("receptionist/create");
        modelAndView.addObject("receptionist", new Receptionist());
        return modelAndView;
    }
    @PostMapping("/save")
    public String saveProduct(Receptionist receptionist, RedirectAttributes redirect){
        receptionisService.addElement(receptionist);
        redirect.addFlashAttribute("message", "Saved product successfully!");
        return "redirect:create";
    }
    @GetMapping("/edit/{id}")
    public  ModelAndView edit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("receptionist/update");
        Receptionist receptionist = receptionisService.findById(id);
        modelAndView.addObject("receptionist", receptionist);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView update(Receptionist receptionist){
        ModelAndView modelAndView = new ModelAndView("receptionist/update");
        receptionisService.updateElement(receptionist.getId(),receptionist);
        modelAndView.addObject("receptionist", receptionist);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("receptionist/delete");
        Receptionist receptionist = receptionisService.findById(id);
        modelAndView.addObject("receptionist", receptionist);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String remove(Receptionist receptionist){
        receptionisService.removeElement(receptionist.getId());
        return "redirect:list";
    }
    @GetMapping("view/{id}")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("receptionist/view");
        Receptionist receptionist = receptionisService.findById(id);
        modelAndView.addObject("receptionist", receptionist);
        return modelAndView;
    }
    @GetMapping("/list")
    public ModelAndView search(@RequestParam ("search")Optional<String> search){
        List<Receptionist> receptionists;
        if (search.isPresent()){
            receptionists = receptionisService.findByName(search.get());
        }
        else {
            receptionists = receptionisService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("receptionist/list");
        modelAndView.addObject("receptionistList",receptionists);
        return modelAndView;
    }
}
