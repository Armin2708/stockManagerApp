package com.insuranceApp.clientFile;

import com.insuranceApp.insuranceClient.InsuranceClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("clientFileJpa")
public class ClientFileJPADataAccess implements ClientFileDao {

private final ClientFileRepository fileRepository;


    public ClientFileJPADataAccess(ClientFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void insertClientFile(ClientFile clientFile) {
        fileRepository.save(clientFile);
    }

    @Override
    public void updateClientFile(ClientFile update) {
        fileRepository.save(update);
    }

    @Override
    public Optional<ClientFile> getClientFileById(Integer id) {
        return fileRepository.findById(id);
    }
}
