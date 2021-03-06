package in.clouthink.nextoa.security.spring;

import in.clouthink.nextoa.bl.service.AccountService;
import in.clouthink.nextoa.bl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    /**
     * @param username the account name (may be in email format)
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (!StringUtils.isEmpty(username)) {
            username = username.trim().toLowerCase();
        }
        User user = accountService.findAccountByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("无效的用户名%s", username));
        }

        return new UserDetails(user);
    }

}
