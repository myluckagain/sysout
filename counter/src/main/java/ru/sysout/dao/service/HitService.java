package ru.sysout.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.sysout.dao.HitRepository;
import ru.sysout.model.Hits;

@Service
public class HitService {
    @Autowired
    private HitRepository hitRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Hits updateAndReturnCount() {

        hitRepository.updateCount(1l);
        Hits hits = hitRepository.getCount(1l);

        return hits;
    }
}
