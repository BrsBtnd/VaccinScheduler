import { useNavigate } from 'react-router-dom';
import CustomButton from '../../button/CustomButton';

const SchedulesButton = ({ auth }) => {
  const navigate = useNavigate();

  const navigateToSchedules = (event) => {
    event.target.blur();
    navigate('/schedules');
  };

  return auth ? (
    <CustomButton onClick={navigateToSchedules} buttonName='My Schedules' />
  ) : (
    <></>
  );
};

export default SchedulesButton;
