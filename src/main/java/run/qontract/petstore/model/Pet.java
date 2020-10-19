package run.qontract.petstore.model;

import run.qontract.petstore.api.SearchResponse;

public class Pet {
    private Integer id = 0;
    private String name = "";
    private String type = "";
    private String status = "";

    public Pet(Integer id, String name, String type, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public Pet(NewPet newPet, int id) {
        this.id = id;
        this.name = newPet.getName();
        this.type = newPet.getType();
        this.status = newPet.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public run.qontract.petstore.api.GetPetResponse toPetResponse() {
        run.qontract.petstore.api.GetPetResponse genPet = new run.qontract.petstore.api.GetPetResponse();

        genPet.setId(this.id);
        genPet.setName(this.name);
        genPet.setStatus(this.status);
        genPet.setType(this.type);

        return genPet;
    }

    public SearchResponse.Pet toSearchResponsePet() {
        SearchResponse.Pet genPet = new SearchResponse.Pet();

        genPet.setId(this.id);
        genPet.setName(this.name);
        genPet.setStatus(this.status);
        genPet.setType(this.type);

        return genPet;
    }
}
