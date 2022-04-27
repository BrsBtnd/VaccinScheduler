import { Card, Table } from 'react-bootstrap';

import SchedulesView from './schedulesView/SchedulesView';
import SchedulesCardBody from './schedulesCardBody/SchedulesCardBody';

import './Schedules.css';

const OldSchedules = ({ schedules, setSchedules, auth }) => {
  const oldSchedules = schedules.filter(
    (schedule) =>
      new Date().getTime() > new Date(schedule.vaccineDate).getTime()
  );

  return (
    <Card className='schedules-card'>
      <Card.Header className='schedules-card-header'>
        <Card.Title>Old Schedules and Vaccines</Card.Title>
      </Card.Header>
      {oldSchedules.length === 0 ? (
        <SchedulesCardBody subtitle={`You don't have any old appointments`} />
      ) : (
        <Table className='schedules-table-style'>
          <thead>
            <tr>
              <th>Vaccine producer</th>
              <th>Center</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {oldSchedules.map((schedule, index) => (
              <SchedulesView
                key={index}
                schedule={schedule}
                schedules={schedules}
                setSchedules={setSchedules}
                isNew={false}
                auth={auth}
              />
            ))}
          </tbody>
        </Table>
      )}
    </Card>
  );
};

export default OldSchedules;
