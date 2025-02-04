package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping()
    public String allUsers(ModelMap model) {
        model.addAttribute("all_users", userService.allUsers());
        return "all_users";
    }

    @GetMapping("add")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping()
    public String addDbUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("edit")
    public String editUser(ModelMap model, @RequestParam("id") int id) {
        model.addAttribute("user1", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("edit")
    public String editDbUser(@ModelAttribute("user1") User user) {
        userService.editUser(user);
        return "redirect:/users";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
