import { useNavigate } from 'react-router-dom';

import CustomButton from '../../button/CustomButton';

const UserButton = ({ userRole }) => {
  const navigate = useNavigate();

  const navigateToUsers = (event) => {
    event.target.blur();
    navigate('/users');
  };

  return userRole === 'ADMIN' ? (
    <CustomButton onClick={navigateToUsers} buttonName='Users' />
  ) : (
    <></>
  );
};

export default UserButton;
