package sn.isi.refuge.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
        import sn.isi.refuge.model.Animal;
import sn.isi.refuge.model.Status;
import sn.isi.refuge.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/animaux")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Créer un animal
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal creer(@Valid @RequestBody Animal animal) {
        return animalService.creer(animal);
    }

    // Lister les animaux
    @GetMapping
    public List<Animal> lister() {
        return animalService.lister();
    }

    // Modifier le statut d'un animal
    @PatchMapping("/{id}/status")
    public Animal modifierStatut(@PathVariable Long id,
                                 @RequestBody StatusRequest request) {
        return animalService.modifierStatut(id, request.status());
    }

    public record StatusRequest(Status status) {}
}