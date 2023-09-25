package br.com.felipe.gorisfood.authorizationserver.config;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

import br.com.felipe.gorisfood.authorizationserver.properties.GorisFoodSecurityProperties;
import br.com.felipe.gorisfood.authorizationserver.properties.JwtKeyStoreProperties;
import br.com.felipe.gorisfood.authorizationserver.service.JpaUserDetailsService;

@Configuration
public class AuthorizationServerConfig {

	private static final int HORA_EM_SEGUNDOS = 3600;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		return http.build();
	}
	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////		security.checkTokenAccess("isAuthenticated()");
//		security.checkTokenAccess("permitAll()")
//			.tokenKeyAccess("permitAll()") //permite expor endpoint para visualizar a chave pública do certificado. Opção mais simples caso não tenha instalado o OPENSSL para pegar a chave pública direto do certificado.
//			.allowFormAuthenticationForClients()
//		;
//	}
	
	
	@Bean
	ProviderSettings providerSettings(GorisFoodSecurityProperties properties) {
		return ProviderSettings
				.builder()
				.issuer(properties.getProviderUrl())
				.build();
	}
	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		var tokenEnhancerChain = new TokenEnhancerChain();
//		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(
//				new JwtCustomClaimTokenEnhancer(), // O nosso Custom tem que ser o primeiro da lista, senão não funciona
//				jwtAccessTokenConverter()))
//		;
//		
//		endpoints
//			.authenticationManager(authenticationManager)
//			.authorizationCodeServices(new RedisAuthorizationCodeServices(redisConnectionFactory))
//			.userDetailsService(userDetailsService)
//			.reuseRefreshTokens(false)
//			.accessTokenConverter(jwtAccessTokenConverter())
//			.tokenEnhancer(tokenEnhancerChain)
//			.approvalStore(approvalStore(endpoints.getTokenStore())) //Esse método tem q ser depois do accessTokenConverter
//			.tokenGranter(tokenGranter(endpoints))
//			
//		;
//	}
		
	
	@Bean
	RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
		RegisteredClient registeredClient = RegisteredClient
			.withId("1")
			.clientId("gorisfood-backend")
			.clientSecret(passwordEncoder.encode("backend123"))
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
			.scope("READ")
			.tokenSettings(TokenSettings.builder()
					.accessTokenFormat(OAuth2TokenFormat.REFERENCE)
					.accessTokenTimeToLive(Duration.ofMinutes(30))
					.build())
			.build();
		return new InMemoryRegisteredClientRepository(Arrays.asList(registeredClient));
	}
	
	@Bean
	OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.jdbc(dataSource);
//	}
	
	
	
	

	
	
	
	

//	private ApprovalStore approvalStore(TokenStore tokenStore) {
//		var approvalStore = new TokenApprovalStore();
//		approvalStore.setTokenStore(tokenStore);
//		return approvalStore;
//	}

//	@Bean
//	JwtAccessTokenConverter jwtAccessTokenConverter() {
//		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
////		jwtAccessTokenConverter.setSigningKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
//		jwtAccessTokenConverter.setKeyPair(keyPair());
//		return jwtAccessTokenConverter;
//	}

//	@Bean
//	JWKSet jwkSet() {
//		RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
//			.keyUse(KeyUse.SIGNATURE)
//			.algorithm(JWSAlgorithm.RS256)
//			.keyID("gorisfood-key-id")
//			.build();
//		
//		return new JWKSet(rsaKey);
//			
//	}
	
//	private KeyPair keyPair() {
//		var jksResource = jwtKeyStoreProperties.getJksLocation();
//		var keyStorePass = jwtKeyStoreProperties.getPassword();
//		var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
//		
//		var keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
//		return keyStoreKeyFactory.getKeyPair(keyPairAlias);
//		
//	}

//	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
//		var pkceAuthorizatinoCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(), endpoints.getAuthorizationCodeServices(), 
//				endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
//		
//		var granters = Arrays.asList(pkceAuthorizatinoCodeTokenGranter, endpoints.getTokenGranter());
//		
//		return new CompositeTokenGranter(granters);
//	}
	

	
}
