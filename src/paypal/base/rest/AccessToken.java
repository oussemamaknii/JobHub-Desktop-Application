package paypal.base.rest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class AccessToken {
    public AccessToken(String accessToken, long expires) {
        this.accessToken = accessToken;
        this.expires = expires;
    }

    private String accessToken;
    private long expires = 0;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    /**
     * Specifies how long this token can be used for placing API calls. The
     * remaining lifetime is given in seconds.
     *
     * @return remaining lifetime of this access token in seconds
     */
    public long expiresIn() {
        return expires - new java.util.Date().getTime() / 1000;
    }

}
