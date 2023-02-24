package geek.prtns.geek_prtns.service;

import geek.prtns.geek_prtns.entity.CodeEntity;
import geek.prtns.geek_prtns.exeption.CustomException;
import geek.prtns.geek_prtns.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Override
    public CodeEntity generateNextCode() {
        CodeEntity lastCode = getLastCode();
        String code = getCode(lastCode);
        CodeEntity newCode = new CodeEntity();
        newCode.setCreateAt(LocalDateTime.now());
        newCode.setCode(code);
        codeRepository.save(newCode);
        return newCode;
    }

    @Override
    public CodeEntity getLastCode() {
        return codeRepository.findFirstByOrderByCreateAtDesc().orElseThrow(() ->
                new CustomException("The last code not found", HttpStatus.NOT_FOUND));
    }

    public String getCode(CodeEntity codeEntity) {
        String code = codeEntity.getCode();
        int positionOfCode = code.length() - 2;
        while (positionOfCode >= 0) {
            if (code.charAt(positionOfCode + 1) == '9' && code.charAt(positionOfCode) == 'z') {
                positionOfCode -= 2;
            } else {
                break;
            }
        }
        StringBuilder newCode = new StringBuilder();
        int position;
        if (positionOfCode == -2) {
            position = positionOfCode;
            while (position < code.length()) {
                newCode.append('a').append('0');
                position += 2;
            }
        } else {
            newCode = new StringBuilder(code);
            position = positionOfCode + 2;
            while (position < code.length()) {
                newCode.setCharAt(position, 'a');
                newCode.setCharAt(position + 1, '0');
                position += 2;
            }
            if (code.charAt(positionOfCode + 1) != '9') {
                positionOfCode++;
            } else {
                newCode.setCharAt(positionOfCode + 1, '0');
            }
            newCode.setCharAt(positionOfCode, (char) (newCode.charAt(positionOfCode) + 1));
        }
        return newCode.toString();
    }
}