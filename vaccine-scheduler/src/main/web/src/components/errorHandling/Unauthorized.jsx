import { Container } from 'react-bootstrap';

import './Unauthorized.css';

const Unauthorized = () => {
  return (
    <Container className='error-message'>
      <h1>Unauthorized please Login</h1>
    </Container>
  );
};

export default Unauthorized;
