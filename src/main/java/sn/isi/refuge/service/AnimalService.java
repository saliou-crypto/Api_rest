package sn.isi.refuge.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sn.isi.refuge.model.Animal;
import sn.isi.refuge.model.Status;
import sn.isi.refuge.repository.AnimalRepository;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal creer(Animal animal) {
        animal.setId(null);
        return animalRepository.save(animal);
    }

    public List<Animal> lister() {
        return animalRepository.findAll();
    }

    public Animal modifierStatut(Long id, Status status) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Animal introuvable avec l'id " + id));
        animal.setStatus(status);
        return animalRepository.save(animal);
    }
}