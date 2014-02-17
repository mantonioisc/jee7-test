package test.jee7.ejbs;

import javax.ejb.Local;


@Local
public interface BusinessService {
    void businessOperation();
}
