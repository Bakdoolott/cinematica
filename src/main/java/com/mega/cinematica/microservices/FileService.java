package com.mega.cinematica.microservices;

import com.mega.cinematica.microservices.json.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "fileService", url = "http://localhost:8011")
public interface FileService {
    @PostMapping(value = "/api/v1/file/upload", consumes = "multipart/form-data")
    FileResponse upload(@RequestPart MultipartFile file);

    @GetMapping("/api/v1/file/download/{folder}/{fileName}")
    ResponseEntity<byte[]> download(@PathVariable String folder,
                                    @PathVariable String fileName);
}
