package com.saa.web.utils;

import com.saa.web.entity.authentication.User;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.JoseException;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

/**
 * <h1>{@link TokenJWT}</h1>
 * Classe responsavel para criação dos tokens de autenticação.
 */
public class TokenJWT {
    private final String secretKey = "FDC78EDA51DE656CB587DA5783FE0B";
    private final String issuer = "www.saasoftware.com.br";
    private final String audience = "SAA Software LTDA";
    private final Integer expiresAtDays = 2;

    private static EllipticCurveJsonWebKey senderJwk;

    public void createAlgorithm() throws Exception {
        try {
            EllipticCurveJsonWebKey senderJwk = EcJwkGenerator.generateJwk(EllipticCurves.P521);
            senderJwk.setKeyId(this.secretKey);
            this.senderJwk = senderJwk;
        } catch (JoseException e) {
            throw new Exception(e.getMessage());
        }
    }

    public String create(User user) {
        return this.create(user, this.expiresAtDays);
    }

    public String create(User user, Integer days) {
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(this.issuer);
            claims.setAudience(this.audience);
            claims.setExpirationTimeMinutesInTheFuture(days * 24 * 60);
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(2);
            claims.setSubject(user.getOrganization().getNickname() + "." + user.getNickname());

            claims.setClaim("user", user.getNickname());
            claims.setClaim("organization", user.getOrganization().getNickname());
            claims.setClaim("company", user.getCompany().getName());

            JsonWebSignature jws = new JsonWebSignature();

            jws.setPayload(claims.toJson());

            jws.setKey(senderJwk.getPrivateKey());
            jws.setKeyIdHeaderValue(senderJwk.getKeyId());

            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512);

            return jws.getCompactSerialization();

        } catch (JoseException ex) {
            throw new NotAuthorizedException(ex.getMessage());
        }
    }

    public void valid(String token) {
        try {
            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setRequireJwtId()
                    .setAllowedClockSkewInSeconds(30)
                    .setRequireSubject()
                    .setExpectedIssuer(this.issuer)
                    .setExpectedAudience(this.audience)
                    .setVerificationKey(senderJwk.getKey())
                    .setJwsAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST, AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512))
                    .build();

            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

        } catch (InvalidJwtException ex) {

            if (ex.hasExpired())
                throw new NotAuthorizedException("Acesso Negado - Token expirado", Response.Status.UNAUTHORIZED);

            if (ex.hasErrorCode(ErrorCodes.AUDIENCE_INVALID))
                throw new NotAuthorizedException("Acesso Negado - Token inválido", Response.Status.UNAUTHORIZED);

            throw new NotAuthorizedException("Acesso Negado - " + ex.getMessage(), Response.Status.UNAUTHORIZED);
        } catch (Exception ex) {
            throw new InternalServerErrorException("Erro Interno - " + ex.getMessage());
        }

    }
}
