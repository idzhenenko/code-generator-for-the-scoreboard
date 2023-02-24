package geek.prtns.geek_prtns.service;

import geek.prtns.geek_prtns.model.dto.CodeDTO;

public interface CodeService {

    CodeDTO generateNextCode();

    CodeDTO getLastCode();
}
