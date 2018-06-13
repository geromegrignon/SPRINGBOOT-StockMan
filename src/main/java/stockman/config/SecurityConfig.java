package stockman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import stockman.security.CustomUserDetailsService;
import stockman.security.JwtAuthenticationEntryPoint;
import stockman.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity 
@EnableGlobalMethodSecurity( //enable security based on annotations
        securedEnabled = true, // @Secured on methods
        jsr250Enabled = true, // @RolesAllowed on methods
        prePostEnabled = true // @PreAuthorize @PostAuthorize more complex access control on methods
)
public class SecurityConfig extends WebSecurityConfigurerAdapter { // provide custom security configurations

    @Autowired
    CustomUserDetailsService customUserDetailsService; // methods to load user

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler; // handles 401 error if trying to access a protected resource without proper authentication

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() { //read, validates token, load user with token
        return new JwtAuthenticationFilter();
    }
    

    // main interface for authentication
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // define accesses
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests() //all requests must be authorized
                    .antMatchers("/api/auth/**")
                        .permitAll()
                    .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/supply/**", "/api/provider/**", "/api/request/**", "/api/user/**")
                        .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/supply/**", "/api/provider/**", "/api/request/**")
                        .permitAll()
                    .antMatchers(HttpMethod.PUT, "/api/supply/**", "/api/provider/**", "/api/request/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated();
        
//        http
//        	.authorizeRequests()
//        		.anyRequest().hasRole("USER")
//        		.and()
//        	.formLogin()
//        		.loginPage("/signin")
//        		.permitAll()
//        		.successHandler(loginSuccessHandler())
//        		.failureHandler(loginFailureHandler())
//        		.and()
//        	.logout()
//        		.permitAll()
//        		.logoutSuccessUrl("/login")
//        		.and()
//        	.csrf();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

//	private AuthenticationFailureHandler loginFailureHandler() {
//		// TODO Auto-generated method stub
//		return (request, response, authentication) -> response.sendRedirect("/");
//	}
//
//	private AuthenticationSuccessHandler loginSuccessHandler() {
//		// TODO Auto-generated method stub
//		return (request, response, exception) -> {
//			request.getSession().setAttribute("flash", new FlashMessage("incorrect information", FlashMessage.Status.FAILURE));
//			response.sendRedirect("/login");
//		};
//		};
	}
