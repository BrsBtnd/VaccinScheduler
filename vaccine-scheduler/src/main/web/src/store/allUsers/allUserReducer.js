import ALL_USER_ACTION_TYPES from './allUserActionTypes';

const INITIAL_STATE = {
  users: null,
};

export const allUserReducer = (state = INITIAL_STATE, action) => {
  const { type, payload } = action;

  switch (type) {
    case ALL_USER_ACTION_TYPES.SET_USERS:
      return { ...state, users: payload };
    default:
      return state;
  }
};
