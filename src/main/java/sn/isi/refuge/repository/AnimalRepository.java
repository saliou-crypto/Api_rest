package sn.isi.refuge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.refuge.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}