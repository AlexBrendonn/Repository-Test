package com.saa.web.control;

import com.saa.web.entity.authentication.AuthenticationToken;
import com.saa.web.entity.authentication.Company;
import com.saa.web.entity.authentication.Organization;
import com.saa.web.entity.authentication.User;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;

public class MainControl {
    protected AuthenticationToken token;
    protected User user;
    protected Company company;
    protected Organization organization;

    public MainControl(AuthenticationToken token) {
        this.token = token;
        this.user = token.getUser();
        this.company = token.getUser().getCompany();
        this.organization = this.token.getOrganization();

        Optional.ofNullable(this.token).orElseThrow(() -> new NotAuthorizedException("Token inválido"));
        Optional.ofNullable(this.user).orElseThrow(() -> new NotAuthorizedException("Usuário não encontrado"));
        Optional.ofNullable(this.company).orElseThrow(() -> new ForbiddenException("Nenhuma empresa ativa neste usuário"));
        Optional.ofNullable(this.token).orElseThrow(() -> new ForbiddenException("Empresa do Usuário não encontrada"));
    }
}
