import { createAction } from '../../utils/reducer/reducer';
import USER_ACTION_TYPES from './userActionTypes';

export const setUser = (user) => createAction(USER_ACTION_TYPES.SET_USER, user);

export const signInUser = (auth) =>
  createAction(USER_ACTION_TYPES.SIGN_IN, auth);

export const signOutUser = () => createAction(USER_ACTION_TYPES.SIGN_OUT, null);
