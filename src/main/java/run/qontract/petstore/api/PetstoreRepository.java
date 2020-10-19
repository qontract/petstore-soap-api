package run.qontract.petstore.api;

import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;
import run.qontract.petstore.model.NewPet;
import run.qontract.petstore.model.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PetstoreRepository {
    private static int id = 0;
    private static final HashMap<Integer, Pet> pets = new HashMap<>();

    static synchronized int getNewId() {
        id = id + 1;
        return id;
    }

    static synchronized void addPet(Pet pet) {
        pets.put(pet.getId(), pet);
    }

    static {
        addPet(new Pet(getNewId(), "Archie", "dog", "available"));
        addPet(new Pet(getNewId(), "Judy", "dog", "available"));
    }

    public int add(NewPet pet) {
        id += getNewId();
        addPet(new Pet(pet, id));
        return id;
    }

    public Pet findPet (Integer id){
        return pets.get(id);
    }

    public List<Pet> search (String name, String type, String status){
        ArrayList<Pet> petResult = new ArrayList<>();

        for (Map.Entry<Integer, Pet> petEntry : pets.entrySet()) {
            Pet pet = petEntry.getValue();

            if ((pet.getName() != null &&
                    pet.getName().length() > 0 &&
                    pet.getName().equals(name)) ||
                    (pet.getType() != null &&
                            pet.getType().length() == 0 &&
                            pet.getType().equals(type)) ||
                    (pet.getStatus() != null &&
                            pet.getStatus().length() > 0 &&
                            pet.getStatus().equals(status))) {
                petResult.add(pet);
            }
        }

        return petResult;
    }
}
