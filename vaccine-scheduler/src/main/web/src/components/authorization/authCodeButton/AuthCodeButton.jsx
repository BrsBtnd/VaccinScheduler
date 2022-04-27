import { Button } from 'react-bootstrap';

import { authServerUrl } from '../../../utils/resources/urls';

import './AuthCodeButton.css';

const Authorization = () => {
  const simulateClick = (event) => {
    event.click();
  };

  const auth = () => {
    window.location.assign(
      `${authServerUrl}oauth2/authorize?response_type=code&client_id=react_client&scope=read`
    );
  };
  return (
    <Button ref={simulateClick} onClick={auth} id='authorization-button' />
  );
};

export default Authorization;
