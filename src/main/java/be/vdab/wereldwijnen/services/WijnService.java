package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface WijnService {
    Optional<Wijn> findById(long id);
    List<Wijn> findAllInList(List<Long> idList);
}
