import { getBasicAuthHeader } from '../../../utils/resources/headers';
import { authServerUrl } from '../../../utils/resources/urls';

const fetchAuthToken = async (authCode) => {
  const headers = getBasicAuthHeader();
  const resp = await fetch(
    `${authServerUrl}oauth2/token?grant_type=authorization_code&scope=read&code=${authCode}`,
    { method: 'POST', headers: headers, credentials: 'include' }
  );
  const authData = await resp.json();
  return authData;
};

export default fetchAuthToken;
