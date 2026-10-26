package com.mun.mundelegation.contoller;

import com.mun.mundelegation.Country;
import com.mun.mundelegation.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String listCountries(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        return "countries/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("country", new Country());
        return "countries/form";
    }

    @PostMapping("/save")
    public String saveCountry(@ModelAttribute Country country, Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            countryService.addCountry(country);
            redirectAttributes.addFlashAttribute("success",
                    "Country registered successfully!");
            return "redirect:/countries";
        } catch (IllegalArgumentException e) {
            model.addAttribute("country", country);
            model.addAttribute("error", e.getMessage());
            return "countries/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Country country = countryService.getCountryById(id);

        if (country == null) {
            return "redirect:/countries";
        }

        model.addAttribute("country", country);
        return "countries/form";
    }

    @PostMapping("/update")
    public String updateCountry(@ModelAttribute Country country, Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            countryService.updateCountry(country);
            redirectAttributes.addFlashAttribute("success",
                    "Country updated successfully!");
            return "redirect:/countries";
        } catch (IllegalArgumentException e) {
            model.addAttribute("country", country);
            model.addAttribute("error", e.getMessage());
            return "countries/form";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        countryService.deleteCountry(id);
        redirectAttributes.addFlashAttribute("success",
                "Country deleted successfully!");
        return "redirect:/countries";
    }
}

