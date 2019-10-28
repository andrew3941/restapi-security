package com.preving.restapi.seguridadApi.security.service;

import com.preving.restapi.seguridadApi.dao.UsuariosExtranetDao;
import com.preving.restapi.seguridadApi.domain.ExtranetUser;
import com.preving.restapi.seguridadApi.exceptions.CustomRestApiException;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorCode;
import com.preving.restapi.seguridadApi.exceptions.errors.RestApiErrorDetail;
import com.preving.restapi.seguridadApi.security.JwtUserFactory;
import com.preving.restapi.seguridadApi.security.crypt.Encryptor;
import com.preving.restapi.seguridadApi.dao.ExtranetUserRepository;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtranetUserServiceImpl implements ExtranetUserService {

    @Value("${frase.autenticacion.extranet}")
    private String fraseAutenticacion;
    @Autowired
    UsuariosExtranetDao usuariosIntranetDao;
    @Autowired
    ExtranetUserRepository userRepository;

    @Override
    public boolean hasActiveSession(String usuarioId) {
        return usuariosIntranetDao.hasActiveSession(usuarioId);
    }

    @Override
    public UserDetails findUserById(String id, String hash) {
        ExtranetUser user = userRepository.findExtranetUserById(Long.parseLong(id));
        if (user == null) {
            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("user.not.found", RestApiErrorCode.USER_NOT_FOUND.getMessage()));
            throw new CustomRestApiException(HttpStatus.BAD_REQUEST, RestApiErrorCode.USER_NOT_FOUND, errores);
        }
        return JwtUserFactory.create(user, hash);
    }

    @Override
    public String getHashByUsuarioId(String usuarioId) {
        Encryptor e = new Encryptor(fraseAutenticacion);
        try {
            return e.encryptAndHashString(usuarioId);
        } catch (CryptoException e1) {
            e1.printStackTrace();

            List<RestApiErrorDetail> errores = new ArrayList<>();
            errores.add(new RestApiErrorDetail("error.codificacion.unicidad", RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD.getMessage()));
            throw new CustomRestApiException(HttpStatus.INTERNAL_SERVER_ERROR, RestApiErrorCode.ERROR_CODIFICACION_UNICIDAD, errores);
        }
    }
}
