import { useEffect, useState } from 'react';
import { Col, Container, Row } from 'react-bootstrap';

import VaccineCard from './vaccineCard/VaccineCard';

import { getFromResourceServerWithHeader } from '../../utils/resources/fetchRequests';
import { authErrorHandling } from '../../utils/errorHandling/errorHandling';
import { getResourcesForErrorHandling } from '../../utils/resources/resourcesForErrorHandling';

const Vaccines = () => {
  const [vaccines, setVaccines] = useState([]);

  const { navigate, dispatch, auth } = getResourcesForErrorHandling();

  useEffect(() => {
    const fetchVaccines = async () => {
      try {
        const fetchedVaccines = await getFromResourceServerWithHeader(
          'vaccines',
          auth
        );
        setVaccines(fetchedVaccines);
      } catch (err) {
        await authErrorHandling(err, dispatch, navigate, auth);
      }
    };
    fetchVaccines();
  }, []);

  return (
    <Container>
      <Row xs={1} md={2} className='g-4'>
        {vaccines.map((vaccine, index) => (
          <Col key={index}>
            <VaccineCard vaccine={vaccine} />
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Vaccines;
