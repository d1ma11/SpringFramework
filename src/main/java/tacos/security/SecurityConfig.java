package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.data.UserRepository;
import tacos.model.User;

@Configuration
public class SecurityConfig {
    /*
     * Объявляем bean-компонент PasswordEncoder, который
     * мы будем использовать при создании новых пользователей
     * и при аутентификации
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // /*
    // Предположим, что всего несколько учетных записей, которые вряд ли изменятся в
    // будущем.
    // В этом случае было бы более чем достаточно определить эти учетные записи как
    // часть конфигурации безопасности
    // */
    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    // List<UserDetails> usersList = new ArrayList<>();
    // usersList.add(new User(
    // "pushkin", encoder.encode("yaProigral"),
    // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    // usersList.add(new User(
    // "dantes", encoder.encode("yaPobedil"),
    // Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    // return new InMemoryUserDetailsManager(usersList);
    // }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .mvcMatchers("/design", "/orders").hasRole("USER")
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .build();
    }

    // /*
    // Выражения могут быть гораздо гибкими. Например,
    // представим, что нам понадобилось разрешить создавать новые тако только
    // пользователям
    // с полномочиями ROLE_USER и только по вторникам
    // */
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // return http
    // .authorizeRequests()
    // .antMatchers("/design","/orders")
    // .access("hasRole('USER') && " +
    // "T(java.util.Calendar).getInstance().get("+
    // "T(java.util.Calendar).DAY_OF_WEEK) == " +
    // "T(java.util.Calendar).TUESDAY")
    // .antMatchers("/","/**").access("permitAll")
    // .and()
    // .build();
    // }

}
