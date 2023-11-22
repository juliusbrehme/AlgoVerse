import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { options } from '../../config';
import TreeGraph from "./TreeGraph";
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';

function Tree_App() {
    return (
        <React.Fragment>
            <Container fluid>
                <Col>
                    <TreeGraph options={options}/>
                </Col>
            </Container>
        </React.Fragment>
    );
}

export default Tree_App;
