package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.images.dtos.CreateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.PaginateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.UpdateImageDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

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
        var image = findById(id);
        String filePath = image.getLocalPath();
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }

        repository.delete(image);
        return image;
    }

    @Value("${local.path}")
    private String imagesPath;


    @Autowired
    private AccountsRepository accountsRepository;

    public ImageDto upload(MultipartFile multipartFile) throws ProjectException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new ProjectException(
                    new ErrorReport(
                            "ImagesService_upload",
                            ErrorEnum.ValidationError,
                            "File is null"
                    )
            );
        }

        File folder = new File(imagesPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        ImageEntity image = create(new CreateImageDto());
        String fileName = image.getId();
        String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        try {
            File file = new File(folder, fileName + fileExtension);
            Logger.getAnonymousLogger().info(file.getAbsolutePath()); //keep it for debugging
            if (!file.exists()) {
                file.createNewFile();
            }
            multipartFile.transferTo(file.getAbsoluteFile());
            image.setLocalPath(file.getPath());
            repository.save(image);
        } catch (Exception e) {
            delete(image.getId());
            throw new ProjectException(
                    new ErrorReport(
                            "ImagesService_upload",
                            ErrorEnum.ValidationError,
                            e.getMessage()
                    )
            );
        }
        return image.toResponseDto();
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
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(fileContent);
        fileInputStream.close();
        return fileContent;
    }


    public AccountEntity setAccountImg(String id, MultipartFile multipartFile) throws ProjectException {
        AccountEntity result = null;

        try {
            Optional<AccountEntity> account = accountsRepository.findById(id);

            if (account.isPresent()) {
                ImageDto imageEntity = upload(multipartFile);

                if (imageEntity != null) {
                    ImageEntity newAvt = new ImageEntity();

                    newAvt.setId(imageEntity.getId());
                    newAvt.setAccount(account.get());
                    newAvt.setLocalPath(imageEntity.getLocalPath());
                    newAvt.setName(imageEntity.getName());
                    newAvt.setUpdateAt(LocalDateTime.now());


                    account.get().setAvatar(newAvt);
                    accountsRepository.save(account.get());
                    result = account.get();
                }

            }
        } catch (Exception e)
        {
            System.out.println("SET AVT ERR: " + e.toString());
        }
        return result;
    }
}
