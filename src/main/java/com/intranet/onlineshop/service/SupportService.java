package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.TicketServiceModel;

/**
 * Class used for declaring the business logic methods regarding the Support roles
 */
public interface SupportService {

    /**
     * create a ticket for the support department
     * @param ticketServiceModel service model of the ticket
     */
    void createTicket(TicketServiceModel ticketServiceModel);
}
