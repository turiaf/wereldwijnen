package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Bestelbon;
import be.vdab.wereldwijnen.domain.Bestelbonlijn;
import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.exceptions.WijnNietGevondenException;
import be.vdab.wereldwijnen.repositories.BestelbonRepository;
import be.vdab.wereldwijnen.repositories.WijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultBestelbonService implements BestelbonService {
    private final BestelbonRepository repository;
    private final WijnRepository wijnRepository;

    public DefaultBestelbonService(BestelbonRepository repository, WijnRepository wijnRepository) {
        this.repository = repository;
        this.wijnRepository = wijnRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Bestelbon bestelbon) {
        repository.create(bestelbon);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public long bestelbonBevestigen(Map<Long, Integer> wijnen, Bestelbon bestelbon) {
        wijnen.entrySet().stream().forEach(entry -> {
            Optional<Wijn> optionalWijn = wijnRepository.findById(entry.getKey());
            if(optionalWijn.isPresent()) {
                Wijn wijn = optionalWijn.get();
                wijn.verhoogBestelling(entry.getValue());
                bestelbon.addBonLijn(new Bestelbonlijn(entry.getValue(),
                        wijn.teBetalen(entry.getValue()), wijn));
            } else {
                throw new WijnNietGevondenException();
            }
            /*wijnRepository.findById(entry.getKey()).ifPresent(wijn -> {
                wijn.verhoogBestelling(entry.getValue());
                bestelbon.addBonLijn(new Bestelbonlijn(entry.getValue(),
                        wijn.teBetalen(entry.getValue()), wijn));
            });*/
        });
        repository.create(bestelbon);
        return bestelbon.getId();
    }
}
