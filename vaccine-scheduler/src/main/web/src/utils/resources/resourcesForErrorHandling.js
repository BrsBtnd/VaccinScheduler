import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import { selectAuth } from '../../store/user/userSelector';

export const getResourcesForErrorHandling = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector(selectAuth);
  return {
    dispatch,
    navigate,
    auth,
  };
};

export const getDispatchAndNavigate = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  return {
    dispatch,
    navigate,
  };
};
