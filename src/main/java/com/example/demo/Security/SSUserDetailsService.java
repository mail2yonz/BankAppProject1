package com.example.demo.Security;


import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service

public class SSUserDetailsService implements UserDetailsService {

    //    information can be passed back
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    //Creating a UserDetials SErivce file you must have this method inside class
    //This is th e username that I have and this is what SPring needs to do
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Username String returns userDetials or userdata
        try{
            //Find usernamefrom database
            User user = userRepository.findByUsername(username);
            if(user==null)
            {
                return null;
            }
            //Creates and new user from depency from pon file username is all from the entity
            //Translates our user to spring user using information from the dtatbase
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user));

        }catch (Exception e)
        {

        }
        return null;
    }
    //Creates Roles for spring sercurity
    //Granted authority's allows
    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role eachRole:user.getRoles())
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(eachRole.getRole());
            authorities.add(grantedAuthority);
            System.out.println("Granted Authority"+grantedAuthority.toString());
        }
        return authorities;
    }
}