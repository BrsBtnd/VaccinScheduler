import { useState } from 'react';
import { Button, FloatingLabel, Form, Offcanvas } from 'react-bootstrap';

import { postUser } from '../../utils/resources/fetchRequests';
import CustomButton from '../button/CustomButton';

import './CreateAccountOffCanvas.css';

const CreateAccountOffCanvas = () => {
  const initialBody = {
    firstName: '',
    lastName: '',
    age: 0,
    gender: '',
    username: '',
    password: '',
    cnp: '',
    userRole: 'ROLE_USER',
  };

  const [body, setBody] = useState(initialBody);
  const [show, setShow] = useState(false);
  const [validated, setValidated] = useState(false);
  const toggleShow = (event) => {
    event.target.blur();
    setShow((s) => !s);
  };
  const handleClose = () => {
    setShow(false);
    setBody(initialBody);
  };

  const createBodyComponentType = (compType) => {
    const compTypeSplit = compType
      ? compType.toLowerCase().split(' ')
      : ['gender'];
    let finalCompType = compTypeSplit[0];
    if (compTypeSplit.length === 2) {
      finalCompType =
        compTypeSplit[0] +
        compTypeSplit[1].charAt(0).toUpperCase() +
        compTypeSplit[1].slice(1);
    }
    return finalCompType;
  };

  const setBodyContent = (event) => {
    const bodyParam = createBodyComponentType(event.target.placeholder);
    setBody({
      ...body,
      [bodyParam]: event.target.value ? event.target.value : '',
    });
  };

  const sendUserToDb = async () => {
    console.log(body);
    setValidated(true);
    await postUser(body);
    setBody(initialBody);
    setShow(false);
  };

  return (
    <>
      <CustomButton onClick={toggleShow} buttonName='Create account' />
      <Offcanvas show={show} scroll={true} onHide={handleClose}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>Create account</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body className='offcanvas-body'>
          <Form validated={validated} onSubmit={sendUserToDb}>
            <FloatingLabel
              label='Username'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='text' placeholder='Username' required />
            </FloatingLabel>
            <FloatingLabel
              label='First name'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='text' placeholder='First name' required />
            </FloatingLabel>
            <FloatingLabel
              label='Last name'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='text' placeholder='Last name' required />
            </FloatingLabel>
            <FloatingLabel
              label='Password'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='password' placeholder='Password' required />
            </FloatingLabel>
            <FloatingLabel
              label='Age'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='number' placeholder='Age' required />
            </FloatingLabel>
            <FloatingLabel label='Gender' className='mb-3'>
              <Form.Select onChange={setBodyContent} required>
                <option value='female'>Female</option>
                <option value='male'>Male</option>
              </Form.Select>
            </FloatingLabel>
            <FloatingLabel
              label='CNP'
              className='mb-3'
              onChange={setBodyContent}
            >
              <Form.Control type='number' placeholder='CNP' required />
            </FloatingLabel>
            <Button type='submit' className='create-account-button'>
              Create Account{' '}
            </Button>
          </Form>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
};

export default CreateAccountOffCanvas;
