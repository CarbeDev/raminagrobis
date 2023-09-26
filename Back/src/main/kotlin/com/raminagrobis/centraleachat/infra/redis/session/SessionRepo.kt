package com.raminagrobis.centraleachat.infra.redis.session

import org.springframework.data.repository.CrudRepository

interface SessionRepo : CrudRepository<SessionEntity,String>{
    fun findByIp(ip : String) : SessionEntity?
}