package com.raminagrobis.centraleachat.infra.produit

import com.raminagrobis.centraleachat.infra.produit.entity.ProduitEntity
import org.springframework.data.repository.CrudRepository

interface SQLProduit : CrudRepository<ProduitEntity,String>{

}
