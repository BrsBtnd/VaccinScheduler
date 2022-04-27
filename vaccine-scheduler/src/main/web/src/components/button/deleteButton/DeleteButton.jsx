import { Button } from 'react-bootstrap';

import './DeleteButton.css';

const DeleteButton = ({ buttonName, onClick }) => {
  return (
    <Button className='delete-button' onClick={onClick}>
      {buttonName}
    </Button>
  );
};

export default DeleteButton;
