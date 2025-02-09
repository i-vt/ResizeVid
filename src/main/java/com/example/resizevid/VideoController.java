package com.example.resizevid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import javax.annotation.PostConstruct;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID; // Import UUID

@Controller
public class VideoController {

    private final Path rootLocation = Paths.get("uploaded-videos");

    @GetMapping("/")
    public String index() {
        return "index"; // returns the index.html Thymeleaf template
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(filename);
        String safeFilename = UUID.randomUUID().toString(); // Generate UUID as the new filename
        if (fileExtension != null && !fileExtension.isEmpty()) {
            safeFilename += "." + fileExtension; // Append the original file extension
        }
        
        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
                return "redirect:/";
            }
            if (filename.contains("..")) {
                redirectAttributes.addFlashAttribute("message", "Invalid path sequence " + filename);
                return "redirect:/";
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(safeFilename), StandardCopyOption.REPLACE_EXISTING);
            String outputFilename = resizeVideo(safeFilename);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded and resized '" + safeFilename + "'");
            redirectAttributes.addFlashAttribute("downloadLink", "/videos/" + outputFilename);
            redirectAttributes.addFlashAttribute("filename", outputFilename); // Ensure this line is added
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload file: " + safeFilename);
            return "redirect:/";
        }
    }

    private String resizeVideo(String filename) throws IOException, InterruptedException {
        String inputPath = this.rootLocation.resolve(filename).toString();
        String outputPath = this.rootLocation.resolve("resized-" + filename).toString();
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", inputPath, "-vf", "scale=-2:360", outputPath);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to resize the video");
        }
        return "resized-" + filename;
    }

    @GetMapping("/videos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveVideo(@PathVariable String filename) {
        try {
            Path file = this.rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) { // Corrected 'or' to '||'
                String contentType = "application/octet-stream"; // Setting content type for downloading
                return ResponseEntity.ok()
                                     .contentType(MediaType.parseMediaType(contentType))
                                     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                                     .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
}
