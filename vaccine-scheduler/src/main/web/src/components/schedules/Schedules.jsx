import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { Container } from 'react-bootstrap';

import { selectUser } from '../../store/user/userSelector';
import { getFromResourceServerWithHeader } from '../../utils/resources/fetchRequests';
import { authErrorHandling } from '../../utils/errorHandling/errorHandling';
import { getResourcesForErrorHandling } from '../../utils/resources/resourcesForErrorHandling';

import OldSchedules from './OldSchedules';
import UpcomingSchedules from './UpcomingSchedules';

import './Schedules.css';

const Schedules = () => {
  const user = useSelector(selectUser);
  const [schedules, setSchedules] = useState([]);
  const { dispatch, navigate, auth } = getResourcesForErrorHandling();

  useEffect(() => {
    const fetchSchedules = async () => {
      try {
        const schedulesData = await getFromResourceServerWithHeader(
          `users/${user.userId}/userSchedules`,
          auth
        );
        setSchedules(schedulesData);
      } catch (err) {
        await authErrorHandling(err, dispatch, navigate, auth);
      }
    };
    fetchSchedules();
  }, []);

  return (
    <Container>
      <UpcomingSchedules
        schedules={schedules}
        setSchedules={setSchedules}
        auth={auth}
      />
      <OldSchedules
        schedules={schedules}
        setSchedules={setSchedules}
        auth={auth}
      />
    </Container>
  );
};

export default Schedules;
