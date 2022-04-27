import { getBasicAuthHeader } from '../../../utils/resources/headers';
import { authServerUrl } from '../../../utils/resources/urls';

const refreshToken = async (refreshToken) => {
  const headers = getBasicAuthHeader();
  const resp = await fetch(
    `${authServerUrl}oauth2/token?grant_type=refresh_token&refresh_token=${refreshToken}`,
    { method: 'POST', headers: headers, credentials: 'include' }
  );
  if (resp.status >= 400) {
    return 'Refresh token expired';
  }
  const authData = await resp.json();

  return authData;
};

export default refreshToken;
