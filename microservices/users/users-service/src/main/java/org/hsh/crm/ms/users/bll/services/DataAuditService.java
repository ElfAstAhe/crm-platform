package org.hsh.crm.ms.users.bll.services;

import org.hsh.crm.ms.common.bll.services.audit.AuditClientParams;
import org.hsh.crm.ms.common.bll.services.audit.BaseDataAuditService;
import org.hsh.crm.ms.users.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.users.bll.settings.UsersSettingsEnum;
import org.hsh.ms.common.ep.client.BaseRestClient;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.net.ssl.SSLSession;

@LocalBean
@Stateless
public class DataAuditService extends BaseDataAuditService {
    @EJB
    private UsersSettingsRepository repoSettings;

    @Override
    protected AuditClientParams getClientParams() {
        return new AuditClientParams(repoSettings.getStringValue(UsersSettingsEnum.AUDIT_SERVICE),
                                     repoSettings.getIntegerValue(UsersSettingsEnum.AUDIT_CONNECTION_TIME_OUT)
                                                 .longValue(),
                                     repoSettings.getIntegerValue(UsersSettingsEnum.AUDIT_READ_TIME_OUT)
                                                 .longValue(),
                                     this::getJwt,
                                     this::sslHostnameVerifier);
    }

    private String getJwt() {
        return null;
    }

    private boolean sslHostnameVerifier(String hostname, SSLSession sslSession) {
        return true;
    }
}
