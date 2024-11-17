package com.insuranceApp.insuranceClient;

import com.insuranceApp.clientFile.ClientFile;
import com.insuranceApp.clientFile.ClientFileDao;
import com.insuranceApp.exception.RequestValidationException;
import com.insuranceApp.exception.DuplicateResourceException;
import com.insuranceApp.exception.ResourceNotFoundException;
import com.insuranceApp.requests.ClientFileRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceClientService {

    private final InsuranceClientDao insuranceClientDao;
    private final ClientFileDao clientFileDao;

    public void generateClients() {
        for (int i = 0; i < 100; i++) {
            InsuranceClient randomClient = InsuranceClient.generateRandomClient();
            insuranceClientDao.insertClient(randomClient);
        }
    }



    public InsuranceClientService(@Qualifier("insuranceClientJpa") InsuranceClientDao insuranceClientDao,@Qualifier("clientFileJpa") ClientFileDao clientFileDao) {
        this.insuranceClientDao = insuranceClientDao;
        this.clientFileDao = clientFileDao;
    }

    public List<InsuranceClientDTO> getAllClient(Integer pageNumber){
        return insuranceClientDao.getAllClient(pageNumber)
                .stream()
                .map(InsuranceClientDTO::new)
                .collect(Collectors.toList());
    }

    public InsuranceClientDTO getClientById(Integer id){
        InsuranceClient client = insuranceClientDao.getClientById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client with id [%d] not found".formatted(id))
        );
        return new InsuranceClientDTO(client);
    }

    public List<InsuranceClientDTO> getClientByNameSearch(String name, Integer pageNumber){
        String [] parts = name.split("&",-1);
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";
        List<InsuranceClient> clients = null;

        if (firstName.isEmpty() && !lastName.isEmpty()){
            clients = insuranceClientDao.getClientByLastName(lastName,pageNumber);
        } else if (lastName.isEmpty() && !firstName.isEmpty()){
            clients = insuranceClientDao.getClientByFirstName(firstName, pageNumber);
        } else if (!firstName.isEmpty() && !lastName.isEmpty()){
            clients = insuranceClientDao.getClientByFirstAndLastName(firstName,lastName, pageNumber);
        } else if (firstName.isEmpty() && lastName.isEmpty()){
            clients = insuranceClientDao.getAllClient(pageNumber);
        }

        if (clients.isEmpty()){
            throw new ResourceNotFoundException("No Client was found");
        }

        return clients.stream().map(InsuranceClientDTO::new).collect(Collectors.toList());
    }

    public void addClient(InsuranceClientRegistrationRequest clientRegistrationRequest){
        String email = clientRegistrationRequest.email();
        if(insuranceClientDao.existClientByEmail(email)){
            throw new DuplicateResourceException("Client with email [%s] already exists".formatted(email));
        }

            InsuranceClient client = new InsuranceClient(
                    clientRegistrationRequest.email(), clientRegistrationRequest.firstName(),clientRegistrationRequest.lastName(),clientRegistrationRequest.gender(),clientRegistrationRequest.birthDate(), clientRegistrationRequest.phoneNumber(),
                    clientRegistrationRequest.country(), clientRegistrationRequest.state(), clientRegistrationRequest.city(), clientRegistrationRequest.postalCode(), clientRegistrationRequest.street(),
                    clientRegistrationRequest.ageRisk(),clientRegistrationRequest.healthRisk(),clientRegistrationRequest.jobRisk(),clientRegistrationRequest.livingAreaRisk()

                    );
            insuranceClientDao.insertClient(client);
    }

    public void updateClient(Integer id, InsuranceClientUpdateRequest update){
        InsuranceClient client = insuranceClientDao.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "client with email [%d] doesn't exist".formatted(id)
                ));
        boolean changes=false;

        //Update Client Contact infos

        if (update.firstName()!=null && !update.firstName().equals(client.getFirstName())){
            client.setFirstName(update.firstName());
            changes=true;
        }
        if (update.lastName()!=null && !update.lastName().equals(client.getLastName())){
            client.setLastName(update.lastName());
            changes=true;
        }
        if (update.gender()!=null && !update.gender().equals(client.getGender())){
            client.setGender(update.gender());
            changes=true;
        }
        if (update.birthDate()!=null && !update.birthDate().equals(client.getBirthDate())){
            client.setBirthDate(update.birthDate());
            changes=true;
        }
        if (update.phoneNumber()!=null && !update.phoneNumber().equals(client.getPhoneNumber())){
            client.setPhoneNumber(update.phoneNumber());
            changes=true;
        }

        //Update Client Address

        if (update.country()!=null && !update.country().equals(client.getCountry())){
            client.setCountry(update.country());
            changes=true;
        }
        if (update.state()!=null && !update.state().equals(client.getState())){
            client.setState(update.state());
            changes=true;
        }
        if (update.city()!=null && !update.city().equals(client.getCity())){
            client.setCity(update.city());
            changes=true;
        }
        if (update.postalCode()!=null && !update.postalCode().equals(client.getPostalCode())){
            client.setPostalCode(update.postalCode());
            changes=true;
        }
        if (update.street()!=null && !update.street().equals(client.getStreet())){
            client.setStreet(update.street());
            changes=true;
        }

        if (update.ageRisk()!=null && !update.ageRisk().equals(client.getAgeRisk())){
            client.setAgeRisk(update.ageRisk());
            changes=true;
        }
        if (update.healthRisk()!=null && !update.healthRisk().equals(client.getHealthRisk())){
            client.setHealthRisk(update.healthRisk());
            changes=true;
        }
        if (update.jobRisk()!=null && !update.jobRisk().equals(client.getJobRisk())){
            client.setJobRisk(update.jobRisk());
            changes=true;
        }
        if (update.livingAreaRisk()!=null && !update.livingAreaRisk().equals(client.getLivingAreaRisk())){
            client.setLivingAreaRisk(update.livingAreaRisk());
            changes=true;
        }

        if (!changes){
            throw new RequestValidationException("no data changes found");
        }

        insuranceClientDao.updateClient(client);
    }

    public void deleteClient(Integer id){
        if (!insuranceClientDao.existClientById(id)){
            throw new ResourceNotFoundException("client with email [%d] not found".formatted(id));
        }
        insuranceClientDao.deleteClientById(id);
    }

    public boolean controller(InsuranceClientRegistrationRequest request){
        boolean valid=true;

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String birthdateRegex = "^(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$";
        String phoneRegex = "^\\+(\\d+)$";
        String nameFormat = "^[A-Za-z-]+$";


        if (!request.email().matches(emailRegex)) {
            throw new RuntimeException("Invalid email format");
        }
        if (!request.birthDate().matches(birthdateRegex)){
            throw new RuntimeException("Invalid birthDate format");
        }
        if (!request.phoneNumber().matches(phoneRegex)){
            throw new RuntimeException("Invalid phoneNumber format");
        }
        if (!request.firstName().matches(nameFormat)){
            throw new RuntimeException("Invalid firstName format");
        }
        if (!request.lastName().matches(nameFormat)){
            throw new RuntimeException("Invalid lastName format");
        }
        return valid;
    }

    public ClientFile getClientFileById(Integer id){
        ClientFile file = clientFileDao.getClientFileById(id)
                .orElseThrow(() -> new ResourceNotFoundException("file not found"));

        return file;
    }
    public void addClientFile(Integer clientId, ClientFileRegistrationRequest clientFile) {
        // Fetch client
        InsuranceClient client = insuranceClientDao.getClientById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));


        String fileContent = clientFile.fileContent();
        String[] parts = fileContent.split(",");
        byte[] decodedBytes;
        if(parts.length > 1) {
            String data = parts[1]; // actual base64 encoded file content
            decodedBytes = Base64.getDecoder().decode(data);
        } else {
            decodedBytes = Base64.getDecoder().decode(fileContent);
        }
        // Set client to client file
        ClientFile file= new ClientFile(
                client,
                clientFile.fileName(),
                clientFile.fileSize(),
                clientFile.fileFormat(),
                decodedBytes
        );

        // Save client file
        clientFileDao.insertClientFile(file);
    }
}
