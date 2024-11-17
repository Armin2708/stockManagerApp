package com.insuranceApp.clientFile;

import java.util.Optional;

public interface ClientFileDao {

    void insertClientFile(ClientFile clientFile);

    void updateClientFile(ClientFile update);

    Optional<ClientFile> getClientFileById(Integer id);


}
