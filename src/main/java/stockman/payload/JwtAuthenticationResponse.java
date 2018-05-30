package stockman.payload;

import lombok.*;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private Long expiresInMsec;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, Long expiresInMsec) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresInMsec = expiresInMsec;
    }

}