package com.esnuguel.inicio.common.util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUtils {

    public String saveProductImage(MultipartFile file){
        String uniqueFileName;
        try(InputStream inputStream=file.getInputStream()) {
            //NOMBRE DEL ARCHIVO
            String filname=StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            uniqueFileName= UUID.randomUUID().toString().concat("-").concat(filname);
            //CREACION DE RUTA
            //Path path=Path.of("src/main/resources/products/"); //Sirve pero en memoria va a valer queso xd
            Path path=Path.of("uploads/products/");
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(uniqueFileName),StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException("Error al subir la imagen "+ e);
        }

        return uniqueFileName;
    }

}
