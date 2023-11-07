package hh.sof003.bikestore.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import hh.sof003.bikestore.domain.OrderRepository;


@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping(value="/orderpage", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
            return "orders";
        
    }
    
}
