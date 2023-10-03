package com.raminagrobis.centraleachat.infra.redis.session

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash(value = "Session", timeToLive = (5*60*60).toLong())
class SessionEntity(
    @Id
    var jwt : String? = null,
    var ip : String? =null
) : Serializable