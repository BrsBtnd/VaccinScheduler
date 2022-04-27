import { createSelector } from 'reselect';

const selectCenterReducer = (state) => state.centers;

export const selectAllCenters = createSelector(
  [selectCenterReducer],
  (centersSlice) => centersSlice.centers
);

export const selectCentersByName = (state, name) =>
  selectAllCenters(state).filter((center) =>
    center.centerName.toLowerCase().includes(name)
  );

export const selectCenterById = (state, centerId) =>
  selectAllCenters(state).filter((center) => center.centerId === +centerId);

// export const selectMemoizeAuth = createSelector(
//   [selectCenterReducer],
//   (centers) =>
// );
