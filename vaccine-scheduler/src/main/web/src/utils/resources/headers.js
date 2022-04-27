import base64 from 'base-64';

export const getAuthorizationHeader = (auth) => {
  const headers = new Headers();
  headers.append('Authorization', `Bearer ${auth?.access_token}`);
  return headers;
};

export const getBasicAuthHeader = () => {
  const headers = new Headers();
  headers.append(
    'Authorization',
    'Basic ' + base64.encode('react_client:react_secret')
  );
  return headers;
};
