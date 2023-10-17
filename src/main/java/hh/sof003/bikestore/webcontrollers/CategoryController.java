package hh.sof003.bikestore.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof003.bikestore.domain.Category;
import hh.sof003.bikestore.domain.CategoryRepository;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    // Saves added category
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:categorylist";
    }

    // Lists all categories
    @RequestMapping(value = "/categorylist", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    // Adds a category into categorylist
    @RequestMapping(value = "/addcategory", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return ("addcategory");
    }
}
