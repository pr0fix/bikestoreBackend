package hh.sof003.bikestore.webcontrollers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;
import hh.sof003.bikestore.domain.Order;
import hh.sof003.bikestore.domain.OrderRepository;
import hh.sof003.bikestore.domain.ProductRepository;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    // Show all orders made (admin feature)
    @RequestMapping(value = "/orderpage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    // Show a product ordering form with account pre-filled with the one currently
    // logged in. (user feature)
    @RequestMapping(value = "/orderitem")
    @PreAuthorize("isAuthenticated()")
    public String orderItem(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = accountRepository.findByUsername(username);

        Order order = new Order();
        order.setAccount(account);

        model.addAttribute("order", order);
        model.addAttribute("account", account);
        model.addAttribute("products", productRepository.findAll());

        return "orderitem";
    }

    // Add new order (admin feature)
    @RequestMapping(value = "/addorder")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "addorder";
    }

     // Save order (admin feature)
     @RequestMapping(value = "/saveorder", method = RequestMethod.POST)
     public String saveOrder(Order order) {
         order.setOrderDate(getCurrentDateString());
         order.setDeliveryDate(getDeliveryDate());
         orderRepository.save(order);
         return "redirect:orderconfirmation";
     }

    // Delete order (admin feature)
    @RequestMapping(value = "/deleteOrder/{orderId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteOrder(@PathVariable("orderId") Long orderId, Model model) {
        orderRepository.deleteById(orderId);
        return "redirect:../orderpage";
    }

    // Simple confirmation page for order
    @RequestMapping(value = "/orderconfirmation", method = RequestMethod.GET)
    public String confirmOrder() {
        return "orderconfirmation";
    }

    // Used to generate order date and setting it to current date
    private LocalDate getCurrentDateString() {
        return LocalDate.now();
    }

    // Used to generate imaginary delivery date
    private LocalDate getDeliveryDate() {
        LocalDate today = LocalDate.now();
        int randomNumber = (int) (Math.random() * 30) + 1;
        return today.plusDays(randomNumber);
    }
}
