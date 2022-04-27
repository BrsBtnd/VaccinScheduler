import { useState } from 'react';
import { Card, Table } from 'react-bootstrap';

import SchedulesView from './schedulesView/SchedulesView';
import SchedulesCardBody from './schedulesCardBody/SchedulesCardBody';
import ModalFormSchedules from './modalFormSchedules/ModalFormSchedules';
import CustomButton from '../button/CustomButton';

import './Schedules.css';

const UpcomingSchedules = ({ schedules, setSchedules, auth }) => {
  const [modal, setModal] = useState(false);
  const upcomingSchedules = schedules.filter(
    (schedule) =>
      new Date().getTime() < new Date(schedule.vaccineDate).getTime() //need to change the direction of the condition
  );

  const setModalShow = (event) => {
    event.target.blur();
    setModal(true);
  };

  const setModalHide = () => setModal(false);

  return (
    <Card className='schedules-card'>
      <Card.Header
        className='schedules-card-header'
        id='upcoming-schedules-card-header'
      >
        <Card.Title className='upcoming-schedules-card-title'>
          Upcoming Vaccination Dates
        </Card.Title>
        <CustomButton
          onClick={setModalShow}
          buttonName='+'
          id='upcomming-schedules-button'
        />
        <ModalFormSchedules show={modal} onHide={setModalHide} />
      </Card.Header>
      {upcomingSchedules.length === 0 ? (
        <SchedulesCardBody subtitle={`Make an appointment`} />
      ) : (
        <Table className='schedules-table-style'>
          <thead>
            <tr>
              <th>Vaccine producer</th>
              <th>Center</th>
              <th>Date and time</th>
            </tr>
          </thead>
          <tbody>
            {upcomingSchedules.map((schedule, index) => (
              <SchedulesView
                key={index}
                schedule={schedule}
                schedules={schedules}
                setSchedules={setSchedules}
                isNew={true}
                auth={auth}
              />
            ))}
          </tbody>
        </Table>
      )}
    </Card>
  );
};

export default UpcomingSchedules;
