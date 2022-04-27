import { Card } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

import { selectAuth } from '../../../store/user/userSelector';

import '../../button/CustomButtonStyle.css';
import './CenterPreview.css';

const CenterPreview = ({ center }) => {
  const auth = useSelector(selectAuth);
  const { centerName, city, region, centerId } = center;

  return (
    <Card className='center-preview-card'>
      <Card.Body>
        <Card.Title>{centerName}</Card.Title>
        <Card.Subtitle>City: {city}</Card.Subtitle>
        <Card.Text>Region: {region}</Card.Text>
        {auth ? (
          <Link
            to={`/center/${centerId}`}
            state={{ center }}
            className='btn btn-primary log-button center-preview-button'
          >
            Reserve in this center
          </Link>
        ) : (
          <Card.Text>
            To more information please login or create an account
          </Card.Text>
        )}
      </Card.Body>
    </Card>
  );
};

export default CenterPreview;
