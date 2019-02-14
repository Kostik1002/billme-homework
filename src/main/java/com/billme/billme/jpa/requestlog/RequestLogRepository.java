package com.billme.billme.jpa.requestlog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestLogRepository extends CrudRepository<RequestLog, Long> {

    @Override
    List<RequestLog> findAll();

}
