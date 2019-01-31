package com.vsaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.vsaa.services.AuthenticationService;
import com.vsaa.services.ContatoService;
import com.vsaa.util.Constantes;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
	@Autowired
	private AuthenticationService authenticationService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**","/resources/static/**").permitAll()
                .antMatchers("/home/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/listarContatos").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/alterarContato").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/inserirContato").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/removerContato").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/salvar").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/alterar").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/alterar/").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/alterar/*").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/alterar/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO)
                .antMatchers("/remover/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO,Constantes.PERMISSAO_CONTATO)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles(Constantes.PERMISSAO_ADMINISTRADOR,Constantes.PERMISSAO_CONTATO);
        
        auth.userDetailsService(authenticationService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    	
}