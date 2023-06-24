package com.hmy.staybooking.repository;

import com.hmy.staybooking.model.Stay;
import com.hmy.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// common methods like save, deleteById, findById are defined in the JpaRepository
// so we only need to define our method findByHost and findByIdAndHost

// Add a new method called findByIdInAndGuestNumberGreaterThanEqual()
// besides location, the guest number is another parameter for search
@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    List<Stay> findByHost(User user);

    Stay findByIdAndHost(Long id, User host);

    List<Stay> findByIdInAndGuestNumberGreaterThanEqual(List<Long> ids, int guestNumber);
}

