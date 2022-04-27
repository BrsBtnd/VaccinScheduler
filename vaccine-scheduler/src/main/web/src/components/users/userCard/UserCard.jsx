import { deleteUser } from '../../../utils/resources/fetchRequests';
import DeleteButton from '../../button/deleteButton/DeleteButton';

import './UserCard.css';
const UserCard = ({
  user,
  auth,
  users,
  selectActualUsers,
  paginationButton,
}) => {
  const { userId, username, cnp, age, firstName, lastName, gender } = user;

  const deleteUserOnClick = async (event) => {
    event.target.blur();
    const delUInd = users.findIndex((user) => user.userId === userId);

    users.splice(delUInd, 1);
    selectActualUsers(paginationButton, users);
    await deleteUser(userId, auth);
  };

  return (
    <tr>
      <td>{username}</td>
      <td>{firstName}</td>
      <td>{lastName}</td>
      <td>{age}</td>
      <td>{cnp}</td>
      <td>{gender}</td>
      <td className='user-delete-button-table-data'>
        <DeleteButton buttonName='Delete' onClick={deleteUserOnClick}/>
      </td>
    </tr>
  );
};

export default UserCard;
