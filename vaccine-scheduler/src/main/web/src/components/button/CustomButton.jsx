import { Button } from 'react-bootstrap';

import './CustomButtonStyle.css';

const CustomButton = ({ buttonName, onClick, id = undefined }) => {
  return (
    <Button onClick={onClick} className='log-button' id={id}>
      {buttonName}
    </Button>
  );
};

export default CustomButton;
