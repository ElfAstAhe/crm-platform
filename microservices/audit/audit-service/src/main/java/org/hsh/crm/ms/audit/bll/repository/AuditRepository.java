package org.hsh.crm.ms.audit.bll.repository;

import org.hsh.crm.ms.audit.bll.model.Audit;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Local
public interface AuditRepository {
    List<Audit> listAll() throws ExecutionException, InterruptedException;
}
