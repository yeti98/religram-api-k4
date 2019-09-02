package relipa.religram.service;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relipa.religram.custom_repository.PhotoRepository;
import relipa.religram.entity.Photo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhotoServiceImpl {
    @Autowired
    private PhotoRepository photoRepository;

    public static String encodeToBase64(String url) {
        try (InputStream inputStream = new URL(url).openStream()) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return new String(Base64.encodeBase64(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Photo decodeToImage(Integer hashcode, String imageValue) {
        try {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte = Base64.decodeBase64(imageValue);
            String photoUri = "photos/" + System.currentTimeMillis() + hashcode + ".png";
            String rootPath = System.getProperty("user.dir") + "/";
            new FileOutputStream(rootPath + photoUri).write(imageByte);
            return new Photo(photoUri, LocalDateTime.now(), LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveNewPhotos(List<Photo> photos) {
        photos.forEach(photo -> photoRepository.save(photo));
    }
}
