import React, { useState } from "react";
import Form from 'react-bootstrap/Form';
import FormLabel from "react-bootstrap/FormLabel";
import '../../style/treeForm.css';
import { useNavigate } from "react-router-dom";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";

const InputForm = (props) => {
    const { tree } = props;
    const [enabled, setEnabled] = useState(false);

    const navigate = useNavigate();

    const handleButtonClick = (algorithm) => {
        // Update the URL with the selected algorithm parameter
        navigate(`?algorithm=${algorithm}`);
    };

    const handleInput = async (event) => {
        const buttonClass = event.nativeEvent.submitter.className;
        event.preventDefault();        
    
        if (buttonClass.includes("add-btn")) {

            handleButtonClick("Binary-add");

            tree.insert(event.target.input.value.toLowerCase());
            tree.all_clear();

            event.target.input.value = '';
            props.update(tree.toGraph());

        } else if (buttonClass.includes("delete-btn")) {

            handleButtonClick("Binary-delete");

            tree.delete(event.target.input.value.toLowerCase());

            event.target.input.value = '';
            props.update(tree.toGraph());

        } else if (buttonClass.includes("binary-select-btn")) {

            handleButtonClick("Binary-search");

            tree.all_clear();
            let target = event.target.input.value.toLowerCase();
            
            if(target.trim() !== ""){
                let result = tree.binarySearch(target);
                console.log(result);
    
                while (result === -1) {
                    // Wait for 500ms before the next iteration
                    event.target.input.value = '';
                    props.update(tree.toGraph());
    
                    // Wrap setTimeout in a Promise to make it asynchronous
                    await new Promise(resolve => setTimeout(resolve, 500));
    
                    result = tree.binarySearch(target);
                }
    
                event.target.input.value = '';
                props.update(tree.toGraph());
            }
           
        }else if (buttonClass.includes("bfs-select-btn")) {
            handleButtonClick("BFS");
            tree.all_clear();
            let target = event.target.input.value.toLowerCase();
            if (target.trim() !== ""){
                let result = tree.bfsSearch(target);
                console.log(result);

                while (result === -1) {
                    // Wait for 500ms before the next iteration
                    event.target.input.value = '';
                    props.update(tree.toGraph());

                    // Wrap setTimeout in a Promise to make it asynchronous
                    await new Promise(resolve => setTimeout(resolve, 500));

                    result = tree.bfsSearch(target);
                }

                event.target.input.value = '';
                props.update(tree.toGraph());

            }
            
        }
        else if (buttonClass.includes("dfs-select-btn")) {
            handleButtonClick("DFS");
            tree.all_clear();
            let target = event.target.input.value.toLowerCase();
            if (target.trim() !== ""){
                let result = tree.dfsSearch(target);
                console.log(result);
    
                while (result === -1) {
                    // Wait for 500ms before the next iteration
                    event.target.input.value = '';
                    props.update(tree.toGraph());
    
                    // Wrap setTimeout in a Promise to make it asynchronous
                    await new Promise(resolve => setTimeout(resolve, 500));
    
                    result = tree.dfsSearch(target);
                }
    
                event.target.input.value = '';
                props.update(tree.toGraph());

            }
           
        }
    
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
                            placeholder={" ex. 10 or desk"}
                            onChange={handleChange}
                        />
                        <input type={"submit"} className={"btn btn-primary add-btn"} value={"Insert"} disabled={!enabled}/>
                        <input type={"submit"} className={"btn btn-primary delete-btn"} value={"Delete"} disabled={!enabled}/>
                    </Col>
                    <Col className={"col-2"}>
                        <input type={"submit"} className={"btn btn-primary select binary-select-btn"} value={"Binary Search"} />
                        <input type={"submit"} className={"btn btn-primary select dfs-select-btn"} value={"DFS Search"}/>
                        <input type={"submit"} className={"btn btn-primary select bfs-select-btn"} value={"BFS Search"}/>
                    </Col>
                </Row>
            </Form> }
        </div>
    );
};

export default InputForm;
