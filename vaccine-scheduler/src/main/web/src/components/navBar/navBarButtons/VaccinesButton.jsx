import { useNavigate } from 'react-router-dom';

import CustomButton from '../../button/CustomButton';

const VaccineButton = ({ auth }) => {
  const navigate = useNavigate();

  const navigateToVaccines = (event) => {
    event.target.blur();
    navigate('/vaccines');
  };

  return auth ? (
    <CustomButton onClick={navigateToVaccines} buttonName='Vaccines' />
  ) : (
    <></>
  );
};

export default VaccineButton;
