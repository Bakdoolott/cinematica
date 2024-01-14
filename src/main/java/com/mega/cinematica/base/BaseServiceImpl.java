package com.mega.cinematica.base;

import com.mega.cinematica.exceptions.NotFoundException;
import com.mega.cinematica.mappers.CycleAvoidingMappingContext;
import com.mega.cinematica.utils.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;
@MappedSuperclass
public abstract class BaseServiceImpl<E extends BaseEntity, DTO extends BaseEntityDto, R extends BaseRepository<E>,
        M extends BaseMapper<E, DTO>> implements BaseService<DTO> {
    protected R repository;
    protected M mapper;
    @Autowired
    protected CycleAvoidingMappingContext context;

    public BaseServiceImpl(R r, M m) {
        this.repository = r;
        this.mapper = m;
    }

    @Override
    public DTO saveEntity(DTO e) {
        E entity = repository.save(mapper.toEntity(e, context));
        e = mapper.toDto(entity, context);
        return e;
    }

    @Override
    public void delete(DTO e) {
        repository.delete(mapper.toEntity(e, context));
    }

    @Override
    public DTO update(DTO e) {
        return mapper.toDto(repository.saveAndFlush(mapper.toEntity(e, context)), context);
    }

    @Override
    public DTO findById(Long id) {
        E e = repository.findById(id).orElse(null);
        DTO dto = mapper.toDto(e, context);
        return dto;
    }

    @Override
    public List<DTO> findAll(){
        return mapper.toDtos(repository.findAll(), context);
    }
}
