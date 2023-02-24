package geek.prtns.geek_prtns.service;

import geek.prtns.geek_prtns.entity.CodeEntity;
import geek.prtns.geek_prtns.exeption.CustomException;
import geek.prtns.geek_prtns.model.dto.CodeDTO;
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
    public CodeDTO generateNextCode() {
        CodeDTO lastCode = getLastCode();
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode(lastCode.getCode());
        String code = getCode(codeEntity);
        codeEntity.setCreateAt(LocalDateTime.now());
        codeEntity.setCode(code);
        codeRepository.save(codeEntity);
        return new CodeDTO(codeEntity.getId(), codeEntity.getCode(), codeEntity.getCreateAt());
    }

    @Override
    public CodeDTO getLastCode() {
        CodeEntity code = codeRepository.findFirstByOrderByCreateAtDesc().orElseThrow(() ->
                new CustomException("The last code not found", HttpStatus.NOT_FOUND));
        return new CodeDTO(code.getId(), code.getCode(), code.getCreateAt());
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