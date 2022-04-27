import USER_ACTION_TYPES from './userActionTypes';

const INITIAL_STATE = {
  user: null,
};

export const userReducer = (state = INITIAL_STATE, action) => {
  const { type, payload } = action;

  switch (type) {
    case USER_ACTION_TYPES.SET_USER:
      return { ...state, user: payload };
    case USER_ACTION_TYPES.SIGN_IN:
      return { ...state, auth: payload };
    case USER_ACTION_TYPES.SIGN_OUT:
      return { ...state, auth: null, user: null };
    default:
      return state;
  }
};
