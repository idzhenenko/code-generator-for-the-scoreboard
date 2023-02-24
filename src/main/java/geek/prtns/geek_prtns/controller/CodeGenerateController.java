package geek.prtns.geek_prtns.controller;

import geek.prtns.geek_prtns.entity.CodeEntity;
import geek.prtns.geek_prtns.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/code")
public class CodeGenerateController {
    private final CodeService codeService;

    @PostMapping("/generation")
    public ResponseEntity<CodeEntity> createScoreboardCode() {
        return new ResponseEntity<>(codeService.generateNextCode(), HttpStatus.OK);
    }
}
