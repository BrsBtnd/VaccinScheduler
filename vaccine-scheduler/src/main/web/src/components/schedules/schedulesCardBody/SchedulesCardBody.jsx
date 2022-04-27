import { Card } from 'react-bootstrap';

import './SchedulesCardBody.css';

const SchedulesCardBody = ({ subtitle }) => {
  return (
    <Card.Body>
      <Card.Subtitle className='schedules-card-subtitle' as='h5'>
        {subtitle}
      </Card.Subtitle>
    </Card.Body>
  );
};

export default SchedulesCardBody;
