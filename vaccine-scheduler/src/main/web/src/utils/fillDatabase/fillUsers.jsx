import { useEffect, useState } from 'react';
import { Container } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { selectAuth } from '../../store/user/userSelector';
import { getAuthorizationHeader } from '../resources/headers';
import { resourceServerApiUrl } from '../resources/urls';

const FillUsers = () => {
  const [data, setData] = useState([]);
  const auth = useSelector(selectAuth);

  useEffect(() => {
    // const fetchDummyJson = async () => {
    //   const resp = await fetch('https://dummyjson.com/users?skip=90');
    //   const respData = await resp.json();
      
    //   setData(respData.users);
    // };
    // fetchDummyJson();

    const body = {
      firstName: "admin fname",
      lastName: "admin lname",
      age: 27,
      gender: "female",
      username: "admin",
      password: "1",
      cnp: "1234567891011",
      userRole: "admin",
    };
    console.log(body);
    fetch(`${resourceServerApiUrl}users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${auth.access_token}`,
      },
      credentials: 'include',
      body: JSON.stringify(body),
    });
  }, []);


  return (
    <Container>
      {/* {data.forEach((d) => {
        
        d.ip = d.ip.slice(0,13);
        console.log(d.ip);
        const {
          age,
          firstName,
          lastName,
          gender,
          password,
          username,
          ip: cnp,
        } = d;
        
        const body = {
          age,
          firstName,
          lastName,
          gender,
          password,
          username,
          cnp,
        };
        console.log(body);
        fetch(`${resourceServerApiUrl}users`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${auth.access_token}`,
          },
          credentials: 'include',
          body: JSON.stringify(body),
        });
      })} */}
    </Container>
  );
};

export default FillUsers;
