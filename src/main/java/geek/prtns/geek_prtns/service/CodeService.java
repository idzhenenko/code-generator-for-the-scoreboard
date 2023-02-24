package geek.prtns.geek_prtns.service;

import geek.prtns.geek_prtns.entity.CodeEntity;

public interface CodeService {

    CodeEntity generateNextCode();

    CodeEntity getLastCode();
}
