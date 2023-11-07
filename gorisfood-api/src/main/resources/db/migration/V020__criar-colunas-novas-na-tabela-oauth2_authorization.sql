ALTER TABLE oauth2_authorization
    ADD user_code_value blob DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD user_code_issued_at timestamp DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD user_code_expires_at timestamp DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD user_code_metadata blob DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD device_code_value blob DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD device_code_issued_at timestamp DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD device_code_expires_at timestamp DEFAULT NULL;
ALTER TABLE oauth2_authorization
    ADD device_code_metadata blob DEFAULT NULL;