import CustomButton from '../../button/CustomButton';
import { signOutUser } from '../../../store/user/userAction';
import { getDispatchAndNavigate } from '../../../utils/resources/resourcesForErrorHandling';

const LoginLogoutButton = ({ auth }) => {
  const { dispatch, navigate } = getDispatchAndNavigate();

  const logout = (event) => {
    event.target.blur();
    dispatch(signOutUser());
    navigate('/');
  };

  const login = () => {
    window.location.assign('http://localhost:9000/login');
  };
  return auth ? (
    <CustomButton onClick={logout} buttonName='Logout' />
  ) : (
    <CustomButton onClick={login} buttonName='Login' />
  );
};

export default LoginLogoutButton;
