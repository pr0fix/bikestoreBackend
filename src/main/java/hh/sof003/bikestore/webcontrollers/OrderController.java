package hh.sof003.bikestore.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // Admin feature which shows all orders made
    @RequestMapping(value = "/orderpage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveOrder(Order order) {
        orderRepository.save(order);
        return "redirect:orderpage";
    }

    // TODO: Delete order (admin feature)
    // @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.GET)
    // public String deleteOrder(@PathVariable("orderId") Long orderId, Model model) {
    //     orderRepository.deleteById(orderId);
    //     return "redirect:../orders";
    // }

    // TODO: Edit order (admin feature)
    // @RequestMapping(value = "/edit/{orderId}", method = RequestMethod.GET)
    // @PreAuthorize("hasAuthority('ADMIN')")
    // public String editOrder(@PathVariable("orderId") Long orderId, Model model) {
    //     model.addAttribute(("order"), orderRepository.findById(orderId));
    //     model.addAttribute("accounts", accountRepository.findAll());
    //     model.addAttribute("products", productRepository.findAll());
    //     return "editorder";
    // }
}
