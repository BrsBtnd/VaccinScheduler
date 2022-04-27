import { Card, Container } from 'react-bootstrap';
import { useLocation } from 'react-router-dom';
import './Center.css';

const Center = () => {
  const location = useLocation();
  const { center } = location.state;
  const { centerName, city, region, street, streetNumber } = center;

  return (
    <Container>
      <Card>
        <Card.Body>
          <Card.Title className='center-name-title'>
            Center name: {centerName}
          </Card.Title>
          <Card.Subtitle className='center-region-subtitle'>
            Region: {region}
          </Card.Subtitle>
          <Card.Text>
            {city} Str: {street}, nr: {streetNumber}
          </Card.Text>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default Center;
