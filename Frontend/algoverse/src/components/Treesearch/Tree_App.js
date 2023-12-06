import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { options } from '../../config';
import TreeGraph from "./TreeGraph";
import Col from 'react-bootstrap/Col';

function Tree_App() {
    return (
        <React.Fragment>
                <Col>
                    <TreeGraph options={options}/>
                </Col>
        </React.Fragment>
    );
}

export default Tree_App;
