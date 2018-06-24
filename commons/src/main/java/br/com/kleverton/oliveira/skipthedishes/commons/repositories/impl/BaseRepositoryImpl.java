package br.com.kleverton.oliveira.skipthedishes.commons.repositories.impl;

import br.com.kleverton.oliveira.skipthedishes.commons.dtos.BaseDTO;
import br.com.kleverton.oliveira.skipthedishes.commons.repositories.BaseRespository;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T extends Table, E extends BaseDTO> implements BaseRespository<E> {
    @Autowired
    private DSLContext dsl;

    private Table<UpdatableRecord> table;
    private Class<E> dtoClass;

    public BaseRepositoryImpl(T table, Class<E> dtoClass) {
        this.table = table;
        this.dtoClass = dtoClass;
    }

    protected DSLContext getDsl() {
        return this.dsl;
    }

    @Override
    public List<E> findAll() {
        return this.dsl.select().from( this.table ).fetchInto( this.dtoClass );
    }

    @Override
    public <V extends Serializable> List<E> findBy(TableField field, V value) {
        return this.dsl.select().from( this.table ).where( field.eq( value ) ).fetchInto( this.dtoClass );
    }

    @Override
    public E save(E record) {
        final UpdatableRecord result = this.dsl.newRecord( this.table, record );

        result.store();

        record = result.into( this.dtoClass );

        return record;
    }

    @Override
    public void delete(E record) {
        this.dsl.delete( this.table ).where( this.table.getIdentity().getField().getName() + " = " + record.getId() ).execute();
    }
}