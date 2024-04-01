package com.udea.serviagenda.controller;

import com.udea.serviagenda.dominio.user.dto.UserAuthData;
import com.udea.serviagenda.dominio.user.model.User;
import com.udea.serviagenda.infra.security.DatosJWttoken;
import com.udea.serviagenda.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class authController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWttoken> autenticarUsuario(@RequestBody @Valid UserAuthData userAuthData ){
        Authentication authToken= new UsernamePasswordAuthenticationToken(userAuthData.email(),userAuthData.password());
        var userAuhtenticate = authenticationManager.authenticate(authToken);
        var JWttoken = tokenService.makeToken((User) userAuhtenticate.getPrincipal());
        return ResponseEntity.ok(new DatosJWttoken(JWttoken));
    }
}
