import { createSelector } from 'reselect';

// const selectAuthReducer = (state) => state.auth;

// export const selectMemoizeAuth = createSelector(
//   [selectAuthReducer],
//   (authSlice) => authSlice.auth
// );
// this is for memoization
export const selectAuth = (state) => state.user.auth;

export const selectUserRole = (state) =>
  state.user.user?.userRole.split('_')[1];

export const selectUser = (state) => state.user.user;
