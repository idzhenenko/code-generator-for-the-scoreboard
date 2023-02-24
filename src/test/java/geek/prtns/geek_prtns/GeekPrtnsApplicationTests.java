package geek.prtns.geek_prtns;

import geek.prtns.geek_prtns.entity.CodeEntity;
import geek.prtns.geek_prtns.service.CodeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeekPrtnsApplicationTests {

    private final CodeEntity code = new CodeEntity();

    @Autowired
    private CodeServiceImpl codeService;

    @Test
    public void successfulGetNextCode1() {
        code.setCode("a0a0");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a1");
    }

    @Test
    public void successfulGetNextCode2() {
        code.setCode("a0a9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0b0");
    }

    @Test
    public void successfulGetNextCode3() {
        code.setCode("z9z9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a0a0");
    }

    @Test
    public void successfulGetNextCode4() {
        code.setCode("z9z9z9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a0a0a0");
    }

    @Test
    public void successfulGetNextCode5() {
        code.setCode("a0a0y9z9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a0z0a0");
    }

    @Test
    public void successfulGetNextCode6() {
        code.setCode("a0a0a9a0a0z9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a0a9a0a1a0");
    }

    @Test
    public void successfulGetNextCode7() {
        code.setCode("a0a0a9a0a0y9");
        String nextCode = codeService.getCode(code);
        Assertions.assertEquals(nextCode, "a0a0a9a0a0z0");
    }
}
