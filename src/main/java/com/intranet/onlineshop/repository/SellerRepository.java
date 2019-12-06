package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Product;
import com.intranet.onlineshop.domain.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    Seller findSellerByUser_Username(String username);
}
