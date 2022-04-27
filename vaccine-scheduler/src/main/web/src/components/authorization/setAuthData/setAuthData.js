import jwt_decode from 'jwt-decode';
import { signInUser, setUser } from '../../../store/user/userAction';
import { getUserFromResourceServer } from '../../../utils/resources/fetchRequests';

const setAuthData = async (dispatch, authData) => {
  const decodedJwt = jwt_decode(authData.access_token);
  console.log(authData.access_token);
  const username = decodedJwt.sub;
  const user = await getUserFromResourceServer(username, authData);
  dispatch(signInUser(authData));
  dispatch(setUser(user));
};

export default setAuthData;
