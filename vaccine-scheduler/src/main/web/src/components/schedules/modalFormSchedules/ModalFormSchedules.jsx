import { Modal, Form, FloatingLabel, Button } from 'react-bootstrap';
import './ModalFormSchedules.css';
import { useEffect, useState } from 'react';
import {
  getFromResourceServer,
  getFromResourceServerWithHeader,
  postSchedule,
} from '../../../utils/resources/fetchRequests';
import { authErrorHandling } from '../../../utils/errorHandling/errorHandling';

import { getResourcesForErrorHandling } from '../../../utils/resources/resourcesForErrorHandling';
import { useSelector } from 'react-redux';
import { selectUser } from '../../../store/user/userSelector';

const ModalFormSchedules = ({ show, onHide }) => {
  const user = useSelector(selectUser);
  const initialBody = {
    vaccineDate: '',
    center: {},
    user,
    vaccine: {},
  };

  const { dispatch, navigate, auth } = getResourcesForErrorHandling();
  const [centers, setCenters] = useState([]);
  const [vaccines, setVaccines] = useState([]);
  const [body, setBody] = useState(initialBody);
  const [validated, setValidated] = useState(false);

  useEffect(() => {
    const fetchVaccines = async () => {
      try {
        const fetchedVaccines = await getFromResourceServerWithHeader(
          'vaccines',
          auth
        );
        setVaccines(fetchedVaccines);
      } catch (err) {
        await authErrorHandling(err, dispatch, navigate, auth);
      }
    };
    fetchVaccines();
  }, []);

  const findVaccine = (value) =>
    vaccines.find((vaccine) => vaccine.producer === value);

  const findCenter = (value) =>
    centers.find((center) => center.centerName === value);

  const createBodyComponentType = (compType, value) => {
    const findedVaccine = findVaccine(value);
    let finalCompType;
    let finalValue;

    if (!findedVaccine && !compType) {
      finalCompType = 'center';
      finalValue = findCenter(value);
    } else if (findedVaccine && !compType) {
      finalCompType = 'vaccine';
      finalValue = findedVaccine;
    } else {
      finalCompType = 'vaccineDate';
      finalValue = value;
    }
    return { finalCompType, finalValue };
  };

  const setBodyContent = (event) => {
    const { finalCompType: bodyParam, finalValue: value } =
      createBodyComponentType(event.target.placeholder, event.target.value);

    setBody({
      ...body,
      [bodyParam]: value,
    });
  };

  const fetchCenters = async (path) => {
    const fetchedCenters = await getFromResourceServer(
      `centers/search/${path}`
    );
    setCenters(fetchedCenters);
  };

  const getCenters = (event) => {
    return event.target.value.length
      ? fetchCenters(event.target.value)
      : setCenters([]);
  };

  const handlePostSchedule = () => {
    setValidated(true);
    postSchedule(body);
    handleModalHide();
  };
  const handleModalHide = () => {
    onHide();
    setBody(initialBody);
    setCenters([]);
  };
  return (
    <Modal
      size='lg'
      centered
      aria-labelledby='contained'
      show={show}
      onHide={handleModalHide}
    >
      <Modal.Header closeButton>
        <Modal.Title>Appointment modal</Modal.Title>
      </Modal.Header>
      <Modal.Body className='modal-form-schedule-body'>
        <Form validated={validated} onSubmit={handlePostSchedule}>
          <FloatingLabel className='mb-3' label='Search for a vaccine center'>
            <Form.Control
              placeholder='Search for a vaccine center'
              onChange={getCenters}
              className='mb-1'
            ></Form.Control>
            <Form.Select onChange={setBodyContent} required>
              <option>Select a Vaccine Center</option>
              {centers.map((center, index) => (
                <option value={`${center.centerName}`} key={index}>
                  {center.centerName}
                </option>
              ))}
            </Form.Select>
          </FloatingLabel>

          <FloatingLabel
            label='Vaccine Date'
            className='mb-3'
            onChange={setBodyContent}
          >
            <Form.Control
              type='datetime-local'
              placeholder='Vaccine Date'
              required
            />
          </FloatingLabel>

          <FloatingLabel label='Vaccine' className='mb-3' id='vaccine'>
            <Form.Select onChange={setBodyContent} required>
              <option>Select a Vaccine</option>
              {vaccines.map((vaccine, index) => (
                <option value={`${vaccine.producer}`} key={index}>
                  {vaccine.producer}
                </option>
              ))}
            </Form.Select>
          </FloatingLabel>

          <Modal.Footer>
            <Button type='submit' className='modal-form-schedule-button'>
              Make an appointment
            </Button>
          </Modal.Footer>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default ModalFormSchedules;
