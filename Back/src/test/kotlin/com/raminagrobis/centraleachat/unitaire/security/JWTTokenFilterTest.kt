@file:Suppress("NonAsciiCharacters")

package com.raminagrobis.centraleachat.unitaire.security

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenFilter
import com.raminagrobis.centraleachat.app.security.jwt.JWTTokenUtil
import com.raminagrobis.centraleachat.domain.administration.model.Role
import com.raminagrobis.centraleachat.domain.connexion.model.Utilisateur
import com.raminagrobis.centraleachat.infra.redis.session.SessionEntity
import com.raminagrobis.centraleachat.infra.redis.session.SessionRepo
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class JWTTokenFilterTest {

    @Mock
    private lateinit var jwtTokenUtil: JWTTokenUtil
    @Mock
    private lateinit var repo : SessionRepo
    @InjectMocks
    private lateinit var filter : JWTTokenFilter

    @Mock
    private lateinit var request : HttpServletRequest
    @Mock
    private lateinit var response : HttpServletResponse
    @Mock
    private lateinit var filterChain: FilterChain

    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkByYW1pbmFncm9iaXMuY29tIiwiaWF0IjoxNjk1NzM2OTc0LCJleHAiOjE2OTU3NTQ5NzR9.cVwyOzBLlQZuOMTI3oPrIPo6-fP44NB8GFniY1-SUi0"
    private val ip = "192.0.0.1"

    @BeforeEach
    fun setup(){
    }

    @Test
    fun `Le chemin de connexion doit être acessible par tout le monde`() = run {
        //GIVEN
        `when`(request.requestURI).thenReturn("/connexion")
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verify(filterChain).doFilter(request, response)
        verifyNoMoreInteractions(filterChain)
    }

    @Test
    fun `Une route sans role particulier doit etre identifie`() = run {
        //GIVEN
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.requestURI).thenReturn("/produits")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADHERENT))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verify(filterChain).doFilter(request, response)
        verifyNoMoreInteractions(filterChain)
    }

    @Test
    fun `Un appel à une route privé non identifié ne doit pas être exécuté`() = run {
        //GIVEN
        `when`(request.requestURI).thenReturn("/produits")
        `when`(request.getHeader("Authorization")).thenReturn(null)
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }
    @Test
    fun `Un appel avec un token non valide ne doit pas être exécuter`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/produits")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADHERENT))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(false)
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }

    @Test
    fun `Un appel avec un token qui est relié à une autre adresse IP ne doit pas être exécuté`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/admin/desactivate/VISPRO")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADMIN))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(null)
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }

    @Test
    fun `Un appel avec une adresse IP relié à un autre token ne doit pas être effectué `() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/admin/desactivate/VISPRO")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADMIN))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity("autre jwt", ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }

    @Test
    fun `Un appel à une route admin doit être effectué par un admin`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/admin/desactivate/VISPRO")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADMIN))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verify(filterChain).doFilter(request, response)
        verifyNoMoreInteractions(filterChain)
    }


    @Test
    fun `Un appel à une route fournisseur doit être effectué par un fournisseur`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/fournisseur/propositions")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.FOURNISSEUR))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verify(filterChain).doFilter(request, response)
        verifyNoMoreInteractions(filterChain)
    }

    @Test
    fun `Un appel à une route fornisseur effectué par un autre role ne doit pas être exécuter`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/fournisseur/propositions")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADHERENT))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }

    @Test
    fun `Un appel à une route adherent doit être effectué par un adherent`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/adherent/achats")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.ADHERENT))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verify(filterChain).doFilter(request, response)
        verifyNoMoreInteractions(filterChain)
    }

    @Test
    fun `Un appel à une route adherent effectué par un autre role ne doit pas être exécuter`() = run {
        //GIVEN
        `when`(request.remoteAddr).thenReturn(ip)
        `when`(request.getHeader("Authorization")).thenReturn(token)
        `when`(request.requestURI).thenReturn("/adherent/achats")
        `when`(jwtTokenUtil.getUtilisateurFromToken(token)).thenReturn(Utilisateur("test@test.fr","motdepasse",Role.FOURNISSEUR))
        `when`(jwtTokenUtil.validateToken(token)).thenReturn(true)
        `when`(repo.findByIp(ip)).thenReturn(SessionEntity(token,ip))
        //WHEN
        filter.doFilter(request,response, filterChain)
        //THEN
        verifyNoInteractions(filterChain)
    }
}