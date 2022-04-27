import { createSelector } from 'reselect';

// const selectAuthReducer = (state) => state.auth;

// export const selectMemoizeAuth = createSelector(
//   [selectAuthReducer],
//   (authSlice) => authSlice.auth
// );
// this is for memoization
export const selectUsersReducer = (state) => state.users.users;

export const selectUsers = createSelector([selectUsersReducer], (users) => {
  console.log(users);
  return users;
});
