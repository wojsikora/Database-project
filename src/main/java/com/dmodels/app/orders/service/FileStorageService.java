package com.dmodels.app.orders.service;


import com.dmodels.app.orders.model.File;
import com.dmodels.app.orders.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileRepository fileRepository;

    public File store(MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        File f = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(f);
    }

    public File getFile(String id)
    {
        return fileRepository.findById(id).get();
    }

    public Stream<File> getAllFiles()
    {
        return fileRepository.findAll().stream();
    }

}
