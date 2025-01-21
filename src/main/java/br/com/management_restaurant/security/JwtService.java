package br.com.management_restaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {

    private String chave = "rOeb/oUMs2eYh+gr7MXj/gshXChJkBL+uRNYfX5p2wrCLFyzMZnobWixn+ADQSeXhDrP4Psjx+pwhbESE87IkQ==";
    private Integer expiration = 120;

    public String gerarToken(String username){
        LocalDateTime datetime = LocalDateTime.now().plusMinutes(expiration);
        Instant instant = datetime.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chave)
                .compact();
    }

    private Claims obterToken(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token){
        try {
            Claims claim = obterToken(token);
            Date data = claim.getExpiration();
            LocalDateTime localDateTime = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);

        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token){
        return (String) obterToken(token).getSubject();
    }
}
