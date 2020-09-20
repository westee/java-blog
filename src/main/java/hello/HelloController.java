package hello;

import hello.service.OrderService;
import hello.service.User;
import hello.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RestController
public class HelloController {
    private OrderService orderService;
    private UserService userService;

    @Inject
    public HelloController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index() {
        return "G1111!";
    }

    @GetMapping(value = "/hello/{id}")
    public User getUser(@PathVariable("id") int id) {
       return userService.getUserById( id);
    }

}