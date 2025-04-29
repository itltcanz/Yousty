package com.daila.yousty.service;

import com.daila.yousty.api.ClothingApiService;
import com.daila.yousty.api.UrlApiService;
import com.daila.yousty.entity.Clothing;
import com.daila.yousty.entity.Image;
import com.daila.yousty.modules.PythonModule;
import com.daila.yousty.repository.ClothingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class ClothingService {
    private final ClothingRepository clothingRepository;
    private final PythonModule pythonModule;
    private final UrlApiService urlApiService;
    private final ClothingApiService clothingApiService;

    public ClothingService(ClothingRepository clothingRepository, PythonModule pythonModule, UrlApiService urlApiService, ClothingApiService clothingApiService) {
        this.clothingRepository = clothingRepository;
        this.pythonModule = pythonModule;
        this.urlApiService = urlApiService;
        this.clothingApiService = clothingApiService;
    }

    public List<Clothing> getClothesByOutfitId(Long id) {
        return clothingRepository.getClothesByOutfitsId(id);
    }

    public void saveClothing(Clothing clothing) {
        clothingRepository.save(clothing);
    }

    public List<Clothing> findClothing(Image image) {
        List<String> clothingImages = pythonModule.getClothingImages(image.getPath());
        List<String> clothingImagesUrls = urlApiService.getUrls(clothingImages);
        List<Clothing> clothingList =  clothingApiService.getClothing(clothingImagesUrls);
        for (Clothing clothing : clothingList) {
            saveClothing(clothing);
        }
        return clothingList;
    }
}
