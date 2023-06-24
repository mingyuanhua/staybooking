package com.hmy.staybooking.repository;

import com.hmy.staybooking.model.StayReservedDate;
import com.hmy.staybooking.model.StayReservedDateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

// Add a method named findByIdAndDateBetween() so that we can search results only contains
// stays that are reserved between check-in date and check-out date

// JpaRepository cannot support the custom findByIdInAndDateBetween() method, so we need
// to provide the implementation by ourselves.

// we can use the same solution as LocationRepository to create an implementation class,
// or in this case, just write the SQL query on top of the method
@Repository
public interface StayReservationDateRepository extends JpaRepository<StayReservedDate, StayReservedDateKey> {
    @Query(value = "SELECT srd.id.stay_id FROM StayReservedDate srd WHERE srd.id.stay_id IN ?1 AND srd.id.date BETWEEN ?2 AND ?3 GROUP BY srd.id.stay_id")
    Set<Long> findByIdInAndDateBetween(List<Long> stayIds, LocalDate startDate, LocalDate endDate);
}

