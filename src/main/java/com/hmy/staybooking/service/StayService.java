package com.hmy.staybooking.service;

import com.hmy.staybooking.exception.StayNotExistException;
import com.hmy.staybooking.model.Stay;
import com.hmy.staybooking.model.StayImage;
import com.hmy.staybooking.model.User;
import com.hmy.staybooking.repository.StayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StayService {
    private final StayRepository stayRepository;

    private final ImageStorageService imageStorageService;

    public StayService(StayRepository stayRepository, ImageStorageService imageStorageService) {
        this.stayRepository = stayRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<Stay> listByUser(String username) {
        return stayRepository.findByHost(new User.Builder().setUsername(username).build());
    }

    public Stay findByIdAndHost(Long stayId, String username) throws StayNotExistException {
        User user = new User.Builder().setUsername(username).build();
        Stay stay = stayRepository.findByIdAndHost(stayId, user);
        if (stay == null) {
            throw new StayNotExistException("Stay doesn't exist");
        }

        return stay;
    }

    @Transactional
    public void add(Stay stay, MultipartFile[] images) {
        List<String> mediaLinks = Arrays.stream(images).parallel().map(image -> imageStorageService.save(image)).collect(Collectors.toList());
        List<StayImage> stayImages = new ArrayList<>();
        for (String mediaLink : mediaLinks) {
            stayImages.add(new StayImage(mediaLink, stay));
        }
        stay.setImages(stayImages);

        stayRepository.save(stay);
    }

    @Transactional
    public void delete(Long stayId, String username) {
        User user = new User.Builder().setUsername(username).build();
        Stay stay = stayRepository.findByIdAndHost(stayId, user);
        if (stay == null) {
            throw new StayNotExistException("Stay doesn't exist");
        }

        stayRepository.deleteById(stayId);
    }
}