package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository class used for executing queries on the tickets table represented by the Ticket class
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
