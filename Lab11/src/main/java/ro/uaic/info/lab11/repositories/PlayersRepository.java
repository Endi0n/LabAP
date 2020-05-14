package ro.uaic.info.lab11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.lab11.models.Player;

public interface PlayersRepository extends JpaRepository<Player, Integer> {
}
