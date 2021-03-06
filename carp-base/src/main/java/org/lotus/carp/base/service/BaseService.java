package org.lotus.carp.base.service;

import org.lotus.carp.base.event.SecurityResourceChangedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 *  泛型服务方法基类，提供基本的CRUD操作
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:57 PM
 */
public abstract class BaseService<T extends JpaRepository, E, ID extends Serializable, C, U> {
    @Autowired
    protected ApplicationEventPublisher publisher;

    @Autowired
    protected T repository;

    public List<E> findAll() {
        return repository.findAll();
    }

    Page<E> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public E get(ID id) {
        return (E) repository.getOne(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public Page<E> search(String q, Pageable page) {
        return repository.findAll(createExampleQuery(q), page);
    }

    /**
     * 单表查询
     *
     * @param q 查询关键字
     * @return example
     */
    public abstract Example<E> createExampleQuery(String q);

    @Transactional(rollbackFor = {Exception.class})
    public E delete(ID id) {
        Object entity = repository.getOne(id);
        repository.delete(entity);
        return (E) entity;
    }
    @Transactional(rollbackFor = {Exception.class})
    public E create(C createDto) {
        E entity = newOne();
        BeanUtils.copyProperties(createDto, entity);
        E result = (E) repository.save(entity);
        publishEvent(result);
        return result;
    }
    @Transactional(rollbackFor = {Exception.class})
    public E update(U updateDto) {
        E entity = (E) repository.getOne(getUpdateDtoId(updateDto));
        BeanUtils.copyProperties(updateDto, entity);
        E result =  (E) repository.save(entity);
        publishEvent(result);
        return result;
    }

    public void publishEvent(E e) {
        publisher.publishEvent(new SecurityResourceChangedEvent());
    }

    public abstract E newOne();

    public abstract ID getUpdateDtoId(U updateDto);
}
