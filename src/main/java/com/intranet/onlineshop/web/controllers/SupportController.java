package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.entities.TicketType;
import com.intranet.onlineshop.domain.models.binding.TicketBindingModel;
import com.intranet.onlineshop.domain.models.service.TicketServiceModel;
import com.intranet.onlineshop.service.SupportService;
import com.intranet.onlineshop.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/support")
public class SupportController extends BaseController{

    private final SupportService supportService;
    private final ModelMapper modelMapper;

    @Autowired
    public SupportController(SupportService supportService, ModelMapper modelMapper) {
        this.supportService = supportService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/ticket")
    @PageTitle(value = "support.page.title.ticket")
    public ModelAndView sendTicket() {
        return view("support/ticket-form");
    }

    @PostMapping("/ticket")
    public ModelAndView sendTicketConfirm(@ModelAttribute(name = "model") TicketBindingModel ticketBindingModel,
                                   Principal principal) {
        TicketServiceModel ticketServiceModel = modelMapper.map(ticketBindingModel, TicketServiceModel.class);
        TicketType ticketType = TicketType.valueOf(ticketBindingModel.getTypeName().toUpperCase());
        ticketServiceModel.setType(ticketType);
        ticketServiceModel.setSender(principal.getName());

        supportService.createTicket(ticketServiceModel);

        return view("support/ticket-success");
    }
}
