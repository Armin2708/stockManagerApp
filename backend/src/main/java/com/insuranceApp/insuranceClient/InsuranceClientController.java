package com.insuranceApp.insuranceClient;

import com.insuranceApp.clientFile.ClientFile;
import com.insuranceApp.requests.ClientFileRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientFileUpdateRequest;
import com.insuranceApp.requests.InsuranceClientRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/insuranceApp")
public class InsuranceClientController {

    private final InsuranceClientService insuranceClientService;

    public InsuranceClientController(InsuranceClientService insuranceClientService) {
        this.insuranceClientService = insuranceClientService;
    }

    @GetMapping("/getAll")
    public List<InsuranceClientDTO> getAllClients(Integer pageNumber){
        return insuranceClientService.getAllClient(pageNumber);
    }

    @GetMapping("/get/{id}")
    public InsuranceClientDTO getClient(@PathVariable("id") Integer id){
        return insuranceClientService.getClientById(id);
    }

    @PostMapping("/generateClient")
    public void getClient(){
        insuranceClientService.generateClients();
    }

    @PostMapping("/registerClient")
    public void addClient(@RequestBody InsuranceClientRegistrationRequest registrationRequest){
        if (insuranceClientService.controller(registrationRequest)==true){
            insuranceClientService.addClient(registrationRequest);
        }
    }

    @PutMapping("/update/{id}")
    public void updateClient(
            @PathVariable("id") Integer id,
            @RequestBody InsuranceClientUpdateRequest updateRequest){
        insuranceClientService.updateClient(id,updateRequest);
    }

    @GetMapping("/search/{name}")
    public List<InsuranceClientDTO> getClientByNameSearch(Integer pageNumber,
            @PathVariable("name") String name
            ){
        return insuranceClientService.getClientByNameSearch(name,pageNumber);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Integer id){
        insuranceClientService.deleteClient(id);
    }


    @GetMapping("/{id}/file")
    public ClientFile getFileById(
            @PathVariable("id") Integer id
    ){
        return insuranceClientService.getClientFileById(id);
    }

    @PostMapping("/{id}/file")
    public void updateFileById(
            @PathVariable("id") Integer id,
            @RequestBody ClientFileRegistrationRequest registrationRequest
    ){
        System.out.println(registrationRequest);
        insuranceClientService.addClientFile(id,registrationRequest);
    }
}
