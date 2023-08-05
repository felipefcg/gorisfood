package br.com.felipe.gorisfood.auth.core.config;

import java.security.KeyPair;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import br.com.felipe.gorisfood.auth.core.properties.JwtKeyStoreProperties;
import br.com.felipe.gorisfood.auth.core.service.JpaUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final int HORA_EM_SEGUNDOS = 3600;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
				.withClient("gorisfood-web")
				.secret(passwordEncoder.encode("web123"))
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("write", "read")
				.accessTokenValiditySeconds(HORA_EM_SEGUNDOS * 6)
				.refreshTokenValiditySeconds(HORA_EM_SEGUNDOS * 24 * 2)
			.and()
				.withClient("checktoken")
				.secret(passwordEncoder.encode("check123"))
			.and()
				.withClient("faturamento")
				.secret(passwordEncoder.encode("fat123"))
				.authorizedGrantTypes("client_credentials")
				.scopes("write", "read")
			.and()
				.withClient("foodanalytics")
				.secret(passwordEncoder.encode("fan123"))
				.authorizedGrantTypes("authorization_code")
				.scopes("write", "read")
				.redirectUris("http://aplicacao-cliente")
			.and()
				.withClient("webadmin")
				.authorizedGrantTypes("implicit")
				.scopes("write", "read")
				.redirectUris("http://aplicacao-cliente")
			;
		
		
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
		security.checkTokenAccess("permitAll()")
			.tokenKeyAccess("permitAll()") //permite expor endpoint para visualizar a chave pública do certificado. Opção mais simples caso não tenha instalado o OPENSSL para pegar a chave pública direto do certificado.
			.allowFormAuthenticationForClients()
		;
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		var tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(
				new JwtCustomClaimTokenEnhancer(), // O nosso Custom tem que ser o primeiro da lista, senão não funciona
				jwtAccessTokenConverter()));
		
		endpoints
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.reuseRefreshTokens(false)
			.accessTokenConverter(jwtAccessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain)
			.approvalStore(approvalStore(endpoints.getTokenStore())) //Esse método tem q ser depois do accessTokenConverter
			.tokenGranter(tokenGranter(endpoints))
			
		;
	}


	private ApprovalStore approvalStore(TokenStore tokenStore) {
		var approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore);
		return approvalStore;
	}

	@Bean
	JwtAccessTokenConverter jwtAccessTokenConverter() {
		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
//		jwtAccessTokenConverter.setSigningKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
		jwtAccessTokenConverter.setKeyPair(keyPair());
		return jwtAccessTokenConverter;
	}

	private KeyPair keyPair() {
		var jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());
		var keyStorePass = jwtKeyStoreProperties.getPassword();
		var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
		
		var keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
		return keyStoreKeyFactory.getKeyPair(keyPairAlias);
		
	}

	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		var pkceAuthorizatinoCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(), endpoints.getAuthorizationCodeServices(), 
				endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
		
		var granters = Arrays.asList(pkceAuthorizatinoCodeTokenGranter, endpoints.getTokenGranter());
		
		return new CompositeTokenGranter(granters);
	}
	

	
}
