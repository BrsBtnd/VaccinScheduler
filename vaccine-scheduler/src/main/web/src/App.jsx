import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route } from 'react-router-dom';
import NavBar from './components/navBar/NavBar';
import AuthCodeButton from './components/authorization/authCodeButton/AuthCodeButton';
import AuthJwtToken from './components/authorization/authJwtToken/AuthJwtToken';
import HomePage from './components/home/HomePage';
import User from './components/users/User';
import Vaccines from './components/vaccines/Vaccines';
import Center from './components/centers/center/Center';
import Unauthorized from './components/errorHandling/Unauthorized';
import FillUsers from './utils/fillDatabase/fillUsers';
import Schedules from './components/schedules/Schedules';

const App = () => {
  return (
    <>
      <Routes>
        <Route path='/' element={<NavBar />}>
          <Route index element={<HomePage />} />
          <Route exact path='/authcode' element={<AuthCodeButton />} />
          <Route path='/auth' element={<AuthJwtToken />} />
          <Route path='/users' element={<User />} />
          <Route path='/vaccines' element={<Vaccines />} />
          <Route path='/center/:centerId' element={<Center />} />
          <Route path='/unauthorized' element={<Unauthorized />} />
          <Route path='/schedules' element={<Schedules />} />

          {/* <Route path='/fill' element={<FillUsers />} /> */}
        </Route>
      </Routes>
    </>
  );
};

export default App;
