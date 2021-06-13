package com.dmodels.app.api;


import com.dmodels.app.orders.model.File;
import com.dmodels.app.orders.service.FileStorageService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileStorageService fileStorageService;

    @GetMapping("/files")
    public ResponseEntity<List<FileResponse>> getListFiles()
    {
        List<FileResponse> files = fileStorageService.getAllFiles().map(File ->{
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(File.getId())
                    .toUriString();

            return new FileResponse(
                    File.getName(),
                    fileDownloadUri,
                    File.getType(),
                    File.getData().length);

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id)
    {
        File file = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file){
        String message ="";
        try
        {
            fileStorageService.store(file);
            message = "Uploaded the file successfully" + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e){
            message = "Could not upload the file:" + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }

    }


    @Data
    @Builder
    static class FileResponse{
        private String name;
        private String url;
        private String type;
        private long size;

        FileResponse(String name, String url, String type, long size)
        {
            this.name = name;
            this.url = url;
            this.type = type;
            this.size = size;
        }
    }

    @Data
    @Builder
    static class MessageResponse{
        private String message;

        MessageResponse(String message)
        {
            this.message = message;
        }


    }
}