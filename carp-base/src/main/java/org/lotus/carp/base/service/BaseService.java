package org.lotus.carp.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:57 PM
 */
public abstract class BaseService<T extends JpaRepository, E, ID extends Serializable> {
    @Autowired
    private T repository;

    public List<E> findAll() {
        return repository.findAll();
    }

    Page<E> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public E get(ID id) {
        return (E) repository.getOne(id);
    }

    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public Page<E> search(String q, Pageable page) {
        return repository.findAll(createExampleQuery(q), page);
    }

    /**
     * 单表查询
     * @param q 查询关键字
     * @return example
     */
    public abstract Example<E> createExampleQuery(String q);
}