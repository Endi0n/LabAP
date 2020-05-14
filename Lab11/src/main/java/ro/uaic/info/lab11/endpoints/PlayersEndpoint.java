package ro.uaic.info.lab11.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.lab11.models.Player;
import ro.uaic.info.lab11.repositories.PlayersRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayersEndpoint {
    @Autowired
    PlayersRepository playersRepository;

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return  playersRepository.findAll();
    }

    @PostMapping(value="/create")
    public Player createPlayer(@RequestBody Player player) {
        return playersRepository.save(player);
    }

    @GetMapping(value = "/{player}")
    public Map<String, Object> getPlayer(@PathVariable("player") int playerId) {
        var res = new LinkedHashMap<String, Object>();

        var player_dao = playersRepository.findById(playerId);
        if (player_dao.isEmpty()) {
            res.put("error", String.format("No player with id %d.", playerId));
            return res;
        }

        res.put("id", player_dao.get().getId());
        res.put("username", player_dao.get().getUsername());

        return res;
    }

    @PutMapping(value = "/{player}")
    public Map<String, Object> updatePlayer(@PathVariable("player") int playerId, @RequestBody Player player) {
        var res = new LinkedHashMap<String, Object>();

        var player_dao = playersRepository.findById(playerId);
        if (player_dao.isEmpty()) {
            res.put("error", String.format("No player with id %d.", playerId));
            return res;
        }

        player_dao.get().setUsername(player.getUsername());
        playersRepository.save(player_dao.get());

        res.put("message", "Player updated successfully.");
        return res;
    }

    @DeleteMapping(value = "/{player}")
    public Map<String, Object> deletePlayer(@PathVariable("player") int playerId) {
        var res = new LinkedHashMap<String, Object>();

        if (playersRepository.findById(playerId).isEmpty()) {
            res.put("error", String.format("No player with id %d.", playerId));
            return res;
        }

        playersRepository.deleteById(playerId);

        res.put("message", "Player deleted successfully.");

        return res;
    }
}
