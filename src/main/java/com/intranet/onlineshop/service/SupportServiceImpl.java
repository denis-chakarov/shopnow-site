package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Ticket;
import com.intranet.onlineshop.domain.models.service.TicketServiceModel;
import com.intranet.onlineshop.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportServiceImpl implements SupportService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupportServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTicket(TicketServiceModel ticketServiceModel) {
        Ticket ticket = modelMapper.map(ticketServiceModel, Ticket.class);
        ticketRepository.save(ticket);
    }
}
