package com.raminagrobis.centraleachat.infra.produit

import com.raminagrobis.centraleachat.domain.administration.model.Produit
import org.springframework.data.repository.CrudRepository

interface SQLProduit : CrudRepository<Produit,String>{

}
