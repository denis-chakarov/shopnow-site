package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Ticket;
import com.intranet.onlineshop.domain.models.service.TicketServiceModel;

public interface SupportService {

    void createTicket(TicketServiceModel ticketServiceModel);
}
