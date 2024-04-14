package com.example.diplom33.services;

import com.deepoove.poi.XWPFTemplate;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

@Service
@AllArgsConstructor
@Transactional
public class DocumentGenerationService {
    private final UserRepository userRepository;

    public ResponseEntity<byte[]> generateDocument(long id) throws IOException {
        User user = userRepository.findById(id).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        XWPFTemplate template = XWPFTemplate.compile("generate.docx");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        template.render(new HashMap<String, Object>() {{
            put("FIO", user.getFullName().toUpperCase(Locale.ROOT));
            put("SNILS", user.getSnils());
            put("BIRTH", user.getBirth().format(formatter));
            put("CITY", user.getPlaceOfBirth());
            put("PASSPORT", user.getPassport());
            put("PASSPORTISSUED", user.getPassportIssued());
            put("DATEISSUEPASSPORT", user.getDateIssuePassport().format(formatter));
            put("KP", user.getKp());
            put("REGISTRATIONADDRESS", user.getRegistrationAddress());
            put("DATE", LocalDate.now().format(formatter));
        }});

        template.write(outputStream);
        outputStream.close();
        template.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "dogovor_s_ip" + System.currentTimeMillis() + ".docx");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

//    public void documentGeneration(long id) throws IOException {
//        User user = userRepository.findById(id).get();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//        XWPFTemplate.compile("generate.docx").render(new HashMap<String, Object>(){{
//            put("FIO", user.getFullName().toUpperCase(Locale.ROOT));
//
//            put("SNILS", user.getSnils());
//
//            put("BIRTH", user.getBirth().format(formatter));
//
//            put("CITY", user.getPlaceOfBirth());
//            put("PASSPORT", user.getPassport());
//            put("PASSPORTISSUED", user.getPassportIssued());
//            put("DATEISSUEPASSPORT", user.getDateIssuePassport().format(formatter));
//            put("KP", user.getKp());
//
//            put("REGISTRATIONADDRESS", user.getRegistrationAddress());
//            put("DATE", LocalDate.now().format(formatter));
//
//        }}).writeToFile("dogovor_s_ip"+System.currentTimeMillis()+".docx");
//    }
    }
}
