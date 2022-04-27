import { createAction } from '../../utils/reducer/reducer';
import CENTER_ACTION_TYPES from './centerActionTypes';

export const setCenters = (centers) =>
  createAction(CENTER_ACTION_TYPES.SET_CENTER, centers);
