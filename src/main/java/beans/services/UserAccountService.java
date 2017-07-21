package beans.services;

import beans.daos.UserAccountDao;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Transactional
    public void saveMoney(UserAccount userAccount) {

        userAccountDao.save(userAccount);
    }
}
