import { useLocation } from 'react-router-dom';

import CenterSearch from '../centers/centerSearch/CenterSearch';

const HomePage = () => {
  const { state } = useLocation();
  return (
    <>
      {state?.refreshTokenExpired ? (
        <p className='error-message'>
          Refresh Token Expired! Reauthentication needed!
        </p>
      ) : (
        <></>
      )}
      <CenterSearch />
    </>
  );
};

export default HomePage;
