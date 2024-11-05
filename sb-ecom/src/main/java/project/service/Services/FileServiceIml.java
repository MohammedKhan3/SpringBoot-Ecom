package project.service.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service

public class FileServiceIml implements FileService {
   @Override
   public String uploadImage(String path, MultipartFile file) throws IOException {
        //File names of Current /orignal file
        String origanlFileName = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(origanlFileName.substring(origanlFileName.lastIndexOf('.')));
        String filePath = path + File.separator+fileName;
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
        //upload to server
        //name the file uniquely
        //Generate a unique file name
        //check if path exist and create
    }
}
