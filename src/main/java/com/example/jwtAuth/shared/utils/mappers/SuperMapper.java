package com.example.jwtAuth.shared.utils.mappers;

import com.example.jwtAuth.models.audits.AuditModel;
import java.util.List;
public interface SuperMapper<E extends AuditModel, D> {
    public D toDto(E e);
    public E toEntity(D d);
    public List<D> toDtoList(List<E> eList);
    public List<E> toEntityList(List<D> eList);
}
