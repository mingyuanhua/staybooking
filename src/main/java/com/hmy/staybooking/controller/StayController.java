package com.hmy.staybooking.controller;

import com.hmy.staybooking.model.Stay;
import com.hmy.staybooking.service.StayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StayController {
    private final StayService stayService;

    public StayController(StayService stayService) {
        this.stayService = stayService;
    }

    @GetMapping(value = "/stays")
    public List<Stay> listStays(@RequestParam(name = "host") String hostName) {
        return stayService.listByUser(hostName);
    }

    @GetMapping(value = "/stays/id")
    public Stay getStay(
            @RequestParam(name = "stay_id") Long stayId,
            @RequestParam(name = "host") String hostName) {
        return stayService.findByIdAndHost(stayId, hostName);
    }

    @PostMapping("/stays")
    public void addStay(@RequestBody Stay stay) {
        stayService.add(stay);
    }

    @DeleteMapping("/stays")
    public void deleteStay(
            @RequestParam(name = "stay_id") Long stayId,
            @RequestParam(name = "host") String hostName) {
        stayService.delete(stayId, hostName);
    }
}
