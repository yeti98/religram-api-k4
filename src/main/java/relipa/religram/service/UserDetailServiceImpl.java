package relipa.religram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import relipa.religram.custom_repository.UserRepository;
import relipa.religram.entity.User;
import relipa.religram.configer.security.CustomUserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Value("${message.unauthorized}")
    private String unauthorizedMessage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = userRepository.findByEmail(username);
        }
        if (user == null)
            throw new InternalAuthenticationServiceException("Khong tim thay user");
        return new CustomUserDetails(user);

    }

    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null)
            throw new InternalAuthenticationServiceException(unauthorizedMessage);

        return new CustomUserDetails(user);
    }
}