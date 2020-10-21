package run.qontract.petstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import run.qontract.petstore.model.NewPet;
import run.qontract.petstore.model.Pet;

import java.util.function.Function;
import java.util.stream.Collectors;

@Endpoint
public class PetstoreEndpoint {
    private static final String NAMESPACE_URI = "http://qontract.run/petstore/api";

    private PetstoreRepository petstoreRepository;

    @Autowired
    public PetstoreEndpoint(PetstoreRepository petstoreRepository) {
        this.petstoreRepository = petstoreRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddPetRequest")
    @ResponsePayload
    public AddPetResponse addPet(@RequestPayload run.qontract.petstore.api.AddPetRequest newPet) {
        NewPet newPetRecord = new NewPet(newPet);
        AddPetResponse petId = new AddPetResponse();
        petId.setId(petstoreRepository.add(newPetRecord));
        return petId;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPetRequest")
    @ResponsePayload
    public run.qontract.petstore.api.GetPetResponse getPet(@RequestPayload GetPetRequest id) {
        return petstoreRepository.findPet(id.getId()).toPetResponse();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchRequest")
    @ResponsePayload
    public run.qontract.petstore.api.SearchResponse search(@RequestPayload SearchRequest petQuery) {
        SearchResponse searchResponse = new SearchResponse();

        searchResponse.pet = petstoreRepository.search(petQuery.getName(), petQuery.getType(), petQuery.getStatus()).stream().map(new Function<Pet, SearchResponse.Pet>() {
            @Override
            public SearchResponse.Pet apply(run.qontract.petstore.model.Pet pet) {
                return pet.toSearchResponsePet();
            }
        }).collect(Collectors.toList());

        return searchResponse;
    }
}
