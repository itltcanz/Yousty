package com.daila.yousty.service;

import com.daila.yousty.entity.Image;
import com.daila.yousty.entity.Style;
import com.daila.yousty.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unused")
public class ImageService {
    private final ImageRepository imageRepository;

    private final ModelMapper converter;

    public ImageService(ImageRepository imageRepository, ModelMapper converter) {
        this.imageRepository = imageRepository;
        this.converter = converter;
    }
    public List<Image> getImagesByOutfitId(Long id, Pageable imagePageRequest) {
        Page<Image> images = imageRepository.getImagesByOutfitsId(id, imagePageRequest);
        return images.toList();
    }

    public List<Image> getImagesByOutfitId(Long id) {
        return imageRepository.getImagesByOutfitsId(id);
    }

    public Image getUserDefaultImage() {
        return imageRepository.getImageById((long) 22);
    }

    private <T> List<T> convertImagesToDTOs(List<Image> images, Class<T> dtoClass) {
        return images.stream()
                .map(style -> converter.map(style, dtoClass))
                .collect(Collectors.toList());
    }

    public List<Image> saveImages(List<MultipartFile> files, Style style) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Image image = new Image();
            String path;
            try {
                path = saveFile(file, style);
            } catch (Exception e) {
                System.err.println("Failed to store file");
                return null;
            }
            image.setPath(path);
            saveImage(image);
            images.add(image);
        }
        return images;
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private String saveFile(MultipartFile multipartFile, Style style) throws IOException, RuntimeException {
        // Создайте директорию, если она не существует
        File file = new File(System.getProperty("user.dir") + "/images/outfits/" + style.getName());
        if (!file.exists()) {
            file.mkdirs();
        }
        // Создайте файл
        String fileName = multipartFile.getOriginalFilename();
        if (fileName != null) {
            String newFilename = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            File dest = new File(file, newFilename);
            multipartFile.transferTo(dest);
            // Верните путь к файлу
            return "/images/outfits/" + style.getName() + "/" + newFilename;
        }
        return null;
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

}