import { signOutUser } from '../../store/user/userAction';
import refreshToken from '../../components/authorization/refreshToken/refreshToken';
import setAuthData from '../../components/authorization/setAuthData/setAuthData';

export const handleIfTokenExpired = (authData, dispatch, navigate) => {
  if (authData === 'Refresh token expired') {
    dispatch(signOutUser());
    navigate('/', { state: { refreshTokenExpired: true } });
  }
};

export const authErrorHandling = async (err, dispatch, navigate, auth) => {
  if (+err.message === 401) {
    navigate('/unauthorized');
  } else {
    const authData = await refreshToken(auth.refresh_token);
    handleIfTokenExpired(authData, dispatch, navigate);
    setAuthData(dispatch, authData);
    window.location.reload(false);
  }
};
