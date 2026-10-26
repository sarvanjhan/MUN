package com.mun.mundelegation.contoller;

import com.mun.mundelegation.model.Delegate;
import com.mun.mundelegation.repository.CountryRepository;
import com.mun.mundelegation.repository.CommitteeRepository;
import com.mun.mundelegation.service.DelegateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.time.LocalDate;

@Controller
@RequestMapping("/delegates")
public class DelegateController {

    private final DelegateService service;
    private final CountryRepository countryRepository;
    private final CommitteeRepository committeeRepository;

    public DelegateController(DelegateService service,
                              CountryRepository countryRepository,
                              CommitteeRepository committeeRepository) {
        this.service = service;
        this.countryRepository = countryRepository;
        this.committeeRepository = committeeRepository;
    }

    private void addDropdownData(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("committees", committeeRepository.findAll());
    }

    @GetMapping
    public String listDelegates(Model model) {
        model.addAttribute("delegates", service.getAllDelegates());
        return "delegates";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("delegate", new Delegate());
        addDropdownData(model);
        return "delegate-form";
    }

    @PostMapping("/save")
    public String saveDelegate(@Valid @ModelAttribute Delegate delegate,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addDropdownData(model);
            return "delegate-form";
        }

        try {
            delegate.setRegistrationDate(LocalDate.now());
            service.registerDelegate(delegate);
            redirectAttributes.addFlashAttribute("success", "Delegate registered successfully!");
            return "redirect:/delegates";
        } catch (org.springframework.dao.DuplicateKeyException e) {
            model.addAttribute("delegate", delegate);
            model.addAttribute("error", "This email is already registered.");
            addDropdownData(model);
            return "delegate-form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editDelegate(@PathVariable Long id, Model model) {
        Delegate delegate = service.getDelegate(id);
        if (delegate == null) {
            return "redirect:/delegates";
        }
        model.addAttribute("delegate", delegate);
        addDropdownData(model);
        return "delegate-edit";
    }

    @PostMapping("/update")
    public String updateDelegate(@ModelAttribute Delegate delegate,
                                 RedirectAttributes redirectAttributes) {
        try {
            service.updateDelegate(delegate);
            redirectAttributes.addFlashAttribute("success", "Delegate updated successfully!");
        } catch (org.springframework.dao.DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("error", "This email is already in use.");
        }
        return "redirect:/delegates";
    }

    @PostMapping("/delete/{id}")
    public String deleteDelegate(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        service.deleteDelegate(id);
        redirectAttributes.addFlashAttribute("success", "Delegate deleted successfully!");
        return "redirect:/delegates";
    }
}

