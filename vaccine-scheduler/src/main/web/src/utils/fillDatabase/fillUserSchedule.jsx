import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { selectAuth } from '../../store/user/userSelector';
import { 
  getFromResourceServerWithHeader,
  getFromResourceServer,
} from '../resources/fetchRequests';
import { Container } from 'react-bootstrap';
import { resourceServerApiUrl } from '../resources/urls';

const FillUserSchedule = () => {
  const [users, setUsers] = useState([]);
  const [vaccines, setVaccines] = useState();
  const [centers, setCenters] = useState([]);
  const auth = useSelector(selectAuth);

  useEffect(() => {
    const fetchUsers = async () => {
      const userData = await getFromResourceServerWithHeader('users', auth);
      setUsers(userData);
      // console.log(userData);
    };
    const fetchVaccines = async () => {
      const fetchedVaccines = await getFromResourceServerWithHeader('vaccines', auth);
      setVaccines(fetchedVaccines);
      console.log(vaccines);
    };

    const fetchCenters = async (path) => {
      const fetchedCenters = await getFromResourceServer('centers');
      console.log(fetchedCenters);
      setCenters(fetchedCenters);
    };
    fetchVaccines();
    fetchUsers();
    fetchCenters();
  }, []);


  //center 31-56
  //vaccines 1-8
  return (
    <Container>
      {
      users.forEach((user) => {
        const vaccineId = Math.floor(Math.random() * 6 );
        const centerId = Math.floor(Math.random() * 24 );
        const start = new Date('2020-01-01T14:00:00');
        const end = new Date();
        const vaccineDate = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
        const body = {
          center: centers[centerId],
          vaccine: vaccines[vaccineId],
          user,
          // centerId, 
          // vaccineId,
          // userId: user.userId,
          vaccineDate
        }
        // console.log(vaccineId, centerId);
        // console.log(body);
        // fetch(`${resourceServerApiUrl}userSchedules`, {
        //   method: 'POST',
        //   headers: {
        //     'Content-Type': 'application/json',
        //     'Authorization': `Bearer ${auth.access_token}`,
        //   },
        //   credentials: 'include',
        //   body: JSON.stringify(body),
        // })  
      })  
      }
    </Container>
  );
};

export default FillUserSchedule;
