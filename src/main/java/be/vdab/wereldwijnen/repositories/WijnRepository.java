package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WijnRepository {
    Optional<Wijn> findById(long id);
    List<Wijn> findAllInList(List<Long> idList);
}
