import { deleteSchedule } from '../../../utils/resources/fetchRequests';
import DeleteButton from '../../button/deleteButton/DeleteButton';

import './SchedulesView.css';

const SchedulesView = ({ schedule, schedules, setSchedules, isNew, auth }) => {
  const { userScheduleId, vaccineDate, center, vaccine } = schedule;

  const findScheduleIndex = () => schedules.findIndex((schedule) => schedule.userScheduleId === userScheduleId); 

  const handleScheduleDelete = async (event) => {
    event.target.blur();
    const scheduleIndex = findScheduleIndex();
    
    schedules.splice(scheduleIndex, 1);
    
    setSchedules([...schedules]);
    deleteSchedule(userScheduleId, auth);
  };

  return (
    <tr>
      <td>{vaccine.producer}</td>
      <td>{center.centerName}</td>
      {isNew ? (
        <td>{`${vaccineDate.split('T')[0]} ${vaccineDate.split('T')[1]}`}</td>
      ) : (
        <td>{vaccineDate.split('T')[0]}</td>
      )}
      {isNew ? (
        <td className='schedule-delete-button-table-data'>
          <DeleteButton buttonName='Delete' onClick={handleScheduleDelete} />
        </td>
      ) : (
        <></>
      )}
    </tr>
  );
};

export default SchedulesView;
