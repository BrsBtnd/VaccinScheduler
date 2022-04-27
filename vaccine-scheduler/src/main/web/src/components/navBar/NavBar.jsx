import { Container, Navbar, NavLink } from 'react-bootstrap';
import { Link, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { selectAuth, selectUserRole } from '../../store/user/userSelector';

import CreateAccountOffCanvas from '../createAccountOffCanvas/CreateAccountOffCanvas';
import LoginLogoutButton from './navBarButtons/LoginLogoutButton';
import SchedulesButton from './navBarButtons/SchedulesButton';
import UserButton from './navBarButtons/UserButton';
import VaccineButton from './navBarButtons/VaccinesButton';

import './NavBar.css';

const NavBar = () => {
  const auth = useSelector(selectAuth);
  const userRole = useSelector(selectUserRole);

  return (
    <>
      <Navbar expand={false} className='navbar-style'>
        <Navbar.Brand>
          <NavLink to='/' as={Link} id='nav-link'>
            Vaccine Scheduler
          </NavLink>
        </Navbar.Brand>
        <Container id='button-container-style'>
          <SchedulesButton auth={auth} />
          <UserButton userRole={userRole} />
          <VaccineButton auth={auth} />
          {!auth ? <CreateAccountOffCanvas /> : <></>}
          <LoginLogoutButton auth={auth} />
        </Container>
      </Navbar>
      <Outlet />
    </>
  );
};

export default NavBar;
