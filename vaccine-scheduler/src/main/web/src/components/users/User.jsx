import { useEffect, useState } from 'react';
import { Container, Pagination, Table } from 'react-bootstrap';

import UserCard from './userCard/UserCard';

import { getFromResourceServerWithHeader } from '../../utils/resources/fetchRequests';
import { getResourcesForErrorHandling } from '../../utils/resources/resourcesForErrorHandling';
import { authErrorHandling } from '../../utils/errorHandling/errorHandling';

import './User.css';

const User = () => {
  const [users, setUsers] = useState([]);
  const [actualUsers, setActualUsers] = useState([]);
  const [paginationButton, setPaginationButton] = useState(1);

  const { navigate, dispatch, auth } = getResourcesForErrorHandling();

  const selectActualUsers = (pageNum, actualUsers = users) => {
    const usersPerPage = actualUsers.slice((pageNum - 1) * 12, pageNum * 12);
    setActualUsers(usersPerPage);
  };

  const generalSettings = (event, pageNum) => {
    event.target.blur();
    selectActualUsers(pageNum);
    setPaginationButton(pageNum);
  };

  const reducePageButton = (event) => {
    generalSettings(event, paginationButton - 1);
  };

  const increasePageButton = (event) => {
    generalSettings(event, paginationButton + 1);
  };

  const setFirstPage = (event) => {
    generalSettings(event, 1);
  };

  const setLastPage = (event) => {
    const lastPageNum = Math.ceil(users.length / 12);
    generalSettings(event, lastPageNum);
  };

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const userData = await getFromResourceServerWithHeader('users', auth);
        setUsers(userData);
        selectActualUsers(1, userData);
      } catch (err) {
        await authErrorHandling(err, dispatch, navigate, auth);
      }
    };
    fetchUsers();
  }, []);

  return (
    <Container>
      <Table striped bordered>
        <thead>
          <tr>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>CNP</th>
            <th>Gender</th>
          </tr>
        </thead>
        <tbody>
          {actualUsers.map((user, index) => (
            <UserCard
              key={index}
              user={user}
              auth={auth}
              users={users}
              selectActualUsers={selectActualUsers}
              paginationButton={paginationButton}
            />
          ))}
        </tbody>
      </Table>
      <Pagination className='pagination-style'>
        <Pagination.First onClick={setFirstPage} />
        {/* <Pagination.First onClick={(event) => generalSettings(event, 1)} /> */}
        {paginationButton !== 1 ? (
          <Pagination.Item onClick={reducePageButton} id='reduce-button'>
            {paginationButton - 1}
          </Pagination.Item>
        ) : (
          <></>
        )}
        <Pagination.Item active>{paginationButton}</Pagination.Item>
        {paginationButton * 12 < users.length ? (
          <Pagination.Item onClick={increasePageButton} id='increase-button'>
            {paginationButton + 1}
          </Pagination.Item>
        ) : (
          <></>
        )}
        <Pagination.Last onClick={setLastPage} />
      </Pagination>
    </Container>
  );
};

export default User;
