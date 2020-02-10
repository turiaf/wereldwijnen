package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Bestelbon;

import java.util.Map;

public interface BestelbonService {
    void create(Bestelbon bestelbon);
    long bestelbonBevestigen(Map<Long, Integer> wijnen, Bestelbon bestelbon);
}
