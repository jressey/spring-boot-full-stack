package userManager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import userManager.domain.User;
import userManager.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @RequestMapping("/{id}")
    public String editUser(@PathVariable(value="id") String id, Model model) {
        User user = userRepository.findOne(Long.parseLong(id));
        model.addAttribute("user", user);
        return "users/form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user, Model model) {
    	userRepository.save(user);
        model.addAttribute("user", user);
    	return "users/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value="id") String id, Model model) {
        userRepository.delete(userRepository.findOne(Long.parseLong(id)));
        model.addAttribute("users", userRepository.findAll());
    	return "users/index";
    }
}