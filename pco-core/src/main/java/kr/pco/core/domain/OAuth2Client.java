package kr.pco.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chano
 * 2021. 8. 18.
 */
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "oauth_client_details")
public class OAuth2Client {
	
	@Id
	@Column(name = "oauth_seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer warehouseSeq;
    @Column(name = "client_id", nullable = false)
    private String clientId;
    @Column(name = "resource_ids")
    private String resourceIds;
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;
    @Column(name = "scope", nullable = false)
    private String scope;
    @Column(name = "authorized_grant_types", nullable = false)
    private String grantTypes;
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;
    @Column(name = "authorities", nullable = false)
    private String authorities;
    @Column(name = "access_token_validity", nullable = false)
    private Integer accessTokenValidity;
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    @Column(name = "additional_information", length = 4096)
    private String additionalInfo;
    @Column(name = "autoapprove")
    private String autoApprove;

    // Getters and Setters

}