import { useState } from 'react';
import { Container, FloatingLabel, FormControl } from 'react-bootstrap';

import CenterPreview from '../centerPreview/CenterPreview';
import { getFromResourceServer } from '../../../utils/resources/fetchRequests';

import './CenterSearch.css';

const CenterSearch = () => {
  const [centers, setCenters] = useState([]);

  const fetchCenters = async (path) => {
    const fetchedCenters = await getFromResourceServer(
      `centers/search/${path}`
    );
    setCenters(fetchedCenters);
  };

  const getCenters = (event) => {
    return event.target.value.length
      ? fetchCenters(event.target.value)
      : setCenters([]);
  };

  return (
    <>
      <Container>
        <FloatingLabel
          id='center-search-form-floating'
          label='Search for a vaccine center'
        >
          <FormControl
            placeholder='Search for a vaccine center'
            onChange={getCenters}
          ></FormControl>
        </FloatingLabel>
      </Container>
      <Container>
        {centers.map((center) => (
          <CenterPreview key={center.centerId} center={center} />
        ))}
      </Container>
    </>
  );
};

export default CenterSearch;
