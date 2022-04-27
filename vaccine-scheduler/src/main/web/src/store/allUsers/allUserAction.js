import { createAction } from '../../utils/reducer/reducer';
import ALL_USER_ACTION_TYPES from './allUserActionTypes';

export const setUsers = (users) =>
  createAction(ALL_USER_ACTION_TYPES.SET_USERS, users);
