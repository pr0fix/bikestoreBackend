package hh.sof003.bikestore.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof003.bikestore.domain.Order;

@Controller
public class OrderController {


    @RequestMapping(value = "/addtocart/{productId}")
    public String addToCart(@PathVariable(name="productId") Long productId, Model model) {
        model.addAttribute("order", new Order());

        return null;
    }
    // TODO
}
