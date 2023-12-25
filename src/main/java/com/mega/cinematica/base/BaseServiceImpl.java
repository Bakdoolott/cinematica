package com.mega.cinematica.base;

import com.mega.cinematica.mappers.CycleAvoidingMappingContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;
@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity, ED extends BaseEntityDto, R extends BaseRepository<E>,
        M extends BaseMapper<E, ED>> implements BaseService<ED> {
    protected R repository;
    protected M mapper;
    @Autowired
    protected CycleAvoidingMappingContext context;

    public BaseServiceImpl(R r, M m) {
        this.repository = r;
        this.mapper = m;
    }

    @Override
    public ED save(ED e) {
        E e1 = mapper.toEntity(e, context);
        return mapper.toDto(repository.save(e1), context);
    }

    @Override
    public void delete(ED e) {
        repository.delete(mapper.toEntity(e, context));
    }

    @Override
    public ED update(ED e) {
        return mapper.toDto(repository.saveAndFlush(mapper.toEntity(e, context)), context);
    }

    @Override
    public ED findById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null), context);
    }

    @Override
    public List<ED> findAll() {
        return mapper.toDtos(repository.findAll(), context);
    }
}
