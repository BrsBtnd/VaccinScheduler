import { useEffect } from 'react';
import setAuthData from '../setAuthData/setAuthData';
import fetchAuthToken from './fetchAuthToken';
import { getDispatchAndNavigate } from '../../../utils/resources/resourcesForErrorHandling';

const AuthJwtToken = () => {
  const { dispatch, navigate } = getDispatchAndNavigate();

  useEffect(() => {
    const authCode = window.location.href.split('code=')[1];
    const fetchAuth = async () => {
      const authData = await fetchAuthToken(authCode);
      setAuthData(dispatch, authData);
    };
    fetchAuth();

    navigate('/');
  }, []);

  return <></>;
};

export default AuthJwtToken;
