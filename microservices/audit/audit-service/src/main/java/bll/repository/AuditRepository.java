package bll.repository;

import bll.model.Audit;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Local
public interface AuditRepository {
    List<Audit> listAll() throws ExecutionException, InterruptedException;
}
