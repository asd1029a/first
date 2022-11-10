package com.hp.first.service;

import com.hp.first.entity.ItemImg;
import com.hp.first.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String originalFilename = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(originalFilename)) {
            imgName = fileService.uploadFile(itemImgLocation, originalFilename, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(originalFilename, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        if(!itemImgFile.isEmpty()) {
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
            }

            String originalFilename = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, originalFilename, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(originalFilename, imgName, imgUrl);


        }
    }
}
