import { Card } from 'react-bootstrap';

const VaccineCard = ({ vaccine }) => {
  const { vaccineName, vaccineType, producer, origin } = vaccine;

  return (
    <Card style={{ width: '20rem' }} border='secondary'>
      <Card.Header>{producer}</Card.Header>
      <Card.Body>
        <Card.Title>Origin: {origin}</Card.Title>
        <Card.Subtitle>Vaccine type: {vaccineType}</Card.Subtitle>
        <Card.Text>Research name: {vaccineName}</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default VaccineCard;
