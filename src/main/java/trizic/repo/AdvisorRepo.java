package trizic.repo;

import trizic.model.Advisor;

import java.util.List;

/**
 * Created by AngelQian on 5/4/18.
 */
public interface AdvisorRepo {
    List<Advisor> find(String advisorId, int page, int size) throws Exception;
    Advisor save(Advisor newUser) throws Exception;
}
