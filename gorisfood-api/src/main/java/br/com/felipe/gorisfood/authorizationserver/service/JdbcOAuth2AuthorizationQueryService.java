package br.com.felipe.gorisfood.authorizationserver.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class JdbcOAuth2AuthorizationQueryService implements OAuth2AuthorizationQueryService {

	private final JdbcOperations jdbcOperations;
	private final RowMapper<RegisteredClient> registeredClientRowMapper;
	private final RowMapper<OAuth2Authorization> oAuth2AuthorizationRowMapper;
	
	public JdbcOAuth2AuthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository repository) {
		this.jdbcOperations = jdbcOperations;
		this.registeredClientRowMapper = new JdbcRegisteredClientRepository.RegisteredClientRowMapper();
		this.oAuth2AuthorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(repository);
	}
	
	@Override
	public List<RegisteredClient> listClientsWithConsent(String principalName) {
		//Text Block (incluido no java 15)
		final String listAuthorizedClients = """
			select rc.*
			from oauth2_authorization_consent c
			inner join oauth2_registered_client rc
				on rc.id = c.registered_client_id
			where c.principal_name = ?
		""";
		
		return this.jdbcOperations.query(listAuthorizedClients, registeredClientRowMapper, principalName);
	}

	@Override
	public List<OAuth2Authorization> listAuthorizations(String principalName, String clientId) {
		// Text Bloc (incluido no java 15)
		final String LIST_AUTHORIZATIONS_QUERY = """
			SELECT a.*
			FROM oauth2_authorization a
			INNER JOIN oauth2_registered_client rc
				ON rc.id = a.registered_client_id
			WHERE a.principal_name = ?
			AND a.registered_client_id = ?
		""";
		
		return this.jdbcOperations.query(LIST_AUTHORIZATIONS_QUERY, oAuth2AuthorizationRowMapper, principalName, clientId);
	}

}
