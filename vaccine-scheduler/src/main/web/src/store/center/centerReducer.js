import CENTER_ACTION_TYPES from './centerActionTypes';

const INITIAL_STATE = {
  centers: [],
};

export const centerReducer = (state = INITIAL_STATE, action) => {
  const { type, payload } = action;

  switch (type) {
    case CENTER_ACTION_TYPES.SET_CENTER:
      return { ...state, centers: payload };
    default:
      return state;
  }
};
