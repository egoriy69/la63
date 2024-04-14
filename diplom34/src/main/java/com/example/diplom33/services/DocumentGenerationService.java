package com.example.diplom33.services;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class DocumentGenerationService {


    public void documentGeneration() throws IOException {
        XWPFTemplate.compile("generate.docx").render(new HashMap<String, Object>(){{
            put("FIO", "Hello, poi-tl Word template engine");

            put("SNILS", "Hello World");

            put("BIRTH", "god23bin");

            put("CITY", "You haven't followed god23bin yet? If you don't follow me anymore, I'll ask you to follow me!");
        }}).writeToFile("out_template.docx");
    }
}
