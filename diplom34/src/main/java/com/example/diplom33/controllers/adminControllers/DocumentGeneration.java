package com.example.diplom33.controllers.adminControllers;

import com.deepoove.poi.XWPFTemplate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@AllArgsConstructor
@RequestMapping("/documentGeneration")
public class DocumentGeneration {

    @GetMapping()
    public void document() throws IOException {

    }
}

//        String filepath = this.getClass().getClassLoader().getResource("/documentGeneration.docx").getPath();
//        XWPFTemplate template = XWPFTemplate.compile(filepath).render(
//                new HashMap<String, Object>() {{
//                    put("FIO", "Hello, poi-tl Word template engine");
//
//                    put("SNILS", "Hello World");
//
//                    put("BIRTH", "god23bin");
//
//                    put("CITY", "You haven't followed god23bin yet? If you don't follow me anymore, I'll ask you to follow me!");
//                }});
//        try {
//            template.writeAndClose(new FileOutputStream("output.docx"));
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }