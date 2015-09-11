package com.easygoapp.service.impl;

import com.easygoapp.service.AbstractCrudService;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.mail.MessagingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir Kolesnikov on 03.03.2015.
 */
public abstract class AbstractCrudServiceImpl<T extends Persistable<PK>, PK extends Serializable>
        implements AbstractCrudService<T, PK> {

    private JpaRepository<T, PK> repository;

    public void setRepository(JpaRepository<T, PK> repository) {
        this.repository = repository;
    }

    @Override
    public T findOne(PK id) {
        return repository.findOne(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(PK id) throws MessagingException {
        repository.delete(id);
    }
}
