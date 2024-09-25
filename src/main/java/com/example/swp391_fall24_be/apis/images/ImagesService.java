package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.images.dtos.CreateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.PaginateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.UpdateImageDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

@Service
public class ImagesService extends AbstractService<
        ImageEntity,
        String,
        CreateImageDto,
        UpdateImageDto,
        PaginateImageDto
        > {
    @Override
    protected void beforeCreate(ImageEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ImageEntity oldEntity, ImageEntity newEntity) throws ProjectException {

    }

    @Override
    public ImageEntity delete(String id) throws ProjectException {
        return null;
    }

    @Value("${images.path}")
    private String imagesPath;

    public ImageDto upload(MultipartFile multipartFile) throws ProjectException {
        ImageEntity image = create(new CreateImageDto());

        String fileName = image.getId();
        String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String filePath = imagesPath + fileName + fileExtension;

        try {
            multipartFile.transferTo(new File(filePath));
            image.setName(multipartFile.getOriginalFilename());
            image.setLocalPath(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return image.toResponseDto();
        }
        return null;
    }

    public byte[] getImage(String id) throws ProjectException {
        var imageEntity = findById(id);
        File file = new File(imageEntity.getLocalPath());
        try {
            return convertFileToByteArray(file);
        } catch (IOException e) {
            throw new ProjectException(
                    new ErrorReport(
                            "ImagesService_getImage",
                            ErrorEnum.ValidationError,
                            "Cannot get image"
                    )
            );
        }
    }

    private byte[] convertFileToByteArray(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        return data.getData();
    }
}
