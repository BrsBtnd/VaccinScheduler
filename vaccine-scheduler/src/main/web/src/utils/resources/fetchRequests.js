import { getAuthorizationHeader } from './headers';
import { resourceServerApiUrl } from './urls';

export const getFromResourceServer = async (path) => {
  const resp = await fetch(`${resourceServerApiUrl}${path}`);
  const data = await resp.json();
  return data;
};

export const getFromResourceServerWithHeader = async (path, auth) => {
  const headers = getAuthorizationHeader(auth);
  const resp = await fetch(`${resourceServerApiUrl}${path}`, {
    headers: headers,
  });
  if (!auth && resp.status === 401) {
    return new Promise((resolve, reject) => {
      reject(new Error(401));
    });
  }
  if (resp.status === 401) {
    return new Promise((resolve, reject) =>
      reject(new Error('Need refresh token'))
    );
  }
  const data = await resp.json();

  return data;
};

export const getUserFromResourceServer = async (username, auth) => {
  const headers = getAuthorizationHeader(auth);
  const resp = await fetch(
    `${resourceServerApiUrl}users/username?username=${username}`,
    { headers: headers }
  );
  const data = await resp.json();

  return data;
};

export const postUser = async (body) => {
  await postFetch(body, 'users');
};

export const postSchedule = async (body) => {
  await postFetch(body, 'userSchedules');
};

const postFetch = (body, path) =>
  fetch(`${resourceServerApiUrl}${path}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify(body),
  });

export const deleteUser = async (userId, auth) => {
  await deleteFetch(userId, auth, 'users');
};

export const deleteSchedule = async (scheduleId, auth) => {
  await deleteFetch(scheduleId, auth, 'userSchedules');
};

const deleteFetch = (id, auth, path) => {
  const headers = getAuthorizationHeader(auth);
  return fetch(`${resourceServerApiUrl}${path}/${id}`, {
    method: 'DELETE',
    headers: headers,
  });
};
