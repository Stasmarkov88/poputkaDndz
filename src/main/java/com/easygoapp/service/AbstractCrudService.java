package com.easygoapp.service;

import org.springframework.data.domain.Persistable;

import javax.mail.MessagingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kir Kolesnikov on 03.03.2015.
 */
public interface AbstractCrudService<T extends Persistable<PK>, PK extends Serializable> {

    public T findOne(PK id);

    public T save(T entity);

    public List<T> findAll();

    public void delete(PK id) throws MessagingException;
}
