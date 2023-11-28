import React, { useState } from "react";
import Form from 'react-bootstrap/Form';
import FormLabel from "react-bootstrap/FormLabel";
import '../../style/treeForm.css';
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

const InputForm = (props) => {
    const { tree } = props;
    const [enabled, setEnabled] = useState(false);

    const handleInput = (event) => {
        const buttonClass = event.nativeEvent.submitter.className;
        event.preventDefault();
        
        if(buttonClass.includes("add-btn")){
            tree.insert(event.target.input.value.toLowerCase());
        }
        else if(buttonClass.includes("delete-btn")){
            tree.delete(event.target.input.value.toLowerCase());
        }
        
        event.target.input.value = '';
        props.update(tree.toGraph());
        setEnabled(false);
    };

    const handleChange = (event) => {
        event.preventDefault();
        let value = event.target.value.toLowerCase();
        setEnabled(value.length !== 0);
    };

    return (
        <div className={"center"}>
            { <Form onSubmit={e => handleInput(e)} className={"form"}>
                <FormLabel className={"noticebar center"} htmlFor={"input"}>Enter word or number</FormLabel>
                <Row>
                    <Col className={"col-1"}>
                        <input
                            className={"form-control"}
                            type={"text"} id="input"
                            placeholder={"  ex. 10 or desk"}
                            onChange={handleChange}
                        />
                    {/* </Col>
                    <Col className={"col-2 center"}> */}
                        <input type={"submit"} className={"btn btn-primary add-btn"} value={"add"} disabled={!enabled}/>
                        <input type={"submit"} className={"btn btn-primary delete-btn"} value={"delete"} disabled={!enabled}/>
                    </Col>
                </Row>
            </Form> }
        </div>
    );
};

export default InputForm;
