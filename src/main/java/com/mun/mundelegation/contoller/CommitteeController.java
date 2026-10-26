package com.mun.mundelegation.contoller;

import com.mun.mundelegation.Committee;
import com.mun.mundelegation.service.CommitteeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/committees")
public class CommitteeController {

    private final CommitteeService committeeService;

    public CommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @GetMapping
    public String listCommittees(Model model) {
        model.addAttribute("committees", committeeService.getAllCommittees());
        return "committees/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("committee", new Committee());
        return "committees/form";
    }

    @PostMapping("/save")
    public String saveCommittee(@ModelAttribute Committee committee) {
        committeeService.addCommittee(committee);
        return "redirect:/committees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Committee committee = committeeService.getCommitteeById(id);

        if (committee == null) {
            return "redirect:/committees";
        }

        model.addAttribute("committee", committee);
        return "committees/form";
    }

    @PostMapping("/update")
    public String updateCommittee(@ModelAttribute Committee committee) {
        committeeService.updateCommittee(committee);
        return "redirect:/committees";
    }

    @PostMapping("/delete/{id}")
    public String deleteCommittee(@PathVariable Long id) {
        committeeService.deleteCommittee(id);
        return "redirect:/committees";
    }
}

