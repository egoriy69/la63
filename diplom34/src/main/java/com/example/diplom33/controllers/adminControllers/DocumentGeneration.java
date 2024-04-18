package com.example.diplom33.controllers.adminControllers;

import com.deepoove.poi.XWPFTemplate;
import com.example.diplom33.services.DocumentGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@AllArgsConstructor
@RequestMapping("/documentGeneration")
public class DocumentGeneration {

    private final DocumentGenerationService documentGenerationService;

//    @GetMapping("/{id}")
//    public void document(@PathVariable long id) throws IOException {
//        documentGenerationService.documentGeneration(id);
//
//    }
    @GetMapping("/{id}")
   public ResponseEntity<byte[]> document(@PathVariable long id) throws IOException {
        return documentGenerationService.generateDocument(id);

    }
}

