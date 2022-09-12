package org.micks.champmaker.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;


    public void registerTeamToChampionship(Long champId, RegisterDTO registerDTO) {
        RegisterEntity registerEntity = new RegisterEntity(champId, registerDTO.getTeamId(), registerDTO.getRegistrationDate());
        registerRepository.save(registerEntity);
    }
}
