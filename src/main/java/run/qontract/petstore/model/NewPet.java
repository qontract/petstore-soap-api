package run.qontract.petstore.model;

public class NewPet {
    private String name;
    private String type;
    private String status;

    public NewPet(run.qontract.petstore.api.AddPetRequest newPet) {
        this.name = newPet.getName();
        this.type = newPet.getType();
        this.status = newPet.getStatus();
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
}
